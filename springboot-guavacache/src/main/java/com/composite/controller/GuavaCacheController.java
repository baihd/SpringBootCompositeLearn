package com.composite.controller;

import com.composite.service.GuavaCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("guava.cacheController")
@RequestMapping(value = "/guava/cache")
public class GuavaCacheController {

    @Autowired
    private GuavaCacheService guavaCacheService;

    /**
     * 查询方法
     */
    @RequestMapping(value = "getByCache", method = RequestMethod.GET)
    public String getByCache() {
        Long startTime = System.currentTimeMillis();
        long timestamp = this.guavaCacheService.getByCache();
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时: " + (endTime - startTime));
        return timestamp + "";
    }

    /**
     * 保存方法
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void save() {
        this.guavaCacheService.save();
    }

    /**
     * 删除方法
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void delete() {
        this.guavaCacheService.delete();
    }

}
