package com.composite.handwritingcode.config;

import java.lang.reflect.Proxy;

public class MySqlSession {
    private Executor executor = new MyExecutor();

    private MyConfiguration myConfiguration = new MyConfiguration();

    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }

    public <T> T getMapper(Class<T> tClass) {
        //动态代理调用
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass},
                new MyMapperProxy(myConfiguration, this));
    }
}
