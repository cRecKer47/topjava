package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id) {
        int userId = authUserId();
        log.info("get meal {} for User {}", id, userId);
        /*Meal meal = service.get(id);
        if (meal.getUserID() == authUserId()){
            return meal;
        }
        return null;*/
        return service.get(id);
    }

    public void delete(int id) {
        log.info("delete meal {} for User {}", id, authUserId());
        /*Meal meal = get(id);
        if (meal.getUserID() == authUserId()){
            service.delete(id);
        }*/
        service.delete(id);
    }

    public List<MealTo> getAll() {
        log.info("getAll for user {}", authUserId());
        return null;//MealsUtil.getFilteredWithExcess(service.getAll(), SecurityUtil.authUserCaloriesPerDay(), LocalTime.MIN, LocalTime.MAX);//service.getAll().stream().filter(m -> m.getUserID() == authUserId()).collect(Collectors.toList());
    }

    public void deleteAll() {
        int userId = authUserId();
        log.info("deleteAll meal for User {}", userId);
        service.deleteAll(userId);
    }

    public void update(Meal meal) {
        log.info("update meal {} with User {}", meal, authUserId());/*
        if (meal.getUserID() == authUserId()) {
            assureIdConsistent(meal, authUserId());
            service.update(meal);
        }*/
        service.update(meal);
    }

    public Meal create(Meal meal) {
        log.info("create meal {} for User {}", meal, authUserId());/*
        if (meal.getUserID() == authUserId()) {
            checkNew(meal);
            return service.create(meal);
        } else {
            return meal;
        }*/
        return service.save(meal);
    }

    public List<MealTo> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        log.info("getBetween {} and {} for User {}", startDate, endDate, authUserId());
        return MealsUtil.getFilteredWithExcess(service.getBetweenDates(startDate, endDate, authUserId()), SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
    }
}