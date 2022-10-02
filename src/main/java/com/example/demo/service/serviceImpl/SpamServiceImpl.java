package com.example.demo.service.serviceImpl;

import com.example.demo.caching.StaticCachingData;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.model.PersonalContact;
import com.example.demo.model.Spam;
import com.example.demo.model.SpamResponseDTO;
import com.example.demo.repository.PersonalContactRepository;
import com.example.demo.repository.SpamRepository;
import com.example.demo.service.SpamService;
import com.example.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Spam Service Impl.
 */

@Service
public class SpamServiceImpl implements SpamService {

    @Lazy
    @Autowired
    private PersonalContactRepository personalContactRepository;

    @Lazy
    @Autowired
    private UserService userService;

    @Lazy
    @Autowired
    private SpamRepository spamRepository;

    /**
     * @param number
     * @return
     */

    @Override
    public SpamResponseDTO markNumberAsSpam(String number) {
        if (StringUtils.isBlank(number)) {
            throw new InvalidDataException("Please enter mobile number !");
        }
        // check that if a number is spammed by any user is presented or not in database
        // if not then add that number into database and marked as spam else update as spam

        String currentUserLoggedInNumber = userService.getLoggedInUsername();
        Spam spam = StaticCachingData.cacheDataForSpam.get(currentUserLoggedInNumber);
        if (spam == null) {
            spam = new Spam();
            spam.setSpamByUser(currentUserLoggedInNumber);
        }
        List<PersonalContact> currentPersonalContacts = new ArrayList<>();
        List<Spam> spams = new ArrayList<>();
        spams.add(spam);
        List<PersonalContact> existingPersonalContacts = personalContactRepository.findByPersonalContactNumber(number);
        if (!existingPersonalContacts.isEmpty()) {
            existingPersonalContacts.stream().forEach(existingPersonalContact -> {
                existingPersonalContact.setSpam(true);
                existingPersonalContact.setSpams(spams);
            });
            spamRepository.saveAll(spams);
            currentPersonalContacts = personalContactRepository.saveAll(existingPersonalContacts);
        } else {
            List<PersonalContact> anonomousContacts = new ArrayList<>();
            PersonalContact newPersonalContact = new PersonalContact();
            newPersonalContact.setPersonalContactName("Anonomous User");
            newPersonalContact.setPersonalContactNumber(number);
            newPersonalContact.setSpam(true);
            newPersonalContact.setSpams(spams);
            anonomousContacts.add(newPersonalContact);
            spamRepository.saveAll(spams);
            currentPersonalContacts = personalContactRepository.saveAll(anonomousContacts);
        }
        // update spam cache data
        for (PersonalContact contact : currentPersonalContacts
        ) {
            contact.getSpams().stream().forEach(spam1 -> {
                StaticCachingData.cacheDataForSpam.put(spam1.getSpamByUser(), spam1);
            });
        }
        // update personal contact cache data.
        currentPersonalContacts.stream().forEach(cachePersonalCotact -> {
            StaticCachingData.cacheDataForPersonalContacts.put(cachePersonalCotact.getPersonalContactName() + "_" + cachePersonalCotact.getPersonalContactNumber(), cachePersonalCotact);
        });
        // for cache updation of user
        currentPersonalContacts.stream().forEach(cachePersonalCotact -> {
            StaticCachingData.cacheDataForUser.forEach((key, values) -> {
                values.getPersonalContacts().stream().forEach(userContact -> {
                    if (cachePersonalCotact.getPersonalContactNumber().equals(userContact.getPersonalContactNumber()) && cachePersonalCotact.getPersonalContactName().equals(userContact.getPersonalContactName())) {
                        userContact.setId(cachePersonalCotact.getId());
                        userContact.setSpams(cachePersonalCotact.getSpams());
                        userContact.setSpam(cachePersonalCotact.isSpam());
                        userContact.setPersonalContactNumber(cachePersonalCotact.getPersonalContactNumber());
                        userContact.setPersonalContactName(cachePersonalCotact.getPersonalContactName());
                    }
                });
            });
        });

        SpamResponseDTO spamResponseDTO = new SpamResponseDTO();
        spamResponseDTO.setByUser(currentUserLoggedInNumber);
        spamResponseDTO.setPersonalContact(number);
        spamResponseDTO.setMessage("Number mark as spam successfully");
        return spamResponseDTO;
    }
}
