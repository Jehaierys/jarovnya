package com.example.jarovnya.dish;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepo dishRepo;

    public List<Dish> getAllActualDishes() {
        ArrayList<Dish> dishes = new ArrayList<>();
        for (long i = 0L; i <10; ++i) {
            dishes.add(dishRepo.getReferenceById(i)); //(Long)(Math.random() * i)
        }
        return dishes;
    }

    public List<Dish> getCarouselDishes() {
        ArrayList<Dish> dishes = new ArrayList<>();
        for (long i = 0L; i < 5; ++i) {
            dishes.add(dishRepo.getReferenceById(i)); //(Long)(Math.random() * i)
        }
        return dishes;
    }

    public void saveDish(Dish dish) {
        dishRepo.save(dish);
    }

    public void disableDish(Long id) {
        dishRepo.deactivateDishById(id);
    }

    public List<Dish> getAllDishes() {
        return dishRepo.findAll();
    }
}