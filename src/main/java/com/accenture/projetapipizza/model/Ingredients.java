package com.accenture.projetapipizza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private int tomato = 10;
    private int basil =10;
    private int mozarella = 10;
}
