package com.composite.dao2;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface Test2Dao {
    void insert(Map<String, Object> map);
}
