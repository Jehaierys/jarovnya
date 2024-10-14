package com.example.jarovnya.dish;

import org.springframework.web.multipart.MultipartFile;

public record NewDish(String name, String kind, String description, double price, MultipartFile img) {
}