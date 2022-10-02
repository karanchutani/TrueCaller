package com.example.demo.responseDTOs;

/**
 * DTO for User Response.
 */

public class UserResponseDTO {
    private String name;
    private String emailAddress;
    private String phoneNumber;

    public UserResponseDTO(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber= phoneNumber;
    }

    public UserResponseDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
