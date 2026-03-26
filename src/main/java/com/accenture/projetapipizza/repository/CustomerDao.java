package com.accenture.projetapipizza.repository;

import com.accenture.projetapipizza.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

}
