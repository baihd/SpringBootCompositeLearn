package com.composite.utils;


import com.composite.config.ZookeeperProperties;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class ZookeeperIdGenerateUtils {
    @Autowired
    private ZookeeperProperties zookeeperProperties;

    private ZkClient client;


    private volatile boolean running = false;

    private ExecutorService cleanExecutor = null;

    //删除节点的级别
    public enum RemoveMethod {
        NONE, IMMEDIATELY, DELAY
    }


    public void start() throws Exception {
        if (running) {
            throw new Exception("server has started...");
        }
        running = true;
        init();
    }

    /**
     * 初始化服务资源
     */
    private void init() {

        client = new ZkClient(zookeeperProperties.getServer(), zookeeperProperties.getSessionTimeout(), zookeeperProperties.getConnectionTimeout(), new BytesPushThroughSerializer());
        cleanExecutor = Executors.newFixedThreadPool(10);
        try {
            client.createPersistent(zookeeperProperties.getRoot(), true);
        } catch (ZkNodeExistsException e) {
            //ignore;
        }

    }

    public void stop() throws Exception {
        if (!running) {
            throw new Exception("server has stopped...");
        }
        running = false;
        freeResource();
    }

    /**
     * 释放服务资源
     */
    private void freeResource() {
        cleanExecutor.shutdown();
        try {
            cleanExecutor.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            cleanExecutor = null;
        }
        if (client != null) {
            client.close();
            client = null;
        }
    }

    /**
     * 产生ID
     * 核心函数
     *
     * @param removeMethod 删除的方法
     * @return
     * @throws Exception
     */
    public String generateId(RemoveMethod removeMethod) throws Exception {
        checkRunning();
        final String fullNodePath = zookeeperProperties.getRoot().concat("/").concat(zookeeperProperties.getNodeName());
        //返回创建的节点的名称
        //final String ourPath = client.createPersistentSequential(fullNodePath, null);
        final String ourPath = client.createEphemeralSequential(fullNodePath, null);
        System.out.println(ourPath);
        /**
         * 在创建完节点后为了不占用太多空间，可以选择性删除模式
         */
        if (removeMethod.equals(RemoveMethod.IMMEDIATELY)) {
            client.delete(ourPath);
        } else if (removeMethod.equals(RemoveMethod.DELAY)) {
            cleanExecutor.execute(new Runnable() {
                public void run() {
                    client.delete(ourPath);
                }
            });

        }
        //node-0000000000, node-0000000001，ExtractId提取ID
        return ExtractId(ourPath);
    }


    /**
     * 检测服务是否正在运行
     *
     * @throws Exception
     */
    private void checkRunning() throws Exception {
        if (!running)
            throw new Exception("请先调用start");
    }

    private String ExtractId(String str) {
        int index = str.lastIndexOf(zookeeperProperties.getNodeName());
        if (index >= 0) {
            index += zookeeperProperties.getNodeName().length();
            return index <= str.length() ? str.substring(index) : "";
        }
        return str;
    }


}
