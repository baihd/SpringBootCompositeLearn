package com.composite.oauth2server.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserDao {
    Map<String, Object> findUserByName(String userName);
}
