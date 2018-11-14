package com.composite.controller;

import com.composite.config.CommandHelloWorld;
import com.composite.config.CommandHttpCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @RequestMapping("/")
    public String hello() {
        return new CommandHelloWorld("hystrix").execute();
    }

    @RequestMapping("/baidu")
    public String getGoogle() {
        return new CommandHttpCall("http://www.baidu.com").execute();
    }

    @RequestMapping("/product")
    public String getProduct() throws InterruptedException {
        Thread.sleep(1000);
        return new CommandHelloWorld("this is content for product").execute();
    }

    @RequestMapping("/order")
    public String getOrder() throws InterruptedException {
        Thread.sleep(1000);
        return new CommandHelloWorld("this is content for order").execute();
    }

    @RequestMapping("/cart")
    public String getCart() throws InterruptedException {
        Thread.sleep(1000);
        return new CommandHelloWorld("this is content for cart").execute();
    }

    @RequestMapping("/observe")
    public String getObserve() throws InterruptedException {
        Observable<String> productCall = new CommandHttpCall("http://localhost:8091/product").observe();
        Observable<String> orderCall = new CommandHttpCall("http://localhost:8091/order").observe();
        Observable<String> cartCall = new CommandHttpCall("http://localhost:8091/cart").observe();

        List<Observable<String>> result = new ArrayList<>();
        result.add(productCall);
        result.add(orderCall);
        Observable.merge(result).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("product&order call complete");
                cartCall.subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        logger.info("cart call complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        logger.error(e.getMessage());
                    }

                    @Override
                    public void onNext(String v) {
                        logger.info("onNext:" + v);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                logger.error(e.getMessage());
            }

            @Override
            public void onNext(String v) {
                logger.info("onNext:" + v);
            }

        });

        return new CommandHelloWorld("this is content for observe").execute();
    }

    @RequestMapping("/future")
    public String getFuture() throws InterruptedException {
        Future<String> productSyncCall = new CommandHttpCall("http://localhost:8091/product").queue();
        try {
            String product = productSyncCall.get();
            logger.info("sync get product" + product);
            Future<String> orderSyncCall = new CommandHttpCall("http://localhost:8091/order").queue();
            Future<String> cartSyncCall = new CommandHttpCall("http://localhost:8091/cart").queue();
            logger.info("sync get order" + orderSyncCall.get());
            logger.info("sync get cart" + cartSyncCall.get());
        } catch (ExecutionException e) {
            logger.error(e.getMessage());
        }
        return new CommandHelloWorld("this is content for future").execute();
    }

}
