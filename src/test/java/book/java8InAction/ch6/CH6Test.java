package book.java8InAction.ch6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import book.java8InAction.ch4.Ch4Test;
import book.java8InAction.domain.Apple;
import book.java8InAction.domain.Dish;

/**
 * Created by ahernandez on 12/20/16.
 */
public class CH6Test {

    /**
     * Comparison for collecting data in java 8 and previous
     * In this example we are collecting data from a List to a Map
     */
    @Test
    public void collectingDataOldVsNew() {
        List<Apple> apples = new ArrayList<>(Arrays.asList(
                new Apple("RED", "Mexico"),
                new Apple("RED", "USA"),
                new Apple("RED", "Canada"),
                new Apple("GREEN", "Mexico"),
                new Apple("GREEN", "Canda"),
                new Apple("Yellow", "USA"),
                new Apple("Yellow", "Mexico")
        ));

        // Old way
        Map<String, List<Apple>> applesByCountry = new HashMap<>();
        for(Apple apple : apples) {
            String country = apple.getOrigin();
            List<Apple> appleList = applesByCountry.get(country);
            if(appleList == null) {
                appleList = new ArrayList<>();
                applesByCountry.put(country, appleList);
            }
            appleList.add(apple);
        }
        System.out.println(applesByCountry);

        // Using collectors
        Map<String, List<Apple>> applesByCountryNew = apples.stream().collect(Collectors.groupingBy(Apple::getOrigin));
        System.out.println(applesByCountryNew);

        Assert.assertEquals(applesByCountry, applesByCountryNew);
    }


    /**
     * Test for reducing and summarizing stream elements to a single value
     *
     */
    @Test
    public void summarization() {
        List<Dish> menu = Ch4Test.getMenu();

        //Count
        Long totalDishes = menu.stream().collect(Collectors.counting());
        System.out.println(totalDishes);

        //Max
        Comparator<Dish> dishCaloriesComparator = Comparator.comparing(Dish::getCalories);
        Optional<Dish> mostCaloricDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        Optional<Dish> lessCaloricDish = menu.stream().collect(Collectors.minBy(dishCaloriesComparator));
        System.out.println(mostCaloricDish);
        System.out.println(lessCaloricDish);

        //Summarization
        LongSummaryStatistics longSummaryStatistics = menu.stream().collect(Collectors.summarizingLong(Dish::getCalories));
        System.out.println(longSummaryStatistics);

        //Joinning strings
        String dishes = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(dishes);
        dishes = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(dishes);
        dishes = menu.stream().map(Dish::getName).collect(Collectors.joining(", ", "[", "]"));
        System.out.println(dishes);

        //Reducing using 3 parameters
        int sumOfCalories = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(sumOfCalories);

        //Reducing using 1 parameter
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(mostCaloricDish);
    }

    @Test
    public void grouping() {
        List<Dish> menu = Ch4Test.getMenu();

        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);
    }
}
