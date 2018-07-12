package com.composite.dao;

import com.composite.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User selectUser(String id);
}
