package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealDAO;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static String LIST_USER = "/meals.jsp";

    private MealService mealService = new MealDAO();

    public MealServlet() {
        mealService.add(new Meal(mealService.getAUTO_ID(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        mealService.add(new Meal(mealService.getAUTO_ID(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        mealService.add(new Meal(mealService.getAUTO_ID(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        mealService.add(new Meal(mealService.getAUTO_ID(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        mealService.add(new Meal(mealService.getAUTO_ID(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        mealService.add(new Meal(mealService.getAUTO_ID(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("create user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to users");
        String action = request.getParameter("action");
        log.debug(action);

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

        List<MealTo> mealsList = MealsUtil.getFilteredWithExcessByCycle(mealService.allMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("meals", mealsList);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        //response.sendRedirect("meals.jsp");
    }


}
