package com.example.jarovnya.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransRepo extends JpaRepository<Transaction, Long> {

}