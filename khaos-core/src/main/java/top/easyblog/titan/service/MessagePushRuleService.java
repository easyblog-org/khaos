package top.easyblog.titan.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.bean.message.MessageConfigBean;
import top.easyblog.titan.bean.message.MessageConfigRuleBean;
import top.easyblog.titan.bean.message.MessagePushRuleBean;
import top.easyblog.titan.constant.Constants;
import top.easyblog.titan.converter.BeanMapper;
import top.easyblog.titan.enums.MessageConfigType;
import top.easyblog.titan.exception.BusinessException;
import top.easyblog.titan.feign.client.MessageConfigClient;
import top.easyblog.titan.feign.client.MessageConfigRuleClient;
import top.easyblog.titan.request.message.*;
import top.easyblog.titan.response.KhaosResultCode;
import top.easyblog.titan.response.PageResponse;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: frank.huang
 * @date: 2023-05-04 20:28
 */
@Slf4j
@Service
public class MessagePushRuleService {

    @Autowired
    private MessageConfigClient messageConfigClient;

    @Autowired
    private MessageConfigRuleClient messageConfigRuleClient;

    @Autowired
    private BeanMapper beanMapper;

    /**
     * 创建消息推送规则
     *
     * @param request
     */
    public void createMessagePushRule(CreateMessagePushRuleRequest request) {
        List<CreateMessageConfigRequest> messageParameterConfigs = request.getMessageParameterConfigs();
        List<String> configIds = createMessageConfigs(messageParameterConfigs);

        CreateMessageConfigRuleRequest messageRuleConfig = request.getMessageRuleConfig();
        messageRuleConfig.setConfigIds(StringUtils.join(configIds, Constants.COMMA));
        messageConfigRuleClient.create(messageRuleConfig);
    }

    private List<String> createMessageConfigs(List<CreateMessageConfigRequest> messageParameterConfigs) {
        if (CollectionUtils.isEmpty(messageParameterConfigs)) {
            log.info("Empty message parameter config list,ignore.");
            throw new BusinessException(KhaosResultCode.MESSAGE_PARAM_CONFIG_REQUIRED);
        }

        messageParameterConfigs.stream().filter(Objects::nonNull)
                .filter(config -> StringUtils.equalsIgnoreCase(MessageConfigType.RECEIVER.name(), config.getType()))
                .findAny().orElseThrow(() -> new BusinessException(KhaosResultCode.MESSAGE_PARAM_CONFIG_RECEIVER_REQUIRED));

        return messageParameterConfigs.stream().filter(Objects::nonNull).map(config -> {
            MessageConfigBean messageConfigBean = messageConfigClient.request(() -> messageConfigClient.create(config));
            return Optional.ofNullable(messageConfigBean).map(MessageConfigBean::getCode).orElse(null);
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
    

    public MessagePushRuleBean details(QueryMessagePushRuleRequest request) {
        MessageConfigRuleBean messageConfigRuleBean = messageConfigRuleClient.request(() -> messageConfigRuleClient.queryMessageTemplateDetails(QueryMessageConfigRuleRequest.builder()
                .businessEvent(request.getBusinessEvent()).businessModule(request.getBusinessModule()).code(request.getCode()).build()));
        return Optional.ofNullable(messageConfigRuleBean).map(configRule -> {

            MessagePushRuleBean messagePushRuleBean = beanMapper.buildMessagePushRuleBean(configRule);

            List<String> configIds = Arrays.stream(StringUtils.split(configRule.getConfigIds(), Constants.COMMA)).collect(Collectors.toList());
            PageResponse<MessageConfigBean> configBeanPageResponse = messageConfigClient.request(() -> messageConfigClient.queryMessageConfigList(QueryMessageConfigsRequest.builder()
                    .codes(configIds).build()));
            if (Objects.isNull(configBeanPageResponse) || CollectionUtils.isNotEmpty(configBeanPageResponse.getData())) {
                return messagePushRuleBean;
            }

            messagePushRuleBean.setConfigs(configBeanPageResponse.getData());
            return messagePushRuleBean;
        }).orElse(null);
    }

    public PageResponse<MessagePushRuleBean> list(QueryMessagePushRulesRequest request) {
        PageResponse<MessageConfigRuleBean> configRuleBeanPageResponse = messageConfigRuleClient.request(() -> messageConfigRuleClient.queryMessageTemplateList(QueryMessageConfigRulesRequest.builder()
                .businessEvents(request.getBusinessEvents())
                .businessModules(request.getBusinessEvents())
                .deleted(request.getDeleted())
                .limit(request.getLimit())
                .offset(request.getOffset()).build()));
        if (Objects.isNull(configRuleBeanPageResponse) || CollectionUtils.isEmpty(configRuleBeanPageResponse.getData())) {
            return PageResponse.<MessagePushRuleBean>builder().offset(request.getOffset()).limit(request.getLimit())
                    .total(NumberUtils.LONG_ZERO).data(Collections.emptyList()).build();
        }

        PageResponse<MessagePushRuleBean> pageResponse = beanMapper.buildMessagePushRuleBean(configRuleBeanPageResponse);

        List<MessageConfigRuleBean> messageConfigRuleBeans = configRuleBeanPageResponse.getData();
        List<String> configIds = messageConfigRuleBeans.stream().map(config -> StringUtils.split(config.getConfigIds(), Constants.COMMA))
                .flatMap(Arrays::stream)
                .distinct().collect(Collectors.toList());
        PageResponse<MessageConfigBean> configBeanPageResponse = messageConfigClient.request(() -> messageConfigClient.queryMessageConfigList(QueryMessageConfigsRequest.builder()
                .codes(configIds).build()));
        if (Objects.isNull(configBeanPageResponse) || CollectionUtils.isNotEmpty(configBeanPageResponse.getData())) {
            return pageResponse;
        }

        List<MessageConfigBean> messageConfigBeans = configBeanPageResponse.getData();
        Map<String, MessageConfigBean> configBeanMap = messageConfigBeans.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(MessageConfigBean::getCode, Function.identity(), (x, y) -> x));

        List<MessagePushRuleBean> messagePushRuleBeans = pageResponse.getData();
        messagePushRuleBeans = messagePushRuleBeans.stream().filter(Objects::nonNull).peek(pushRuleBean -> {
            List<String> configIdList = Arrays.stream(StringUtils.split(pushRuleBean.getConfigIds(), Constants.COMMA)).collect(Collectors.toList());
            List<MessageConfigBean> configBeanList = configIdList.stream()
                    .filter(Objects::nonNull).map(configBeanMap::get).filter(Objects::nonNull).collect(Collectors.toList());
            pushRuleBean.setConfigs(configBeanList);
        }).collect(Collectors.toList());

        pageResponse.setData(messagePushRuleBeans);
        return pageResponse;
    }

    public void update(String code, UpdateMessagePushRuleRequest request) {

    }
}
