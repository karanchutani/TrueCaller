package com.example.demo.service;

import com.example.demo.model.SpamResponseDTO;

public interface SpamService {

    SpamResponseDTO markNumberAsSpam(String number);
}
