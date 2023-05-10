package top.easyblog.titan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.bean.message.MessageTemplateBean;
import top.easyblog.titan.feign.client.MessageTemplateClient;
import top.easyblog.titan.request.template.CreateMessageTemplateRequest;
import top.easyblog.titan.request.template.QueryMessageTemplateRequest;
import top.easyblog.titan.request.template.QueryMessageTemplatesRequest;
import top.easyblog.titan.request.template.UpdateMessageTemplateRequest;
import top.easyblog.titan.response.PageResponse;

/**
 * @author: frank.huang
 * @date: 2023-05-01 18:53
 */
@Service
public class MessageTemplateService {

    @Autowired
    private MessageTemplateClient templateClient;


    public void create(CreateMessageTemplateRequest request) {
        templateClient.request(() -> templateClient.create(request));
    }

    public void update(String code, UpdateMessageTemplateRequest request) {
        templateClient.request(() -> templateClient.update(code, request));
    }

    public MessageTemplateBean details(QueryMessageTemplateRequest request) {
        return templateClient.request(() -> templateClient.queryMessageTemplateDetails(request));
    }

    public PageResponse<MessageTemplateBean> list(QueryMessageTemplatesRequest request) {
        return templateClient.request(() -> templateClient.queryMessageTemplateList(request));
    }

}
