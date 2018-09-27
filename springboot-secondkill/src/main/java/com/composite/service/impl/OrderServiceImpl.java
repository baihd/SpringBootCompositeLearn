package com.composite.service.impl;

import com.composite.dao.OrderInfoMapper;
import com.composite.entity.OrderInfo;
import com.composite.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public long addOrder(OrderInfo orderInfo) {
        return orderInfoMapper.insertSelective(orderInfo);
    }
}
