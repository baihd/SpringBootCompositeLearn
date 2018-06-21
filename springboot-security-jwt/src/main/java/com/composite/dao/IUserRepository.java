package com.composite.dao;

import com.composite.entity.IUser;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository {
    IUser findByUsername(String username);

    int insert(IUser user);
}
