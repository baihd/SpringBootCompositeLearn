package com.composite.oauth2client.service;

import com.composite.oauth2client.entity.AccessToken;
import com.composite.oauth2client.entity.CheckToken;

public interface OAuth2Service {

    AccessToken getAccessToken(String code);

    CheckToken getCheckToken(String accessToken);

}
