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
    private Active active;

    private double price;

    public Pizza(String name, Map<Size, Double> size, Map<String, Integer> listIngedient, Active active,double price) {
        this.name = name;
        this.size = size;
        this.listIngedient = listIngedient;
        this.active = active;
        this.price = price;
    }
}
