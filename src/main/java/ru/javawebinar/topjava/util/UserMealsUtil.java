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

                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),

                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),

                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500)
        );

        List<UserMealWithExceed> filteredMealsWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredMealsWithExceeded.forEach(System.out::println);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return getForRealiese(mealList, startTime, endTime, caloriesPerDay);
    }

    public static List<UserMealWithExceed> getForRealiese(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> groupByDateCalories = new HashMap<>();
        for (UserMeal meal :
                mealList) {


            LocalDate mealData = meal.getDateTime().toLocalDate();
            groupByDateCalories.merge(mealData, meal.getCalories(), Integer::sum);
        }


        ArrayList<UserMealWithExceed> mealExceeded = new ArrayList<>();
        for (UserMeal meal:
             mealList) {
            LocalDateTime mealTime = meal.getDateTime();
            if (TimeUtil.isBetween(mealTime.toLocalTime(), startTime, endTime)) {
                mealExceeded.add(new UserMealWithExceed(mealTime, meal.getDescription(), meal.getCalories(), groupByDateCalories.get(mealTime.toLocalDate()) > caloriesPerDay));
            }
        }

        return mealExceeded;
    }

    public static List<UserMealWithExceed> getStreamRealiese(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> groupByDateCalories = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                Collectors.summingInt(UserMeal::getCalories)));

        List<UserMealWithExceed> result = mealList.stream()
                .filter((s) -> TimeUtil.isBetween(s.getDateTime().toLocalTime(), startTime, endTime))
                .map(p -> {
                    UserMealWithExceed um = new UserMealWithExceed(p.getDateTime(), p.getDescription(), p.getCalories(), false);
                    return um;
                })
                .collect(Collectors.toList());
        return mealList.stream().filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(), groupByDateCalories.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
