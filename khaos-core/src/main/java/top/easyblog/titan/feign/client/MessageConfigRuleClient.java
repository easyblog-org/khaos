package top.easyblog.titan.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.bean.message.MessageConfigRuleBean;
import top.easyblog.titan.feign.config.CommonFeignConfig;
import top.easyblog.titan.feign.internal.BaseClientResponse;
import top.easyblog.titan.feign.internal.Verify;
import top.easyblog.titan.request.message.CreateMessageConfigRuleRequest;
import top.easyblog.titan.request.message.QueryMessageConfigRuleRequest;
import top.easyblog.titan.request.message.QueryMessageConfigRulesRequest;
import top.easyblog.titan.request.message.UpdateMessageConfigRuleRequest;
import top.easyblog.titan.response.PageResponse;

/**
 * @author: frank.huang
 * @date: 2023-02-25 14:27
 */
@FeignClient(name = "message-config-rule", url = "${urls.easyblog}", configuration = CommonFeignConfig.class)
public interface MessageConfigRuleClient extends Verify {

    /**
     * 创建消息配置
     *
     * @param request
     * @return
     */
    @PostMapping("/v1/in/config-rule")
    BaseClientResponse<Void> create(@RequestBody CreateMessageConfigRuleRequest request);

    /**
     * 更新消息配置
     *
     * @param request
     * @return
     */
    @PutMapping("/v1/in/config-rule/{code}")
    BaseClientResponse<Void> update(@PathVariable("code") String code, @RequestBody UpdateMessageConfigRuleRequest request);

    /**
     * 查询消息配置详情
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/config-rule")
    BaseClientResponse<MessageConfigRuleBean> queryMessageTemplateDetails(@SpringQueryMap QueryMessageConfigRuleRequest request);

    /**
     * 查询消息配置列表
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/config-rules")
    BaseClientResponse<PageResponse<MessageConfigRuleBean>> queryMessageTemplateList(@SpringQueryMap QueryMessageConfigRulesRequest request);

}
