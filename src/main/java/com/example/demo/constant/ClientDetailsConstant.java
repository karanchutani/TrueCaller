package com.example.demo.constant;

public class ClientDetailsConstant {
    public static final String CLIENT_ID = "client";
    public static final String CLIENT_SECRET = "secret";
    public static final String[] AUTHORIZED_GRANT_TYPES = {"password","authorization_code","refresh_token"};
    public static final String READ = "read";
    public static final String WRITE = "write";
    public static final String[] AUTHORITIES = {"ROLE_CLIENT","ROLE_TRUSTED_CLIENT","USER","ADMIN"};
    public static final boolean AUTO_APPROVE = true;
    public static final Integer ACCESS_TOKEN_VALIDITY = 600;
    public static final Integer REFRESH_TOKEN_VALIDITY = 600;
    public static final String SIGNING_KEY = "123";
}
