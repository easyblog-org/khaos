package top.easyblog.titan.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.bean.message.MessageConfigBean;
import top.easyblog.titan.bean.message.MessageTemplateBean;
import top.easyblog.titan.feign.config.CommonFeignConfig;
import top.easyblog.titan.feign.internal.BaseClientResponse;
import top.easyblog.titan.feign.internal.Verify;
import top.easyblog.titan.request.message.CreateMessageConfigRequest;
import top.easyblog.titan.request.template.QueryMessageTemplateRequest;
import top.easyblog.titan.request.template.QueryMessageTemplatesRequest;
import top.easyblog.titan.request.template.UpdateMessageTemplateRequest;
import top.easyblog.titan.response.PageResponse;

/**
 * @author: frank.huang
 * @date: 2023-02-25 14:27
 */
@FeignClient(name = "message-config", url = "${urls.easyblog}", configuration = CommonFeignConfig.class)
public interface MessageConfigClient extends Verify {

    /**
     * 创建消息模板
     *
     * @param request
     * @return
     */
    @PostMapping("/v1/in/message-config")
    BaseClientResponse<MessageConfigBean> create(@RequestBody CreateMessageConfigRequest request);

    /**
     * 更新消息模板
     *
     * @param request
     * @return
     */
    @PutMapping("/v1/in/message-config/{code}")
    BaseClientResponse<Void> update(@PathVariable("code") String code, @RequestBody UpdateMessageTemplateRequest request);

    /**
     * 查询消息模板详情
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/message-config")
    BaseClientResponse<MessageTemplateBean> queryMessageTemplateDetails(@SpringQueryMap QueryMessageTemplateRequest request);

    /**
     * 查询消息模板列表
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/message-configs")
    BaseClientResponse<PageResponse<MessageTemplateBean>> queryMessageTemplateList(@SpringQueryMap QueryMessageTemplatesRequest request);

}
