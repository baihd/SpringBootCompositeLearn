package com.composite.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    int insert(Map<String, Object> userMap);

    List<Map<String, Object>> selectUsers();
}
