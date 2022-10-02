package com.example.demo.DTOservice;

import com.example.demo.model.PersonalContact;
import com.example.demo.responseDTOs.SearchResponseDTO;

import java.util.List;

public interface ContactObjectConverterService {

    List<SearchResponseDTO> convertListOfContactsIntoDTOs(List<PersonalContact> contacts);

}
