package com.example.demo.service.serviceImpl;

import com.example.demo.caching.StaticCachingData;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.model.PersonalContact;
import com.example.demo.model.User;
import com.example.demo.repository.PersonalContactRepository;
import com.example.demo.repository.UserDetailsRepository;
import com.example.demo.service.UserService;
import com.example.demo.validation.ValidateUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * User Service Impl.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private PersonalContactRepository personalContactRepository;

    /**
     * @param userName
     * @return
     */

    public User getUserInfoByUserName(String userName) {
        return userDetailsRepository.findByPhoneNumber(userName);
    }

    /**
     * @param user
     * @return
     */

    public synchronized User addUser(User user) {
        ValidateUserDetails.validateUser(user);
        if (userDetailsRepository.findByPhoneNumber(user.getPhoneNumber()) != null) {
            throw new InvalidDataException("phone number already exist please enter different one !");
        }
        List<PersonalContact> newContacts = new ArrayList<>();
        if (!StaticCachingData.cacheDataForPersonalContacts.isEmpty()) {
            for (PersonalContact contact : user.getPersonalContacts()
            ) {
                PersonalContact existingContact = StaticCachingData.cacheDataForPersonalContacts.get(contact.getPersonalContactName() + "_" + contact.getPersonalContactNumber());
                if (existingContact != null) {
                    newContacts.add(existingContact);
                } else {
                    newContacts.add(contact);
                }
            }
            user.setPersonalContacts(newContacts);
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        personalContactRepository.saveAll(user.getPersonalContacts());
        User createdUser = userDetailsRepository.save(user);
        // for updation of cache information of users.
        createdUser.getPersonalContacts().stream().forEach(personalContact -> {
            StaticCachingData.cacheDataForPersonalContacts.put(personalContact.getPersonalContactName() + "_" + personalContact.getPersonalContactNumber(), personalContact);
        });
        StaticCachingData.cacheDataForUser.put(createdUser.getPhoneNumber(), createdUser);
        return createdUser;
    }

    /**
     * @return
     */

    @Override
    public List<User> getAllUsers() {
        return userDetailsRepository.findAll();
    }

    /**
     * @return
     */

    @Override
    public String getLoggedInUsername() {
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return loggedInUser;
    }
}
