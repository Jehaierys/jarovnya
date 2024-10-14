package com.example.jarovnya.dish;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "dishes")
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_seq_gen")
    @SequenceGenerator(name = "dish_seq_gen", sequenceName = "dish_seq", allocationSize = 1)
    private Long id;
    @Column(unique = true)
    private String name;
    private String kind;
    private String extension;
    private String description;
    private double price;
    private boolean isActual;
}