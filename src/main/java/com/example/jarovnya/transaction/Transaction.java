package com.example.jarovnya.transaction;

import com.example.jarovnya.dish.Dish;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_seq_gen")
    @SequenceGenerator(name = "trans_seq_gen", sequenceName = "trans_seq", allocationSize = 5)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish[] dishes;
    private LocalDate date;
}