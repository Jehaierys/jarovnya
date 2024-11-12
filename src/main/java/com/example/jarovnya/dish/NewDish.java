package com.example.jarovnya.dish;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewDish {
    private String name;
    private String kind;
    private String description;
    private double price;
    private MultipartFile img;
}
