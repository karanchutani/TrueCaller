package com.example.demo.service;

import com.example.demo.model.PersonalContact;
import com.example.demo.responseDTOs.SearchResponseDTO;

import java.util.List;

public interface PersonalContactService {
    List<SearchResponseDTO> findUsersWithNameContaining(String name);
    List<SearchResponseDTO> findUsersWithNameContainingNumber(String number);
}
