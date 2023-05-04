package top.easyblog.titan.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.bean.message.MessageConfigBean;
import top.easyblog.titan.constant.Constants;
import top.easyblog.titan.enums.MessageConfigType;
import top.easyblog.titan.exception.BusinessException;
import top.easyblog.titan.feign.client.MessageConfigClient;
import top.easyblog.titan.feign.client.MessageConfigRuleClient;
import top.easyblog.titan.request.message.CreateMessageConfigRequest;
import top.easyblog.titan.request.message.CreateMessageConfigRuleRequest;
import top.easyblog.titan.request.message.CreateMessagePushRuleRequest;
import top.easyblog.titan.response.KhaosResultCode;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

}
