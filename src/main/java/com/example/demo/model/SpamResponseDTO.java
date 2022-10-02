package com.example.demo.model;

/**
 * DTO for Spam entity
 */

public class SpamResponseDTO {
    private String personalContact;
    private String byUser;
    private String message;

    public String getPersonalContact() {
        return personalContact;
    }

    public void setPersonalContact(String personalContact) {
        this.personalContact = personalContact;
    }

    public String getByUser() {
        return byUser;
    }

    public void setByUser(String byUser) {
        this.byUser = byUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SpamResponseDTO{" +
                "personalContact='" + personalContact + '\'' +
                ", byUser='" + byUser + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
