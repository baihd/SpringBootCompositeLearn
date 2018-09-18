package com.composite.oauth2server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private RedisTokenStore redisTokenStore;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/removeToken", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> removeToken(@RequestParam(value = "clientId") String clientId,
                                           @RequestParam(value = "userName") String userName,
                                           @RequestParam(value = "accessToken") String accessToken) {
        Map<String, Object> resultMap = new HashMap<>();
        Collection<OAuth2AccessToken> oAuth2AccessTokens = redisTokenStore.findTokensByClientIdAndUserName(clientId, userName);
        if (oAuth2AccessTokens != null && oAuth2AccessTokens.size() != 0) {
            Iterator<OAuth2AccessToken> it = oAuth2AccessTokens.iterator();
            while (it.hasNext()) {
                OAuth2AccessToken oAuth2AccessToken = it.next();
                String tokenValue = oAuth2AccessToken.getValue();
                OAuth2RefreshToken refreshToken = oAuth2AccessToken.getRefreshToken();
                if (tokenValue.equals(accessToken)) {
                    redisTokenStore.removeAccessToken(accessToken);
                    redisTokenStore.removeRefreshToken(refreshToken);
                }
            }
            resultMap.put("resultMessage", "remove token success");
            return resultMap;
        } else {
            resultMap.put("resultMessage", "token is empty");
            return resultMap;
        }
    }

}
