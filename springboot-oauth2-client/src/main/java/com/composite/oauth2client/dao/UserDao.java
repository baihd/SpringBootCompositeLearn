package com.composite.oauth2client.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserDao {
    Map<String, Object> findUserByName(String userName);

    Map<String, Object> findUserByOtherUserName(String userName);

    int saveUser(Map<String, Object> userMap);

}
