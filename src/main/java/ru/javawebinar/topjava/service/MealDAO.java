package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDAO implements MealService {


    private final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private Map<Integer, Meal> mealsMap = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Meal getById(Integer id) {
        return mealsMap.get(id);
    }

    @Override
    public synchronized Meal add(Meal meal) {
        return mealsMap.put(AUTO_ID.getAndIncrement(), meal);
    }

    @Override
    public synchronized Meal edit(Meal meal) {
        return mealsMap.put(meal.getId(), meal);
    }

    @Override
    public synchronized Meal delete(Meal meal) {
        return mealsMap.remove(meal.getId());
    }

    @Override
    public List<Meal> allMeals() {
//        return MealsUtil.getFilteredWithExcessByCycle(new ArrayList<>(list.values()), LocalTime.MIN, LocalTime.MAX, 2000, false);
        return new ArrayList<>(mealsMap.values());
    }

    @Override
    public Integer getAUTO_ID() {
        return AUTO_ID.get();
    }
}
