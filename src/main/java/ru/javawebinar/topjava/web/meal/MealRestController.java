package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

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

    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll().stream().filter(m -> m.getUserID() == authUserId()).collect(Collectors.toList());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        Meal meal = service.get(id);
        if (meal.getUserID() == authUserId()){
            return meal;
        }
        return null;
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        if (meal.getUserID() == authUserId()) {
            checkNew(meal);
            return service.create(meal);
        } else {
            return meal;
        }
    }

    public void delete(int id) {
        log.info("delete {}", id);
        Meal meal = get(id);
        if (meal.getUserID() == authUserId()){
            service.delete(id);
        }
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        if (meal.getUserID() == authUserId()) {
            assureIdConsistent(meal, id);
            service.update(meal);
        }
    }
}