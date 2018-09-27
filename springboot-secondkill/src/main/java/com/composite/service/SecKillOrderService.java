package com.composite.service;

import com.composite.bo.GoodsBo;
import com.composite.entity.OrderInfo;
import com.composite.entity.SecKillOrder;
import com.composite.entity.User;

public interface SecKillOrderService {

    SecKillOrder getSecKillOrderByUserIdGoodsId(long userId, long goodsId);

    OrderInfo insert(User user, GoodsBo goodsBo);

}
