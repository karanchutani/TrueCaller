package com.example.demo.validation;

import com.example.demo.exception.InvalidDataException;
import com.example.demo.model.User;
import org.apache.commons.lang3.StringUtils;

/**
 * Custom validations of user
 */
public class ValidateUserDetails {

    public static void validateUser(User user) {

        if (StringUtils.isBlank(user.getName())) {
            throw new InvalidDataException("Please enter your name !");
        }

        if (StringUtils.isBlank(user.getPhoneNumber())) {
            throw new InvalidDataException("Phone number must not be null");
        }
    }
}