package com.composite.service.impl;

import com.composite.bo.GoodsBo;
import com.composite.dao.SecKillOrderMapper;
import com.composite.entity.OrderInfo;
import com.composite.entity.SecKillOrder;
import com.composite.entity.User;
import com.composite.service.OrderService;
import com.composite.service.SecKillGoodsService;
import com.composite.service.SecKillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("seckillOrderService")
public class SecKillOrderServiceImpl implements SecKillOrderService {

    @Autowired
    private SecKillOrderMapper secKillOrderMapper;

    @Autowired
    private SecKillGoodsService seckillGoodsService;

    @Autowired
    private OrderService orderService;

    @Override
    public SecKillOrder getSecKillOrderByUserIdGoodsId(long userId, long goodsId) {
        return secKillOrderMapper.selectByUserIdAndGoodsId(userId, goodsId);
    }

    @Transactional
    @Override
    public OrderInfo insert(User user, GoodsBo goods) {
        //秒杀商品库存减一
        seckillGoodsService.reduceStock(goods.getId());
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId((long) user.getId());
        //添加信息进订单
        long orderId = orderService.addOrder(orderInfo);
        SecKillOrder seckillOrder = new SecKillOrder();
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setOrderId(orderId);
        seckillOrder.setUserId((long) user.getId());
        //插入秒杀表
        secKillOrderMapper.insertSelective(seckillOrder);
        return orderInfo;
    }
}
