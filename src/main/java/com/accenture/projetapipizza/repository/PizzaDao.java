package com.accenture.projetapipizza.repository;

import com.accenture.projetapipizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PizzaDao extends JpaRepository<Pizza, UUID> {
}
