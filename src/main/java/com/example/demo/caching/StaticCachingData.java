package com.example.demo.caching;

import com.example.demo.model.PersonalContact;
import com.example.demo.model.Spam;
import com.example.demo.model.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StaticCachingData {

    // for caching users,contacts and spams information

    public static Map<String,PersonalContact> cacheDataForPersonalContacts = null;
    public static Map<String, Spam> cacheDataForSpam = null;
    public static Map<String,User> cacheDataForUser = null;
}
