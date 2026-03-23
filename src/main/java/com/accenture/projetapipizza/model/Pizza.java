package com.accenture.projetapipizza.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Pizza {

    private UUID id;
    private String  name;
    private Map<Size,Double> pricePizza;
    private List<Ingredients> listIngedient;
    private Active active;

}
