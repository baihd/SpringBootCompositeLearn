package com.composite.service;

import java.util.List;
import java.util.Map;


public interface UserService {

    int insert(Map<String, Object> userMap) throws Exception;

    List<Map<String, Object>> selectUsers();

    List<Map<String, Object>> selectUsersByName(String name);
}
