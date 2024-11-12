package com.example.jarovnya.dish;

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
    public Dish[] getCarouselDishes() { // does not work
        return dishService.getCarouselDishes();
    }

    @GetMapping("/menu")
    public List<Dish> menu() {
        return dishService.getAllActualDishes();
    }

    //@PreAuthorized(hasRole("ROLE_ADMIN"))
    @PatchMapping("/admin/disable-dish")
    public ResponseEntity<?> disableDish(@RequestParam String name) {
        dishService.disableByName(name);
        return ResponseEntity.ok().build();
    }

    //@PreAuthorized(hasRole("ROLE_ADMIN"))
    @PatchMapping("/admin/enable-dish")
    public ResponseEntity<?> enableDish(@RequestParam String name) {
        dishService.enable(name);
        return ResponseEntity.ok().build();
    }

    //@PreAuthorized(hasRole("ROLE_ADMIN"))
    @GetMapping("/admin/all-dishes")
    public List<Dish> getAllDishes() {
        return dishService.getAbsolutelyAllDishes();
    }

    //@PreAuthorized(hasRole("ROLE_ADMIN"))
    @PostMapping("/admin/new-dish")
    public ResponseEntity<?> addNewDish(@RequestBody NewDish newDish) {
        logger.info("new dish tried to create");
        if (dishService.existsByName(newDish.getName())) {
            return new ResponseEntity<>(newDish, HttpStatus.CONFLICT); // dish with such name already exists
        }
        dishService.add(newDish);
        return null;
    }
}