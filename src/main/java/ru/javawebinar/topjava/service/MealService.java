package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealService {
    Meal getMealById(Integer id);
    void add(Meal meal);
    void edit(Meal meal);
    void delete(Meal meal);
    List<MealTo> allMeals();
}
