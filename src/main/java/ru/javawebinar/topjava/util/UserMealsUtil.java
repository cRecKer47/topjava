package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(

                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),

                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),

                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMealWithExceed> result = new ArrayList<>();
        LocalDate currentDate = null;
        int sumCalories = 0;
        ArrayList<UserMeal> list1 = new ArrayList<>();
        ArrayList<UserMeal> list2 = new ArrayList<>(mealList);
        list2.add(new UserMeal(LocalDateTime.now(), "empty", 0));
        for (UserMeal u :
                list2.stream().sorted((Comparator.comparing(UserMeal::getDateTime))).collect(Collectors.toList())) {
            if (currentDate == null)
                currentDate = u.getDateTime().toLocalDate();
            if (!u.getDescription().equals("empty") && currentDate.compareTo(u.getDateTime().toLocalDate()) == 0) {
                list1.add(u);
                sumCalories += u.getCalories();
            } else {
                if (sumCalories > caloriesPerDay) {
                    list1.stream().
                            filter((p) -> TimeUtil.isBetween(p.getDateTime().toLocalTime(), startTime, endTime)).
                            forEach((p) -> result.add(new UserMealWithExceed(p.getDateTime(), p.getDescription(), p.getCalories(), true)));
                }
                list1.clear();
                currentDate = u.getDateTime().toLocalDate();
                list1.add(u);
                sumCalories = u.getCalories();
            }

        }
        return result;
    }
}
