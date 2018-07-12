package com.composite.config;

/**
 * 定义rabbitMq需要的常量
 */
public class RabbitMqEnum {

    /**
     * 定义数据交换方式
     */
    public enum Exchange {
        CONTRACT_FANOUT("CONTRACT_FANOUT", "消息分发"),
        CONTRACT_DIRECT("CONTRACT_DIRECT", "点对点"),
        CONTRACT_TOPIC("CONTRACT_TOPIC", "消息订阅");

        private String code;
        private String name;

        Exchange(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }


    /**
     * 定义队列名称
     */
    public enum QueueName {
        FANOUTQUEUE1("FANOUTQUEUE1", "fanout测试队列"),
        FANOUTQUEUE2("FANOUTQUEUE2", "fanout测试队列"),
        DIRECTQUEUE("DIRECTQUEUE", "direct测试队列"),
        TOPICQUEUE1("TOPICQUEUE1", "topic测试队列"),
        TOPICQUEUE2("TOPICQUEUE2", "topic测试队列");
        private String code;
        private String name;

        QueueName(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 定义routing_key
     **/
    public enum QueueEnum {
        DIRECTQUEUEKEY("DIRECTQUEUEKEY", "direct测试队列key"),
        TOPICQUEUEKEY1("*.TEST.*", "topic测试队列key"),
        TOPICQUEUEKEY2("lazy.#", "topic测试队列key");

        private String code;
        private String name;

        QueueEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
