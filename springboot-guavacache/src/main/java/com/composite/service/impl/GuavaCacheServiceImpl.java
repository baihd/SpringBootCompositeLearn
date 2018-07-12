package com.composite.service.impl;

import com.composite.service.GuavaCacheService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service("guava.cacheService")
public class GuavaCacheServiceImpl implements GuavaCacheService {

    @CachePut(value = "guavaCache")
    @Override
    public long save() {
        long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        System.out.println("进行缓存：" + timestamp);
        return timestamp;

    }

    @CacheEvict(value = "guavaCache")
    @Override
    public void delete() {
        System.out.println("删除缓存");
    }

    @Cacheable(value = "guavaCache")
    @Override
    public long getByCache() {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Timestamp(System.currentTimeMillis()).getTime();
    }
}
