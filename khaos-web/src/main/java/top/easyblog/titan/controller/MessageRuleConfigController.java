package top.easyblog.titan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.annotation.ResponseWrapper;
import top.easyblog.titan.bean.message.MessagePushRuleBean;
import top.easyblog.titan.request.message.CreateMessagePushRuleRequest;
import top.easyblog.titan.request.message.QueryMessagePushRuleRequest;
import top.easyblog.titan.request.message.QueryMessagePushRulesRequest;
import top.easyblog.titan.request.message.UpdateMessagePushRuleRequest;
import top.easyblog.titan.response.PageResponse;
import top.easyblog.titan.service.MessagePushRuleService;

import javax.validation.Valid;

/**
 * @author: frank.huang
 * @date: 2023-05-04 20:27
 */
@RequestMapping("/v1/message-rule-config")
@RestController
public class MessageRuleConfigController {

    @Autowired
    private MessagePushRuleService messagePushRuleService;


    @ResponseWrapper
    @PostMapping("")
    public void create(@RequestBody @Valid CreateMessagePushRuleRequest request) {
        messagePushRuleService.createMessagePushRule(request);
    }


    @ResponseWrapper
    @PutMapping("/{code}")
    public void update(@PathVariable("code") String code, UpdateMessagePushRuleRequest request) {
        messagePushRuleService.update(code, request);
    }

    @ResponseWrapper
    @GetMapping("")
    public MessagePushRuleBean details(QueryMessagePushRuleRequest request) {
        return messagePushRuleService.details(request);
    }

    @ResponseWrapper
    @GetMapping("/list")
    public PageResponse<MessagePushRuleBean> list(QueryMessagePushRulesRequest request) {
        return messagePushRuleService.list(request);
    }

}
