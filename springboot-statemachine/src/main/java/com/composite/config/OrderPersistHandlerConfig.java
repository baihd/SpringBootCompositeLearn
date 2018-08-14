package com.composite.config;

import com.composite.pojo.OrderStatus;
import com.composite.pojo.OrderStatusChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class OrderPersistHandlerConfig {
    @Autowired
    private StateMachine<OrderStatus, OrderStatusChangeEvent> stateMachine;

    @Bean
    public PersistStateMachineHandler persistStateMachineHandler() {
        PersistStateMachineHandler handler = new PersistStateMachineHandler(stateMachine);
        handler.addPersistStateChangeListener(persistStateChangeListener());
        return handler;
    }

    @Bean
    public OrderPersistStateChangeListener persistStateChangeListener() {
        return new OrderPersistStateChangeListener();
    }

}
