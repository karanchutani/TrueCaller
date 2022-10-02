package com.example.demo.repository;

import com.example.demo.model.Spam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Spam Entity.
 */

@Repository
public interface SpamRepository extends JpaRepository<Spam,Long> {
}