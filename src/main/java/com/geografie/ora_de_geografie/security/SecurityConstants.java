package com.geografie.ora_de_geografie.security;

import com.geografie.ora_de_geografie.SpringApplicationContext;

public class SecurityConstants {

    public static final long EXPIRATION_TIME = 86400000; // 1 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    public static final String EMAIL_CONFIRMATION_URL = "/users/confirm-account";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}
