package com.example.demo.repository;

import com.example.demo.model.PersonalContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Repository for Contact Entity.
 */

@Repository
public interface PersonalContactRepository extends JpaRepository<PersonalContact,Long> {

    List<PersonalContact> findByPersonalContactNumber(String number);

    List<PersonalContact> findByPersonalContactNameStartsWith(String name);

    Collection<? extends PersonalContact> findByPersonalContactNameContaining(String name);

    List<PersonalContact> findByPersonalContactNumberAndPersonalContactName(String number, String anonomous_user);
}
