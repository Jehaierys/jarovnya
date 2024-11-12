package com.example.jarovnya.dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DishControllerTest {

    @Mock
    private DishService dishService;

    @InjectMocks
    private DishController dishController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dishController).build();
    }

    @Test
    void testGetCarouselDishes() throws Exception {
        Dish dish1 = new Dish(); // Заполни поля по необходимости
        Dish dish2 = new Dish();
        when(dishService.getCarouselDishes()).thenReturn(new Dish[]{dish1, dish2});

        mockMvc.perform(get("/popular"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        verify(dishService).getCarouselDishes();
    }

    @Test
    void testGetMenu() throws Exception {
        List<Dish> dishes = Collections.singletonList(new Dish());
        when(dishService.getAllActualDishes()).thenReturn(dishes);

        mockMvc.perform(get("/menu"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));

        verify(dishService).getAllActualDishes();
    }

    @Test
    void testDisableDish() throws Exception {
        String dishName = "Pasta";

        mockMvc.perform(patch("/admin/disable-dish")
                        .param("name", dishName))
                .andExpect(status().isOk());

        verify(dishService).disableByName(dishName);
    }

    @Test
    void testEnableDish() throws Exception {
        String dishName = "Pasta";

        mockMvc.perform(patch("/admin/enable-dish")
                        .param("name", dishName))
                .andExpect(status().isOk());

        verify(dishService).enable(dishName);
    }


}
