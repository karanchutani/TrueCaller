package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User entity
 */

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 25)
    private Long id;

    @Column(name = "name", length = 50, nullable = true)
    private String name;

    @Column(name = "email_address", length = 50, nullable = true)
    private String emailAddress;

    @Column(name = "password", length = 800)
    private String password;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE,
            })
    @JoinTable(name = "global_users_contacts_table",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "personal_contact_id")})
    private List<PersonalContact> personalContacts = new ArrayList<>();

    public List<PersonalContact> getPersonalContacts() {
        return personalContacts;
    }

    public void setPersonalContacts(List<PersonalContact> personalContacts) {
        this.personalContacts = personalContacts;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", personalContacts=" + personalContacts +
                '}';
    }
}