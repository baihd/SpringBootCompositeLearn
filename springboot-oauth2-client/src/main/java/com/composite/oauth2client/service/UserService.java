package com.composite.oauth2client.service;

import java.util.Map;

public interface UserService {

    Map<String, Object> findUserByName(String userName);

    Map<String, Object> findUserByOtherUserName(String userName);

    int saveUser(Map<String, Object> userMap);
}
