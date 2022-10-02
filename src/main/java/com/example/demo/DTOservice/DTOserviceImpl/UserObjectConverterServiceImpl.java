package com.example.demo.DTOservice.DTOserviceImpl;

import com.example.demo.DTOservice.UserObjectConverterService;
import com.example.demo.model.User;
import com.example.demo.responseDTOs.UserResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserObjectConverterServiceImpl implements UserObjectConverterService {

    /**
     * @param user
     * @return
     */

    @Override
    public UserResponseDTO convertUserObjectToDTO(User user) {
        UserResponseDTO userResponseDTO = null;
        if (user != null) {
            userResponseDTO = new UserResponseDTO();
            userResponseDTO.setEmailAddress(user.getEmailAddress());
            userResponseDTO.setName(user.getName());
            userResponseDTO.setPhoneNumber(user.getPhoneNumber());
        }
        return userResponseDTO;
    }

    /**
     * @param users
     * @return
     */

    @Override
    public Set<UserResponseDTO> convertListOfUsersIntoDTO(Set<User> users) {
        Set<UserResponseDTO> userDTOs = new HashSet<>();
        if (!CollectionUtils.isEmpty(users)) {
            users.stream().forEach(user -> {
                try {
                    userDTOs.add(new UserResponseDTO(user.getName(),
                            user.getPhoneNumber(), user.getEmailAddress()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return userDTOs;
    }
}