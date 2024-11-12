package com.example.jarovnya.dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishServiceTest {

    @Mock
    private DishRepo dishRepo;

    @InjectMocks
    private DishService dishService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllActualDishes() {
        List<Dish> expectedDishes = new ArrayList<>();
        when(dishRepo.getAllActualDishes()).thenReturn(expectedDishes);

        List<Dish> actualDishes = dishService.getAllActualDishes();

        assertSame(expectedDishes, actualDishes);
        verify(dishRepo).getAllActualDishes();
    }

    @Test
    void testGetCarouselDishes() {
        Dish dish1 = new Dish();
        Dish dish2 = new Dish();
        when(dishRepo.getReferenceById(1L)).thenReturn(dish1);
        when(dishRepo.getReferenceById(2L)).thenReturn(dish2);

        Dish[] dishes = dishService.getCarouselDishes();

        assertNotNull(dishes);
        assertEquals(2, dishes.length);
        assertSame(dish1, dishes[0]);
        assertSame(dish2, dishes[1]);
    }

    @Test
    void testExistsByName() {
        String name = "Pasta";
        when(dishRepo.existsByName(name)).thenReturn(true);

        boolean exists = dishService.existsByName(name);

        assertTrue(exists);
        verify(dishRepo).existsByName(name);
    }

    @Test
    void testDisableByName() {
        String name = "Pasta";
        dishService.disableByName(name);
        verify(dishRepo).disableByName(name);
    }

    @Test
    void testEnable() {
        String name = "Pasta";
        dishService.enable(name);
        verify(dishRepo).enableByName(name);
    }
}