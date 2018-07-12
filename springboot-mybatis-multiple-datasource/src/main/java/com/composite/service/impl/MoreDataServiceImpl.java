package com.composite.service.impl;

import com.composite.config.DS;
import com.composite.dao.MoreDataDao;
import com.composite.entity.User;
import com.composite.service.MoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class MoreDataServiceImpl implements MoreDataService {

    @Autowired
    private MoreDataDao moreDataDao;

    //使用数据源1查询
    @DS("datasource1")
    @Override
    public List<Map> getAllUser1() {
        return moreDataDao.getAllUser();
    }

    //使用数据源2查询
    @DS("datasource2")
    @Override
    public List<Map> getAllUser2() {
        return moreDataDao.getAllUser();
    }

    //使用数据源1插入数据
    @DS("datasource1")
    @Override
    @Transactional
    public Long addUserGetID1(User user) {
        Long id = moreDataDao.addUserGetID(user);
        return id;
    }

    //使用数据源1插入数据
    @DS("datasource2")
    @Override
    public Long addUserGetID2(User user) {
        return moreDataDao.addUserGetID(user);

    }

    //使用数据源1插入数据
    @DS("datasource1")
    @Override
    public void addUser1(String name) {
        moreDataDao.addUser(name);

    }

    //使用数据源2插入数据
    @DS("datasource2")
    @Override
    public void addUser2(String name) {
        moreDataDao.addUser(name);
    }
}
