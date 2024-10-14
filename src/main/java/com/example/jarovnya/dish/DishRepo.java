package com.example.jarovnya.dish;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepo extends JpaRepository<Dish, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Dish d SET d.isActual = false WHERE d.id = :id")
    void deactivateDishById(long id);

    @Query("UPDATE Dish d SET d.isActual = false WHERE d.id = :id")
    void putInStopList(long id);

    @Query("SELECT d FROM Dish d WHERE d.isActual = true")
    List<Dish> getAllActualDishes();

    boolean existsByName(String name);
}