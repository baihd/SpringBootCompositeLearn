package com.composite.dao1;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface Test1Dao {
    void insert(Map<String, Object> map);
}
