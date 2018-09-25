package com.composite.oauth2client.service.impl;

import com.composite.oauth2client.entity.AccessToken;
import com.composite.oauth2client.entity.CheckToken;
import com.composite.oauth2client.service.OAuth2Service;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    private final static String CLIENT_ID = "client";

    private final static String CLIENT_SECRET = "secret";

    private final static String TOKEN_REQUEST_URI = "http://127.0.0.1:8081/oauth2/oauth/token";

    private final static String CHECK_TOKEN_REQUEST_URI = "http://127.0.0.1:8081/oauth2/oauth/check_token";

    @Override
    public AccessToken getAccessToken(String code) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("authorization", getBasicAuthHeader());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client-id", CLIENT_ID);
        map.add("client-secret", CLIENT_SECRET);
        map.add("scope", "all");
        map.add("redirect_uri", "http://127.0.0.1:8070/ui/redirect");
        map.add("code", code);
        map.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<OAuth2AccessToken> resp = rest.postForEntity(TOKEN_REQUEST_URI, entity, OAuth2AccessToken.class);
        if (!resp.getStatusCode().equals(HttpStatus.OK)) {
            throw new RuntimeException(resp.toString());
        }
        OAuth2AccessToken oAuth2AccessToken = resp.getBody();
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(oAuth2AccessToken.getValue());
        accessToken.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
        accessToken.setTokenType(oAuth2AccessToken.getTokenType());
        accessToken.setExpiresIn(oAuth2AccessToken.getExpiration().toString());
        accessToken.setScope(oAuth2AccessToken.getScope().toString());
        return accessToken;
    }

    @Override
    public CheckToken getCheckToken(String accessToken) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("authorization", getBasicAuthHeader());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("token", accessToken);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<Object> resp = rest.postForEntity(CHECK_TOKEN_REQUEST_URI, entity, Object.class);
        if (!resp.getStatusCode().equals(HttpStatus.OK)) {
            throw new RuntimeException(resp.toString());
        }
        Map<String,Object> checkTokenMap = (Map<String, Object>) resp.getBody();
        CheckToken checkToken = new CheckToken();
        checkToken.setUserName(checkTokenMap.get("user_name").toString());
        checkToken.setClientId(checkTokenMap.get("client_id").toString());
        checkToken.setScope(checkTokenMap.get("scope").toString());
        checkToken.setAuthorities((List<String>) checkTokenMap.get("authorities"));
        return checkToken;
    }

    private String getBasicAuthHeader() {
        String auth = CLIENT_ID + ":" + CLIENT_SECRET;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}
