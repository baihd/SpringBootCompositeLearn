package com.composite.dubbo;

import com.composite.entity.User;

public interface DubboService {
    User findUser(String username);
}
