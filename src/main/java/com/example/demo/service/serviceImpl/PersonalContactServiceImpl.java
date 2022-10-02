package com.example.demo.service.serviceImpl;

import com.example.demo.caching.StaticCachingData;
import com.example.demo.model.PersonalContact;
import com.example.demo.model.User;
import com.example.demo.repository.PersonalContactRepository;
import com.example.demo.repository.UserDetailsRepository;
import com.example.demo.responseDTOs.SearchResponseDTO;
import com.example.demo.service.PersonalContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Personal Contact Service Impl
 */

@Service
public class PersonalContactServiceImpl implements PersonalContactService {

    @Autowired
    private PersonalContactRepository personalContactRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    /**
     * @param name
     * @return
     */

    @Override
    public List<SearchResponseDTO> findUsersWithNameContaining(String name) {

        List<SearchResponseDTO> totalSearches = new ArrayList<>();
        // for start with search query string Registered users in cache data.
        StaticCachingData.cacheDataForUser.forEach((key, value) -> {
            if (value.getName().startsWith(name)) {
                totalSearches.add(new SearchResponseDTO(value.getName(), value.getPhoneNumber(), false, value.getEmailAddress()));
            }
        });
        // for contains with search query but not start with string Registered users in cache data.
        StaticCachingData.cacheDataForUser.forEach((key, value) -> {
            if (!value.getName().startsWith(name) && value.getName().contains(name)) {
                totalSearches.add(new SearchResponseDTO(value.getName(), value.getPhoneNumber(), false, value.getEmailAddress()));
            }
        });
        // for all personalContacts start with Searches.
        StaticCachingData.cacheDataForUser.forEach((key, value) -> {
            value.getPersonalContacts().stream().forEach(personalContact -> {
                if (personalContact.getPersonalContactName().startsWith(name)) {
                    totalSearches.add(new SearchResponseDTO(personalContact.getPersonalContactName(), personalContact.getPersonalContactNumber(), personalContact.isSpam(), null));
                }
            });
        });
        // for all personalContacts Contains but not start with
        StaticCachingData.cacheDataForUser.forEach((key1, value1) -> {
            value1.getPersonalContacts().stream().forEach(personalContact -> {
                if (!personalContact.getPersonalContactName().startsWith(name) && personalContact.getPersonalContactName().contains(name)) {
                    totalSearches.add(new SearchResponseDTO(personalContact.getPersonalContactName(), personalContact.getPersonalContactNumber(), personalContact.isSpam(), null));
                }

            });
        });
        return totalSearches;
    }

    /**
     * @param number
     * @return
     */

    @Override
    public List<SearchResponseDTO> findUsersWithNameContainingNumber(String number) {
        SearchResponseDTO contact = null;
        List<SearchResponseDTO> totalSearches = new ArrayList<>();
        User registeredUser = userDetailsRepository.findByPhoneNumber(number);
        // check if registered user is present by that given number or not.
        if (registeredUser != null) {
            totalSearches.add(new SearchResponseDTO(registeredUser.getName(), registeredUser.getPhoneNumber(), false, registeredUser.getEmailAddress()));
        } else {
            StaticCachingData.cacheDataForUser.forEach((key, value) -> {
                value.getPersonalContacts().stream().forEach(personalContact -> {
                    if (personalContact.getPersonalContactNumber().equals(number)) {
                        totalSearches.add(new SearchResponseDTO(personalContact.getPersonalContactName(), personalContact.getPersonalContactNumber(), personalContact.isSpam(), null));
                    }
                });
            });
        }

        List<PersonalContact> ananomousUserContacts = personalContactRepository.findByPersonalContactNumberAndPersonalContactName(number, "Anonomous User");
        ananomousUserContacts.stream().forEach(invalidUser -> {
            totalSearches.add(new SearchResponseDTO(invalidUser.getPersonalContactName(), invalidUser.getPersonalContactNumber(), invalidUser.isSpam(), null));
        });
        return totalSearches;
    }
}
