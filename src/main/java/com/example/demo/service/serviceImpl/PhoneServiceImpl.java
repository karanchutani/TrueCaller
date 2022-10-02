package com.example.demo.service.serviceImpl;

import com.example.demo.model.SpamResponseDTO;
import com.example.demo.repository.PersonalContactRepository;
import com.example.demo.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Phone Service Impl
 */

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PersonalContactRepository personalContactRepository;
    @Override
    public SpamResponseDTO markNumberAsSpam(String number) {

        return null;
    }
}
