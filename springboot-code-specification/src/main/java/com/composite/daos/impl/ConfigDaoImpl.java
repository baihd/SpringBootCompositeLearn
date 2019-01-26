package com.composite.daos.impl;

import com.composite.beans.Config;
import com.composite.daos.ConfigDao;
import com.composite.utils.CheckUtil;
import com.composite.utils.UserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ConfigDaoImpl implements ConfigDao {

    private final ConcurrentSkipListMap<Long, Config> configs = new ConcurrentSkipListMap<Long, Config>();

    private static final AtomicLong idSequence = new AtomicLong(1000L);

    @PostConstruct
    public void init() {

    }

    @Override
    public Collection<Config> getAll() {
        return configs.values();
    }

    @Override
    public long add(Config config) {
        //检查名称是否重复
        CheckUtil.check(null == getByName(config.getName()), "name.repeat");
        //创建用户
        config.setCreator(UserUtil.getUser());
        long id = idSequence.incrementAndGet();
        config.setId(id);
        configs.put(id, config);
        return id;
    }

    @Override
    public boolean delete(long id) {
        Config config = configs.get(id);
        if (config == null) {
            return false;
        }
        //判断是否可以删除
        CheckUtil.check(canDelete(config), "no.permission");
        return configs.remove(id) != null;
    }

    private Config getByName(String name) {
        Collection<Config> values = configs.values();
        for (Config config : values) {
            if (config.getName().equalsIgnoreCase(name)) {
                return config;
            }
        }
        return null;
    }

    private boolean canDelete(Config config) {
        return UserUtil.getUser().equals(config.getCreator());
    }
}
