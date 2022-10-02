package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Spam entity
 */

@Entity
@Table(name = "spam")
public class Spam {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "spam_by_user")
    private String spamByUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpamByUser() {
        return spamByUser;
    }

    public void setSpamByUser(String spamByUser) {
        this.spamByUser = spamByUser;
    }

    @Override
    public String toString() {
        return "Spam{" +
                "id=" + id +
                ", spamByUser='" + spamByUser + '\'' +
                '}';
    }
}
