package com.accenture.projetapipizza.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String  name;


    @ElementCollection
    @JoinTable(name = "sizePizza" )
    private Map<Size,Double> size;

    @ElementCollection
    @JoinTable(name = "listIngredient")
    private Map<String,Integer> listIngedient;  //test
    private String active;

    private double price;
    private String sizePizza; // test

    public Pizza(String name, Map<String, Integer> listIngedient, String active,double price) {
        this.name = name;
        this.listIngedient = listIngedient;
        this.active = active;
        this.price = price;
    }
}
