package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Contact Entity
 */

@Entity
@Table(name = "personal_contacts")
public class PersonalContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "personal_contact_number")
    private String personalContactNumber;

    @Column(name = "personal_contact_name")
    private String personalContactName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "spam_phone_contacts",
            joinColumns = {@JoinColumn(name = "personal_contact_id")},
            inverseJoinColumns = {@JoinColumn(name = "spam_id")})
    private List<Spam> spams = new ArrayList<>();


    @Column(name = "is_spam")
    private boolean isSpam = false;

    public List<Spam> getSpams() {
        return spams;
    }

    public void setSpams(List<Spam> spams) {
        this.spams = spams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonalContactNumber() {
        return personalContactNumber;
    }

    public void setPersonalContactNumber(String personalContactNumber) {
        this.personalContactNumber = personalContactNumber;
    }

    public String getPersonalContactName() {
        return personalContactName;
    }

    public void setPersonalContactName(String personalContactName) {
        this.personalContactName = personalContactName;
    }

    public boolean isSpam() {
        return isSpam;
    }

    public void setSpam(boolean spam) {
        isSpam = spam;
    }

    @Override
    public String toString() {
        return "PersonalContact{" +
                "id=" + id +
                ", personalContactNumber='" + personalContactNumber + '\'' +
                ", personalContactName='" + personalContactName + '\'' +
                ", spams=" + spams +
                ", isSpam=" + isSpam +
                '}';
    }
}
