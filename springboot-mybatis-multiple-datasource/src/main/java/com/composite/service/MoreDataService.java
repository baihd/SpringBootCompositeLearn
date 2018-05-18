package com.composite.service;

import com.composite.entity.User;

import java.util.List;
import java.util.Map;

public interface MoreDataService {

    List<Map> getAllUser1();

    List<Map> getAllUser2();

    Long addUserGetID1(User user);

    Long addUserGetID2(User user);

    void addUser1(String name);

    void addUser2(String name);
}
