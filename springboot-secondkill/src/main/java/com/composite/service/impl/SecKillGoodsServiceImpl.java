package com.composite.service.impl;

import com.composite.bo.GoodsBo;
import com.composite.dao.GoodsMapper;
import com.composite.service.SecKillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("secKillGoodsService")
public class SecKillGoodsServiceImpl implements SecKillGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsBo> getSecKillGoodsList() {
        return goodsMapper.selectAllGoodes();
    }

    @Override
    public GoodsBo getSecKillGoodsBoByGoodsId(long goodsId) {
        return goodsMapper.getSecKillGoodsBoByGoodsId(goodsId);
    }

    @Override
    public int reduceStock(long goodsId) {
        return goodsMapper.updateStock(goodsId);
    }
}
