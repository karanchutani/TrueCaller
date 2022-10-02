package com.example.demo.responseDTOs;

/**
 * DTO for Search Entity.
 */

public class SearchResponseDTO {
private String name;
private String phoneNumber;
private boolean isSpam = false;
private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSpam() {
        return isSpam;
    }

    public void setSpam(boolean spam) {
        isSpam = spam;
    }

    public SearchResponseDTO(String name, String phoneNumber, boolean isSpam, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isSpam = isSpam;
        this.email = email;
    }

    public SearchResponseDTO(){}

    @Override
    public String toString() {
        return "SearchResponseDTO{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isSpam=" + isSpam +
                ", email='" + email + '\'' +
                '}';
    }
}
