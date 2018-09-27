package com.composite.service;


import com.composite.bo.GoodsBo;

import java.util.List;

public interface SecKillGoodsService {

    List<GoodsBo> getSecKillGoodsList();

    GoodsBo getSecKillGoodsBoByGoodsId(long goodsId);

    int reduceStock(long goodsId);
}
