package com.composite.service;

import com.composite.pojo.OrderStatusChangeEvent;

public interface OrderStateService {

    String listDbEntries();

    boolean change(int order, OrderStatusChangeEvent event);

}
