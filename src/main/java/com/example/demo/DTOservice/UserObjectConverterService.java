package com.example.demo.DTOservice;

import com.example.demo.model.User;
import com.example.demo.responseDTOs.UserResponseDTO;

import java.util.List;
import java.util.Set;

public interface UserObjectConverterService {

    public UserResponseDTO convertUserObjectToDTO(User user);

    Set<UserResponseDTO> convertListOfUsersIntoDTO(Set<User> users);
}
