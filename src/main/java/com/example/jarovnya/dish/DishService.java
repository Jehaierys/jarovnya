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

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepo dishRepo;
    private final RedisTemplate<String, byte[]> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(DishService.class);

    public List<Dish> getAllActualDishes() { return dishRepo.getAllActualDishes(); }

    public Dish[] getCarouselDishes() {
        Dish[] dishes = new Dish[5];
        for (long i = 1L; i < 5; ++i) {
            dishes[(int)(i - 1)] = dishRepo.getReferenceById(i);
        }
        return dishes;
    }

    public void disableByName(String name) { dishRepo.disableByName(name); }

    public List<Dish> getAbsolutelyAllDishes() { return dishRepo.findAll(); }

    public void add(NewDish dish) {
        final String extension = getFileExtension(dish.getImg().getOriginalFilename());
        logger.info("Img extension: {}", extension);
        Dish newDish = Dish.builder()
                .name(dish.getName())
                .kind(dish.getKind())
                .description(dish.getDescription())
                .price(dish.getPrice())
                .extension(extension)
                .isActual(false)
                .build();

        dishRepo.save(newDish);

        try {
            byte[] imageBytes = dish.getImg().getBytes();
            redisTemplate.opsForValue().set(dish.getName(), imageBytes);
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

    public boolean existsByName(String name) { return dishRepo.existsByName(name); }

    public void enable(String name) { dishRepo.enableByName(name); }
}