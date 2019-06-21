package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Service
public class MealServiceImpl implements MealService {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        log.info("get meal {} for User {}", id, authUserId());
        return repository.get(id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        log.info("delete meal {} for User {}", id, authUserId());
        repository.delete(id);
    }

    @Override
    public void deleteAll(int userId) throws NotFoundException {
        log.info("deleteAll meal for User {}", userId);
        repository.delete(userId);
    }

    @Override
    public Meal save(Meal meal) {
        log.info("save meal {} for User {}", meal, authUserId());
        return repository.save(meal);
    }

    @Override
    public void update(Meal meal) {
        repository.update(meal);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(repository.getAll());
    }

    /*@Override
    public List<Meal> getBetweenDateTimes(LocalDate startDate, LocalDate endDate, int userId) {
        return null;//repository.getBetween(startDate, endDate, userId);
    }*/

    @Override
    public List<Meal> getBetweenDateTimes(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return repository.getBetween(startDate, endDate, userId);
    }

}