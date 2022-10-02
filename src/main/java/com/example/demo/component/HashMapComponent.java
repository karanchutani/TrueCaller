package com.example.demo.component;

import com.example.demo.caching.StaticCachingData;
import com.example.demo.model.PersonalContact;
import com.example.demo.model.Spam;
import com.example.demo.model.User;
import com.example.demo.repository.PersonalContactRepository;
import com.example.demo.repository.SpamRepository;
import com.example.demo.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class HashMapComponent {

    /**
     * This class is used for creating cache information by database
     * when application will start.
     */

    @Autowired
    private PersonalContactRepository personalContactRepository;

    @Autowired
    private SpamRepository spamRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @PostConstruct
    public void createCacheForHashMap() {
        StaticCachingData.cacheDataForPersonalContacts = new ConcurrentHashMap<>();
        List<PersonalContact> allPersonalContactsUser = (List<PersonalContact>) personalContactRepository.findAll();
        allPersonalContactsUser.stream().forEach(personalContact -> {
            StaticCachingData.cacheDataForPersonalContacts.put(personalContact.getPersonalContactName() + "_" + personalContact.getPersonalContactNumber(), personalContact);
        });
        StaticCachingData.cacheDataForSpam = new ConcurrentHashMap<>();
        List<Spam> spams = spamRepository.findAll();
        spams.stream().forEach(spam -> {
            StaticCachingData.cacheDataForSpam.put(spam.getSpamByUser(), spam);
        });
        StaticCachingData.cacheDataForUser = new ConcurrentHashMap<>();
        List<User> users = userDetailsRepository.findAll();
        users.stream().forEach(user -> {
            StaticCachingData.cacheDataForUser.put(user.getPhoneNumber(), user);
        });
    }
}
