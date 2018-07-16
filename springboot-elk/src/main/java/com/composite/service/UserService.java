package com.composite.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {
    String insertUser(Map<String, Object> insertMap) throws IOException;

    String deleteUser(Map<String,Object> deleteMap);

    String updateUser(Map<String,Object> updateMap) throws Exception;

    List<Map<String, Object>> searchUser(Map<String,Object> searchMap);
}
