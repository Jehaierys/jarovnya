package com.example.jarovnya.dish;

import jakarta.transaction.Status;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;
    private final ResourceLoader resourceLoader;

    private static final Logger logger = LoggerFactory.getLogger(DishController.class);

    @GetMapping("/popular")
    public List<Dish> getCarouselDishes() {
        return dishService.getCarouselDishes();
    }
    @GetMapping("/menu")
    public List<Dish> menu() {
        return dishService.getAllActualDishes();
    }
    //@PreAuthorized(hasRole("ROLE_ADMIN"))
    @PatchMapping("admin/delete")
    public ResponseEntity<?> disableDish(@RequestParam Long id) {
        dishService.disable(id);
        return ResponseEntity.ok().build();
    }
    //@PreAuthorized(hasRole("ROLE_ADMIN"))
    @GetMapping("/admin/dishes")
    public List<Dish> getAllDishes() {
        return dishService.getAbsolutelyAllDishes();
    }
    //@PreAuthorized(hasRole("ROLE_ADMIN"))
    @PostMapping("/new-dish")
    public ResponseEntity<?> addNewDish(@RequestBody NewDish newDish) {
        if (dishService.existsByName(newDish.name())) {
            return new ResponseEntity<>(newDish, HttpStatus.CONFLICT); // dish with such name already exists
        }
        dishService.add(newDish);
        return null;
    }
}