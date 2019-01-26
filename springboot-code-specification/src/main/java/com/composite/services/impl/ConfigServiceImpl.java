package com.composite.services.impl;

import com.composite.beans.Config;
import com.composite.daos.ConfigDao;
import com.composite.services.ConfigService;
import com.composite.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ConfigServiceImpl implements ConfigService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    private final ConfigDao dao;

    public ConfigServiceImpl(ConfigDao configDao) {
        this.dao = configDao;
    }

    @Override
    public Collection<Config> getAll() {
        // 校验通过后打印重要的日志
        logger.info("getAll start ...");
        Collection<Config> data = dao.getAll();
        logger.info("getAll end, data size:{}", data.size());
        return data;
    }

    @Override
    public long add(Config config) {
        CheckUtil.notNull(config, "param.is.null");
        CheckUtil.notEmpty(config.getName(), "name.is.null");
        CheckUtil.notEmpty(config.getValue(), "value.is.null");
        //校验通过后打印重要的日志
        logger.info("add config:" + config);
        long newId = dao.add(config);
        //修改操作需要打印操作结果
        logger.info("add config success, id:{}", newId);
        return newId;
    }

    @Override
    public boolean delete(long id) {
        //参数校验
        CheckUtil.check(id > 0L, "id.error", id);
        boolean result = dao.delete(id);
        //修改操作需要打印操作结果
        logger.info("delete config success, id: {}, result: {}", id, result);
        return result;
    }

    @Override
    public boolean someOpration(long id) {
        int opType = getSomeFlag();
        //校验通过后打印重要的日志
        logger.info("someOpration, id: {}, opType: {}", id, opType);
        boolean result = false;
        if (opType == 1) {
            //做这些事情
        } else {
            //做那些事情
        }
        //修改操作需要打印操作结果
        logger.info("someOpration success, id: {}, result: {}", id, result);
        return result;
    }

    @Override
    public void update(Config config) {

    }

    private int getSomeFlag() {
        return 2;
    }
}
