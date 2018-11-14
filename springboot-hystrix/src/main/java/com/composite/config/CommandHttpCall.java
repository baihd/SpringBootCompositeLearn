package com.composite.config;

import com.netflix.hystrix.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandHttpCall extends HystrixCommand<String> {

    private static final Logger logger = LoggerFactory.getLogger(CommandHttpCall.class);

    private final String url;

    public CommandHttpCall(String url) {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hystrix.command.http"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("hystrix.command.http"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("hystrix.command.http"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        //熔断器在整个统计时间内是否开启的阀值。10秒钟内至少请求20次，熔断器才发挥起作用
                                        .withCircuitBreakerRequestVolumeThreshold(2)
                                        //熔断器默认工作时间
                                        .withCircuitBreakerSleepWindowInMilliseconds(60 * 1000)
                                        //是否启用降级处理，如果启用了，则在超时或异常时调用getFallback进行降级处理，默认开启
                                        .withFallbackEnabled(true)
                                        //当隔离策略为THREAD时，当执行线程执行超时时，是否进行中断处理，默认为true
                                        .withExecutionIsolationThreadInterruptOnTimeout(true)
                                        //超时时间
                                        .withExecutionTimeoutInMilliseconds(5000)));
        this.url = url;
    }


    @Override
    protected String run() throws Exception {
        logger.info("Execution of Command: url={}", url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
            String total = "";
            String line = bufferedReader.readLine();
            while (line != null) {
                total += line;
                line = bufferedReader.readLine();
            }
            return total;
        }
    }

    @Override
    protected String getFallback() {
        return "failBackFor" + url;
    }
}
