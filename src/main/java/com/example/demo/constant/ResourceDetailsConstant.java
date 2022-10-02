package com.example.demo.constant;

public class ResourceDetailsConstant {
    public static final String RESOURCE_ID = "resource-server-rest-api";
    public static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    public static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    public static final String SECURED_PATTERN = "/secured/**";
}
