package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;

//@Controller
public class MealRestController extends AbstractMealController {

    @Autowired
    private AbstractMealController controller;

    /*@Autowired
    public MealRestController(MealService service) {
        super(service);
    }*/
}