package com.composite.dao;

import com.composite.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MoreDataDao {
    //使用xml配置形式查询
    List<Map> getAllUser();

    Long addUserGetID(User user);

    void addUser(@Param("name") String name);
}
