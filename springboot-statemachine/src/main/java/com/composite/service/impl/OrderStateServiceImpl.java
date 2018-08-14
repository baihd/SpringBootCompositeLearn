package com.composite.service.impl;

import com.composite.config.PersistStateMachineHandler;
import com.composite.dao.OrderRepo;
import com.composite.entity.Order;
import com.composite.pojo.OrderStatusChangeEvent;
import com.composite.service.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;

@Service
public class OrderStateServiceImpl implements OrderStateService {

    @Autowired
    private PersistStateMachineHandler persistStateMachineHandler;

    @Autowired
    private OrderRepo orderRepo;


    public String listDbEntries() {
        List<Order> orders = orderRepo.findAll();
        StringJoiner sj = new StringJoiner(",");
        for (Order order : orders) {
            sj.add(order.toString());
        }
        return sj.toString();
    }

    public boolean change(int order, OrderStatusChangeEvent event) {
        Order o = orderRepo.findByOrderId(order);
        return persistStateMachineHandler.handleEventWithState(MessageBuilder.withPayload(event).setHeader("order", order).build(), o.getStatus());
    }

}
