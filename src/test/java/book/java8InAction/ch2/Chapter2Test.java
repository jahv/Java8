package book.java8InAction.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import book.java8InAction.domain.Apple;
import book.java8InAction.ch2.quiz2.ColorApplePrinter;
import book.java8InAction.ch2.quiz2.QuizFunctions;
import book.java8InAction.ch2.quiz2.WeightApplePrinter;

/**
 * Created by ahernandez on 10/28/16.
 */
public class Chapter2Test {

    private static  final int RED_APPLES = 1;
    private static  final int GREEN_APPLES = 1;
    private static  final int HEAVIER_THAN_2_APPLES = 2;
    private static  final int RED_AND_HEAVIER_THAN_2_APPLES = 1;
    private static  final int HEAVIER_THAN_4_APPLES = 1;

    /**
     * Test for first attempting of filtering the apples
     * Page 38
     */
    @Test
    public void firstFilter() {
        List<Apple> applesFiltered = InventoryFunctions.filterGreenApples(apples());
        System.out.println(applesFiltered);
        Assert.assertEquals(applesFiltered.size(), GREEN_APPLES);
    }

    /**
     * Test for second attempting of filtering the apples by color
     * Page 39
     */
    @Test
    public void secondFilterByColor() {
        List<Apple> redApples = InventoryFunctions.filterApplesByColor(apples(), "red");
        System.out.println(redApples);
        Assert.assertEquals(redApples.size(), RED_APPLES);

        List<Apple> greenApples = InventoryFunctions.filterApplesByColor(apples(), "green");
        System.out.println(greenApples);
        Assert.assertEquals(greenApples.size(), GREEN_APPLES);
    }

    /**
     * Test for second attempting of filtering the apples by weight
     * Page 39
     */
    @Test
    public void secondFilterByWeight() {
        List<Apple> moreThan4Apples = InventoryFunctions.filterApplesByWeight(apples(), 4);
        System.out.println(moreThan4Apples);
        Assert.assertEquals(moreThan4Apples.size(), HEAVIER_THAN_4_APPLES);
    }

    /**
     * Test for third attempting of filtering the apples by color or weight based on flag
     * Page 40
     */
    @Test
    public void thirdFilter() {
        List<Apple> filterApplesByColor = InventoryFunctions.filterApples(apples(), "red", 2, true);
        System.out.println(filterApplesByColor);
        Assert.assertEquals(filterApplesByColor.size(), RED_APPLES);

        List<Apple> filterApplesByWeigth = InventoryFunctions.filterApples(apples(), "red", 2, false);
        System.out.println(filterApplesByWeigth);
        Assert.assertEquals(filterApplesByWeigth.size(), HEAVIER_THAN_2_APPLES);
    }

    /**
     * Test for fourth attempting of filtering the apples using the strategy pattern
     * Page 42
     */
    @Test
    public void fourthFilter() {
        List<Apple> filterGreenApples = InventoryFunctions.filterApples(apples(), new AppleGreenColorPredicate());
        System.out.println(filterGreenApples);
        Assert.assertEquals(filterGreenApples.size(), GREEN_APPLES);

        List<Apple> filterHeavierThan2Apples = InventoryFunctions.filterApples(apples(), new AppleHeavierThan2Predicate());
        System.out.println(filterHeavierThan2Apples);
        Assert.assertEquals(filterHeavierThan2Apples.size(), HEAVIER_THAN_2_APPLES);

        List<Apple> filterRedAndHeavyierThan2Apples = InventoryFunctions.filterApples(apples(), new AppleGreenColorPredicate());
        System.out.println(filterRedAndHeavyierThan2Apples);
        Assert.assertEquals(filterRedAndHeavyierThan2Apples.size(), RED_AND_HEAVIER_THAN_2_APPLES);
    }

    /**
     * Test for quiz2
     * Page 45
     */
    @Test
    public void quiz2Test() {
        QuizFunctions.prettyPrintApple(apples(), new ColorApplePrinter());
        QuizFunctions.prettyPrintApple(apples(), new WeightApplePrinter());
    }

    /**
     * Test for fifth attempt of filtering the apples by using strategy pattern with anonymous classes
     * Page 49
     */
    @Test
    public void fifthFilter() {
        List<Apple> redApples = InventoryFunctions.filterApples(apples(), new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
        System.out.println(redApples);
        Assert.assertEquals(redApples.size(), RED_APPLES);
    }

    /**
     * Test for sixth attempt of filtering the apples by using strategy pattern with lambdas
     * Page 51
     *
     */
    @Test
    public void sixthFilter() {
        List<Apple> redApples = InventoryFunctions.filterApples(apples(),
                (Apple apple) ->  "red".equals(apple.getColor()));
        System.out.println(redApples);
        Assert.assertEquals(redApples.size(), RED_APPLES);
    }

    /**
     * Test for seventh attempt of filtering the apples by using a generic strategy pattern with lambdas
     * Not only for apples, it can be used with numbers, or any other type since it's generic
     * Page 51
     *
     */
    @Test
    public void seventhFilter() {
        List<Apple> redApples = InventoryFunctions.filter(apples(),(Apple apple) -> "red".equals(apple.getColor()));
        System.out.println(redApples);
        Assert.assertEquals(redApples.size(), RED_APPLES);

        List<Integer> evenNumbers = InventoryFunctions.filter(Arrays.asList(1,2,3,4,5),(Integer i) -> i%2==0);
        System.out.println(evenNumbers);
        Assert.assertEquals(Arrays.asList(2,4), evenNumbers);
    }

    /**
     * Page 52
     */
    @Test
    public void realWorldExamples() {
        //################
        //Sorting using Comparator old style
        List<Apple> sortApplesByWeightOldStyle = apples();
        Collections.sort(sortApplesByWeightOldStyle, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return new Integer(o1.getWeight())
                        .compareTo(new Integer(o2.getWeight()));
            }
        });
        System.out.println(sortApplesByWeightOldStyle);

        //Sorting using Comparator and lambdas new style
        List<Apple> sortApplesByWeightNewStyle = apples();
        Collections.sort(sortApplesByWeightNewStyle, (Apple a1, Apple a2) ->
                new Integer(a1.getWeight()).compareTo(new Integer(a2.getWeight())));
        System.out.println(sortApplesByWeightNewStyle);

        Assert.assertEquals(sortApplesByWeightOldStyle, sortApplesByWeightNewStyle);

        //################
        //Runnable old style
        Thread tOld = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        tOld.start();

        //Runnable new style
        Thread tNew = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        tNew.start();


    }

    /**
     * Mock List of Apples
     *
     * @return
     */
    private List<Apple> apples() {
        List<Apple> apples = new ArrayList<>();

        Apple apple1 = new Apple();
        apple1.setColor("red");
        apple1.setWeight(5);
        apples.add(apple1);

        Apple apple2 = new Apple();
        apple2.setColor("green");
        apple2.setWeight(3);
        apples.add(apple2);

        return apples;
    }

}
