package com.compostie.service;

import com.compostie.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class GitHubLookupService {
    private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);

    private final RestTemplate restTemplate;

    public GitHubLookupService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public Future<User> findUser(String user) throws InterruptedException {
        logger.info("Looking up" + user);
        String url = String.format("http://127.0.0.1:8099/user/%s", user);
        User result = new User();
        try {
            result = restTemplate.getForObject(url, User.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Thread.sleep(1000L);
        return new AsyncResult<>(result);
    }
}
