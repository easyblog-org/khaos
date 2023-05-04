package top.easyblog.titan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.easyblog.titan.annotation.ResponseWrapper;
import top.easyblog.titan.request.message.CreateMessagePushRuleRequest;
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

}
