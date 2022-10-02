package com.example.demo.service;

import com.example.demo.model.SpamResponseDTO;

public interface PhoneService {

    SpamResponseDTO markNumberAsSpam(String number);
}
