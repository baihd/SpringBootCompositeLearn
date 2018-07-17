package com.composite.service2.impl;

import com.composite.dao2.Test2Dao;
import com.composite.service2.Test2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("test2ServiceImpl")
public class Test2ServiceImpl implements Test2Service {
    @Autowired
    private Test2Dao test2Dao;

    @Override
    public void test2(Map<String, Object> map) {
        test2Dao.insert(map);
    }
}
