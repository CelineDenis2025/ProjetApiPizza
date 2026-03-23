package com.accenture.projetapipizza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Pizza {

    private UUID id;
    private String  name;
    @Enumerated(EnumType.STRING)
    private Size Size;
    private int tomate;
    private int mozarelle;
    private int basilic;

}
