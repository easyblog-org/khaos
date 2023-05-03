package top.easyblog.titan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.annotation.ResponseWrapper;
import top.easyblog.titan.bean.message.MessageTemplateBean;
import top.easyblog.titan.request.template.CreateMessageTemplateRequest;
import top.easyblog.titan.request.template.QueryMessageTemplateRequest;
import top.easyblog.titan.request.template.QueryMessageTemplatesRequest;
import top.easyblog.titan.request.template.UpdateMessageTemplateRequest;
import top.easyblog.titan.response.PageResponse;
import top.easyblog.titan.service.MessageTemplateService;

import javax.validation.Valid;

/**
 * @author: frank.huang
 * @date: 2023-02-25 13:48
 */
@RequestMapping("/v1/template")
@RestController
public class MessageTemplateController {


    @Autowired
    private MessageTemplateService templateService;


    @ResponseWrapper
    @PostMapping("")
    public void createTemplate(@RequestBody @Valid CreateMessageTemplateRequest request) {
        templateService.create(request);
    }

    @ResponseWrapper
    @PutMapping("/{code}")
    public void updateUserAccount(@PathVariable("code") String code, @RequestBody @Valid UpdateMessageTemplateRequest request) {
        templateService.update(code, request);
    }


    @ResponseWrapper
    @GetMapping("")
    public MessageTemplateBean details(QueryMessageTemplateRequest request) {
        return templateService.details(request);
    }

    @ResponseWrapper
    @GetMapping("/list")
    public PageResponse<MessageTemplateBean> queryList(QueryMessageTemplatesRequest request) {
        return templateService.list(request);
    }


}
