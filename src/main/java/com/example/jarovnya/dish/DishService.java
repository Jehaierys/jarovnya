package com.example.jarovnya.dish;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepo dishRepo;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(DishService.class);

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

    public void save(Dish dish) {
        dishRepo.save(dish);
    }

    public void disable(Long id) {
        dishRepo.deactivateDishById(id);
    }

    public List<Dish> getAbsolutelyAllDishes() {
        return dishRepo.findAll();
    }

    public void add(NewDish dish) {

        Dish newDish = Dish.builder()
                .name(dish.name())
                .kind(dish.kind())
                .description(dish.description())
                .price(dish.price())
                .extension(getFileExtension(dish.img().getOriginalFilename()))
                .isActual(false)
                .build();

        dishRepo.save(newDish);

        try {
            byte[] imageBytes = dish.img().getBytes();
            redisTemplate.opsForValue().set(dish.name(), imageBytes);
        } catch (IOException e) {
            logger.error("Error occurred trying to get image bytes", e);
        }
    }

    public static String getFileExtension(String filePath) {
        File file = new File(filePath);
        String name = file.getName();

        int lastIndexOfDot = name.lastIndexOf('.');
        if (lastIndexOfDot > 0 && lastIndexOfDot < name.length() - 1) {
            logger.info("getFileExtension returns{}", name.substring(lastIndexOfDot + 1));
            return name.substring(lastIndexOfDot + 1);
        }
        logger.warn("getFileExtension returns null");
        return null;
    }
    public boolean existsByName(String name) {
        return dishRepo.existsByName(name);
    }
}