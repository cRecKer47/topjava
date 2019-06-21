package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Comparator<Meal> USER_MEAL_COMPARATOR = (m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime());
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public Meal update(Meal meal) {
        return save(meal);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public boolean deleteAll(int userId) {
        return repository.remove(userId) != null;
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return repository == null ?
                Collections.emptyList() :
                repository.values().stream()
                        .sorted(USER_MEAL_COMPARATOR)
                        .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startTime, LocalDateTime endTime, int userId) {
        Objects.requireNonNull(startTime);
        Objects.requireNonNull(endTime);
        return getAll().stream()
                .filter(u -> DateTimeUtil.isBetween(u.getDateTime().toLocalTime(), startTime.toLocalTime(), endTime.toLocalTime()))
                .sorted(USER_MEAL_COMPARATOR)
                .collect(Collectors.toList());//getAll().stream()
        //.filter(m -> );
    }
}

