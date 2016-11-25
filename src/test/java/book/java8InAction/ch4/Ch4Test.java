package book.java8InAction.ch4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import book.java8InAction.domain.Dish;

/**
 * Created by ahernandez on 11/16/16.
 */
public class Ch4Test {

    /**
     * Comparing old way for iterate over collections in java 7 and java 8.
     * Using Stream Api
     * Page 101
     */
    @Test
    public void oldVsNew() {
        //##### Old Style for getting low calories dishes and sort them and get the names of low
        // calories dishes
        List<Dish> lowCaloriesDishes = new ArrayList<>();
        for(Dish dish : getMenu()) {
            if(dish.getCalories() < 400) {
                lowCaloriesDishes.add(dish);
            }
        }
        Collections.sort(lowCaloriesDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        List<String> lowCaloriesDishesNames = new ArrayList<>();
        for(Dish dish : lowCaloriesDishes) {
            lowCaloriesDishesNames.add(dish.getName());
        }
        System.out.println(lowCaloriesDishesNames);

        //##### New Style
        List<String> lowCaloriesDishesNamesNew = getMenu().stream()
                .filter((Dish dish) -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(lowCaloriesDishesNamesNew);

        Assert.assertEquals(lowCaloriesDishesNames, lowCaloriesDishesNamesNew);
    }

    /**
     * Testing behaviour for intermediate operations
     * Page 114
     */
    @Test
    public void intermediateOperationTest() {
        List<String> names = getMenu().stream()
                .filter(dish -> {
                    System.out.println("Filtering " + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("Mapping " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(names);
    }

    public static List<Dish> getMenu() {
        return Arrays.asList(
            new Dish("Pork", false, 800, Dish.Type.MEAT),
            new Dish("Beef", false, 700, Dish.Type.MEAT),
            new Dish("Chicken", false, 400, Dish.Type.MEAT),
            new Dish("French Fries", true, 350, Dish.Type.OTHER),
            new Dish("Rice", true, 350, Dish.Type.OTHER),
            new Dish("Season Fruit", true, 120, Dish.Type.OTHER),
            new Dish("Pizza", true, 550, Dish.Type.OTHER),
            new Dish("Prawns", false, 300, Dish.Type.FISH),
            new Dish("Salmon", false, 450, Dish.Type.FISH)
        );
    }
}
