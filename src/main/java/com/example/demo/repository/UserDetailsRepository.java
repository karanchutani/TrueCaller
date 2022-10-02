package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  Repository for User Entity.
 */

@Repository
public interface UserDetailsRepository extends JpaRepository<User, Long> {

	public User findById(Integer id);

	public void deleteById(Integer id);

	User findByPhoneNumber(String userName);

    boolean existsByPhoneNumber(String number);
}