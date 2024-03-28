/*
package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.mq.event.GeneralMessageEvent;
import com.metazz.metazzspace.common.mq.producer.GeneralMessageDemoProduce;
import com.metazz.metazzspace.common.response.BaseController;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/message")
@Slf4j
@Api(value = "消息队列")
@SuppressWarnings("all")
public class MessageController implements BaseController {

    private final GeneralMessageDemoProduce generalMessageDemoProduce;

    public MessageController(GeneralMessageDemoProduce generalMessageDemoProduce) {
        this.generalMessageDemoProduce = generalMessageDemoProduce;
    }

    @PostMapping("send/general-message")
    @Operation(summary = "发送RocketMQ普通消息")
    public String sendGeneralMessage() {
        String keys = UUID.randomUUID().toString();
        GeneralMessageEvent generalMessageEvent = GeneralMessageEvent.builder()
                .body("消息具体内容，可以是自定义对象，最终都会序列化为字符串。如果是取消订单，这里应该是订单ID或者相关联的信息")
                .keys(keys)
                .build();
        SendResult sendResult = generalMessageDemoProduce.sendMessage(
                "metazz-topic",
                "general",
                keys,
                generalMessageEvent
        );
        return sendResult.getSendStatus().name();
    }

}
*/
