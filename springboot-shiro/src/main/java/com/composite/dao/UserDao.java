package com.composite.dao;

import com.composite.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    List<User> getByMap(Map<String, Object> map);

    User getById(int id);

    int create(User user);

    int update(User user);

    int delete(int id);

    User getByUserName(String username);

}
