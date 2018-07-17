package com.composite.service1.impl;

import com.composite.dao1.Test1Dao;
import com.composite.service1.Test1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("test1ServiceImpl")
public class Test1ServiceImpl implements Test1Service {
    @Autowired
    private Test1Dao test1Dao;

    @Override
    public void test1(Map<String, Object> map) {
        test1Dao.insert(map);
    }
}
