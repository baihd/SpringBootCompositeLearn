package com.composite.common;

public class Const {
    public interface RedisCacheExtime {
        int REDIS_SESSION_EXTIME = 60 * 30;//30分钟
        int GOODS_LIST = 60;//1分钟
        int GOODS_INFO = 60;//1分钟
    }
}
