package com.composite.service.serviceImpl;

import com.composite.service.JtaTestService;
import com.composite.service1.Test1Service;
import com.composite.service2.Test2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("jtaTestServiceImpl")
public class JtaTestServiceImpl implements JtaTestService {
    @Autowired
    @Qualifier("test1ServiceImpl")
    private Test1Service test1Service;
    @Autowired
    @Qualifier("test2ServiceImpl")
    private Test2Service test2Service;

    @Override
    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = {java.lang.RuntimeException.class})
    public String test(Map<String, Object> map) {
        test1Service.test1(map);
        //int i = 10 / 0;
        test2Service.test2(map);
        return "0";
    }
}
