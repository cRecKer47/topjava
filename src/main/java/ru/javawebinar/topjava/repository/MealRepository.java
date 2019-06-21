package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface MealRepository {
    Meal save(Meal meal);

    Meal update(Meal meal);

    // false if not found
    boolean delete(int id);

    boolean deleteAll(int userId);

    // null if not found
    Meal get(int id);

    List<Meal> getAll();

    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
