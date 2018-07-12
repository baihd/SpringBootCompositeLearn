package com.composite.producer;

import com.composite.config.RabbitMqEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * rabbitmq发送消息工具类
 **/
@Component
public class RabbitMqProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqProducer.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 如果消息没有到exchange,则confirm回调,ack=false
     * 如果消息到达exchange,则confirm回调,ack=true
     * exchange到queue成功,则不回调return
     * exchange到queue失败,则回调return
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            /*logger.info("消息唯一标识: " + correlationData);
            logger.info("消息到达exchange");
            logger.info("失败原因: " + cause);*/
        }else {
            logger.info("消息唯一标识: " + correlationData);
            logger.info("消息未到达exchange");
            logger.info("失败原因: " + cause);
        }

    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info("消息主体:" + message);
        logger.info("消息主体:" + replyCode);
        logger.info("描述：" + replyText);
        logger.info("消息使用的交换器:" + exchange);
        logger.info("消息使用的路由键:" + routingKey);
    }

    public void sendFanout(Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.CONTRACT_FANOUT.getCode(), "", obj, correlationData);
    }

    public void sendDirect(String routeKey, Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //logger.info("send: " + correlationData.getId());
        //1.exchange,queue都正确,confirm被回调, ack=true
        this.rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.CONTRACT_DIRECT.getCode(), routeKey, obj);
        this.rabbitTemplate.convertSendAndReceive(RabbitMqEnum.Exchange.CONTRACT_DIRECT.getCode(), routeKey, obj);
        //2.exchange错误,queue 正确,confirm被回调, ack=false
        //this.rabbitTemplate.convertAndSend("NO", routeKey, obj);
        //3.exchange正确,queue错误,confirm被回调,ack=true;return被回调replyText:NO_ROUTE
        //this.rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.CONTRACT_DIRECT.getCode(), "NO", obj);
        //4.exchange错误,queue错误,confirm被回调,ack=false
        //this.rabbitTemplate.convertAndSend("NO","NO",obj);
    }

    public Object sendAndReceiveDirect(String routeKey, Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Object object = this.rabbitTemplate.convertSendAndReceive(RabbitMqEnum.Exchange.CONTRACT_DIRECT.getCode(), routeKey, obj);
        return object;
    }

    public void sendTopic(String routeKey, Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.CONTRACT_TOPIC.getCode(), routeKey, obj, correlationData);
    }

}
