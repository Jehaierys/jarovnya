package com.example.jarovnya.dish;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping("/popular")
    public List<Dish> getCarouselDishes() {
        return dishService.getCarouselDishes();
    }
    @GetMapping("/menu")
    public List<Dish> getMenu() {
        return dishService.getAllActualDishes();
    }
    @PostMapping("/create")
    public void createDish(@RequestBody DishDto dishDto) {
        Dish dish = Dish.builder()
                .name(dishDto.getName())
                .description(dishDto.getDescription())
                .kind(dishDto.getKind())
                .price(dishDto.getPrice())
                .isActual(false)
                .build();
        dishService.saveDish(dish);
    }
    //@PreAuthorized(hasRole("ROLE_ADMIN"))
    @PatchMapping("admin/delete")
    public ResponseEntity<?> disableDish(@RequestParam Long id) {
        dishService.disableDish(id);
        return ResponseEntity.ok().build();
    }
    //@PreAuthorized(hasRole("ROLE_ADMIN"))
    @GetMapping("/admin/dishes")
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }
}