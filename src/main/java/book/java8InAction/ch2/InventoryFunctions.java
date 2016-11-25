package book.java8InAction.ch2;

import java.util.ArrayList;
import java.util.List;

import book.java8InAction.domain.Apple;

/**
 * Created by ahernandez on 10/28/16.
 */
public class InventoryFunctions {

    /**
     * First attempting for filtering apples
     * Page 38
     *
     * @param inventory
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * Second attempting for filtering apples by color
     * Page 39
     *
     * @param inventory
     * @param color
     * @return
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * Second attempting for filtering apples by weight
     * Page 39
     *
     * @param inventory
     * @param weight
     * @return
     */
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * Third attempting for filtering apples by color or weight based on flag
     * Page 40
     *
     * @param inventory
     * @param color
     * @param weight
     * @param flag if true filter by color, otherwise by weight
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if((flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight)) {
                result .add(apple);
            }
        }
        return result;
    }

    /**
     * Fourth attempt for filtering apples using the strategy pattern
     * Page 42
     *
     * @param inventory
     * @param p
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /*
     Fifth attempt using anonymous class page 49
     Sixth attempt using lambda expression page 51
    */

    /**
     * Seventh attempt for filtering. Implementing a more generic filter, ot only for apples
     * Page 51
     *
     * @param inventory
     * @param p
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> inventory, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T t : inventory) {
            if(p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }
}
