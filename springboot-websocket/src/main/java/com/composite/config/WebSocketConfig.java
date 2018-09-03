package com.composite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
//注解开启STOMP协议来传输基于代理的消息，此时控制器支持使用
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * 注册stomp的端点
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册两个STOMP的endpoint，分别用于广播和点对点
        registry.addEndpoint("/webSocketServer").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/queueServer").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置信息代理
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //订阅Broker名称
        registry.enableSimpleBroker("/queue", "/topic");
        //设置客户端请求前缀
        //例如客户端发送消息的目的地为/app/sendTest，则对应控制层@MessageMapping(“/sendTest”)
        //客户端订阅主题的目的地为/app/subscribeTest，则对应控制层@SubscribeMapping(“/subscribeTest”)
        registry.setApplicationDestinationPrefixes("/app");
        //点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        //registry.setUserDestinationPrefix("/user/");
    }

}
