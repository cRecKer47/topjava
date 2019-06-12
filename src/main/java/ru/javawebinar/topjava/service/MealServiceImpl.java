package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MealServiceImpl implements MealService {


    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, Meal> list = new HashMap<>();

    static {
        Meal meal1 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, AUTO_ID.getAndIncrement());
        list.put(meal1.getId(), meal1);
        Meal meal2 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, AUTO_ID.getAndIncrement());
        list.put(meal2.getId(), meal2);
        Meal meal3 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, AUTO_ID.getAndIncrement());
        list.put(meal3.getId(), meal3);
        Meal meal4 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, AUTO_ID.getAndIncrement());
        list.put(meal4.getId(), meal4);
        Meal meal5 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, AUTO_ID.getAndIncrement());
        list.put(meal5.getId(), meal5);
        Meal meal6 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, AUTO_ID.getAndIncrement());
        list.put(meal6.getId(), meal6);
    }

    @Override
    public Meal getMealById(Integer id) {
        return list.get(id);
    }

    @Override
    public void add(Meal meal) {
        list.put(meal.getId(), meal);
    }

    @Override
    public void edit(Meal meal) {
        list.put(meal.getId(), meal);
    }

    @Override
    public void delete(Meal meal) {
        list.remove(meal.getId());
    }

    @Override
    public List<MealTo> allMeals() {
        return MealsUtil.getFilteredWithExcessByCycle(new ArrayList<>(list.values()), LocalTime.now(), LocalTime.now(), 2000, false);
    }

    public Integer genericId() {
        return AUTO_ID.getAndIncrement();
    }
}
