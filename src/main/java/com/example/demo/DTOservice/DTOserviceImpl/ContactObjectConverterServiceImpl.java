package com.example.demo.DTOservice.DTOserviceImpl;

import com.example.demo.DTOservice.ContactObjectConverterService;
import com.example.demo.model.PersonalContact;
import com.example.demo.responseDTOs.SearchResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactObjectConverterServiceImpl implements ContactObjectConverterService {

    /**
     * @param contacts
     * @return
     */

    @Override
    public List<SearchResponseDTO> convertListOfContactsIntoDTOs(List<PersonalContact> contacts) {
        return null;
    }
}
