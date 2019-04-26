package com.composite.cxfserver.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * dao层数据操作接口
 */
@Repository
public interface UserDao {

    Map<String,Object> getUser(@Param("name") String name);
}
