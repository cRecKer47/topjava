package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface MealService {
    Meal getById(Integer id);
    Meal add(Meal meal);
    Meal edit(Meal meal);
    Meal delete(Meal meal);
    List<Meal> allMeals();
    Integer getAUTO_ID();
}
