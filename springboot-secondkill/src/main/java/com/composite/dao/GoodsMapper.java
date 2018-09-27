package com.composite.dao;


import com.composite.bo.GoodsBo;
import com.composite.entity.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    List<GoodsBo> selectAllGoodes();

    GoodsBo getSecKillGoodsBoByGoodsId(long goodsId);

    int updateStock(long goodsId);
}