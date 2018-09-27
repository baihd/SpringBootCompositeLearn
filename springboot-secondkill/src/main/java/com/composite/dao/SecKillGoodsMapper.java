package com.composite.dao;


import com.composite.entity.SecKillGoods;
import org.springframework.stereotype.Repository;

@Repository
public interface SecKillGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SecKillGoods record);

    int insertSelective(SecKillGoods record);

    SecKillGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecKillGoods record);

    int updateByPrimaryKey(SecKillGoods record);
}