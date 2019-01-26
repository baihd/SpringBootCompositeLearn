package com.composite.controllers;

import com.composite.annotations.Log;
import com.composite.beans.Config;
import com.composite.beans.ResultBean;
import com.composite.consts.LogConst;
import com.composite.services.ConfigService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("/config")
@RestController
public class ConfigController {
    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping("/all")
    @Log(action = LogConst.ACTION_QUERY, itemType = LogConst.ITEM_TYPE_CONFIG)
    public ResultBean<Collection<Config>> getAll() {
        return new ResultBean<>(configService.getAll());
    }

    @PostMapping("/add")
    @Log(action = LogConst.ACTION_ADD, itemType = LogConst.ITEM_TYPE_CONFIG, itemId = "#config.name")
    public ResultBean<Long> add(Config config) {
        return new ResultBean<>(configService.add(config));
    }


    @PostMapping("/delete")
    @Log(action = LogConst.ACTION_DELETE, itemId = LogConst.ITEM_TYPE_CONFIG, itemType = "#id")
    public ResultBean<Boolean> delete(long id) {
        return new ResultBean<>(configService.delete(id));
    }

    @PostMapping("/update")
    @Log(action = LogConst.ACTION_UPDATE, itemType = LogConst.ITEM_TYPE_CONFIG, itemId = "#config.name", param = "#config")
    public ResultBean<Boolean> update(Config config) {
        configService.update(config);
        return new ResultBean<>(true);
    }
}
