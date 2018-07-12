package com.composite.utils;

import java.io.IOException;

public class BuildIdFactory {
    /**
     * 订单表序列
     */
    private final static String TAB_ORDER = "order";

    private static IdGenerator idGenerator;
    private static BuildIdFactory instance;

    private BuildIdFactory() {
    }

    public static BuildIdFactory getInstance() {
        if(idGenerator == null) {
            try {
                idGenerator = IdGenerator.LoadIdGeneratorConfig.loadConfig.buildIdGenerator();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance == null ? new BuildIdFactory() : instance;
    }

    public Long buildFactoryOrderId() {
        return idGenerator.next(TAB_ORDER);
    }
}
