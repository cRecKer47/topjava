package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static String LIST_USER = "/meals.jsp";

    private MealService mealService = new MealServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("create user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to users");
        String action = request.getParameter("action");

        /*if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealService.getMealById(mealId);
            mealService.delete(meal);
            request.setAttribute("meals", mealService.allMeals());
        } else if (action.equalsIgnoreCase("edit")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealService.getMealById(mealId);
            request.setAttribute("user", meal);
        } else if (action.equalsIgnoreCase("meals")){
            request.setAttribute("meals", mealService.allMeals());
        } else {
        }*/

        request.setAttribute("meals", mealService.allMeals());
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        //response.sendRedirect("meals.jsp");
    }


}
