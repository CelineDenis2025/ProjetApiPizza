package com.accenture.projetapipizza.repository;

import com.accenture.projetapipizza.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Integer> {
}
