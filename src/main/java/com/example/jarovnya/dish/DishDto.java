package com.example.jarovnya.dish;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {
    private long id;
    private String name;
    private String kind;
    private byte[] bytes;
    private String extension;
    private String description;
    private int price;
}
