package com.composite.service;

import com.composite.entity.User;
import com.composite.param.LoginParam;
import com.composite.result.Result;

public interface UserService {
    Result<User> login(LoginParam loginParam);
}
