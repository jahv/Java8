package book.java8InAction.ch3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

import book.java8InAction.domain.Apple;

/**
 * Created by ahernandez on 11/4/16.
 */
public class Chapter3Test {

    /**
     * Testing the java.util.function.Predicate
     * page 70
     */
    @Test
    public void predicateTest() {
        List<String> list = Arrays.asList("A", "", "B", "C", "D", "1", "2");
        List<String> nonEmpty = Arrays.asList("A", "B", "C", "D", "1", "2");
        List<String> letters = Arrays.asList("A", "B", "C", "D");
        List<String> numbers = Arrays.asList("1", "2");

        //Non Empty
        Predicate<String> nonEmptyPredicate = (String s) -> !s.isEmpty();
        List<String> result = FunctionalInterfaces.filter(list, nonEmptyPredicate);
        System.out.println(result);
        Assert.assertEquals(nonEmpty, result);

        //letters
        Predicate<String> onlyLettersPredicate = (String s) -> !s.isEmpty() && !s.matches("[0-9]");
        result = FunctionalInterfaces.filter(list, onlyLettersPredicate);
        System.out.println(result);
        Assert.assertEquals(letters, result);

        //numbers
        Predicate<String> onlyNumbersPredicate = (String s) -> s.matches("[0-9]");
        result = FunctionalInterfaces.filter(list, onlyNumbersPredicate);
        System.out.println(result);
        Assert.assertEquals(numbers, result);
    }

    /**
     * Testing the java.util.function.Consumer
     * page 71
     */
    @Test
    public void consumerTest() {
        List<Integer> list = Arrays.asList(1,2,3,4,5);

        Consumer<Integer> printIntegers = (Integer i) -> System.out.println("Value: [" + i + "]");
        FunctionalInterfaces.forEach(list, printIntegers);

        Consumer<Integer> printSecondPowerIntegers = (Integer i) -> System.out.println(i + "*" + i + " = " + (i*i));
        FunctionalInterfaces.forEach(list, printSecondPowerIntegers);
    }

    /**
     * Testing the java.util.function.Function
     * Page 72
     */
    @Test
    public void functionTest() {
        List<String> list = Arrays.asList("a", "", "abc", "ones", "twice", "example");
        Function<String, Integer> string2Length = (String s) -> s.length();
        List<Integer> length = FunctionalInterfaces.map(list, string2Length);
        System.out.println(length);
    }

    /**
     * Testing IntPredicate
     * Page 73
     */
    @Test
    public void intPredicateTest() {
        //There is no boxing since IntPredicate accepts int
        IntPredicate evenNumber = (int i) -> i % 2 == 0;
        Assert.assertTrue(evenNumber.test(1000));

        //There is boxing since Predicate<Integer> needs Integer, but we supply int
        Predicate<Integer> oddNumber = (Integer i) -> i % 2 == 1;
        Assert.assertTrue(oddNumber.test(1001));
    }

    /**
     * Constructor reference
     * Page 86
     */
    @Test
    public void constructorReference() {
        //############# non args constructor

        //Using Supplier interface since its for a non args constructor
        Supplier<Apple> appleSupplier = Apple::new;
        Apple apple = appleSupplier.get();
        System.out.println(apple);

        //Using anonymous class
        appleSupplier = new Supplier<Apple>() {
            @Override
            public Apple get() {
                return new Apple();
            }
        };
        apple = appleSupplier.get();
        System.out.println(apple);

        //Using lambda
        appleSupplier = () -> new Apple();
        apple = appleSupplier.get();
        System.out.println(apple);

        //############# 1 arg constructor

        //Using Function interface since its for a 1 arg constructor
        Function<String, Apple> appleFunction = Apple::new;
        apple = appleFunction.apply("Green");
        System.out.println(apple);

        //Using anonymous class
        appleFunction = new Function<String, Apple>() {
            @Override
            public Apple apply(String s) {
                return new Apple(s);
            }
        };
        apple = appleFunction.apply("Green");
        System.out.println(apple);

        //Using lambda
        appleFunction = (String s) -> new Apple(s);
        apple = appleFunction.apply("Green");
        System.out.println(apple);

        //############# 2 args constructor

        //Using BiFunction interface since its for a 2 args constructor
        BiFunction<String, String, Apple> appleBiFunction = Apple::new;
        apple = appleBiFunction.apply("Red", "Puebla");
        System.out.println(apple);

        //Using anonymous class
        appleBiFunction = new BiFunction<String, String, Apple>() {
            @Override
            public Apple apply(String s, String s2) {
                return new Apple(s, s2);
            }
        };
        apple = appleBiFunction.apply("Red", "Puebla");
        System.out.println(apple);

        //Using lambda
        appleBiFunction = (String color, String origin) -> new Apple(color, origin);
        apple = appleBiFunction.apply("Red", "Puebla");
        System.out.println(apple);

        //############# 3 args constructor

        //Using TriFunction (own) interface since its for a 2 args constructor
        TriFunction<String, String, Integer, Apple> appleTriFunction = Apple::new;
        apple = appleTriFunction.apply("Red", "Puebla", 350);
        System.out.println(apple);

        //Using anonymous class
        appleTriFunction = new TriFunction<String, String, Integer, Apple>() {
            @Override
            public Apple apply(String s, String s2, Integer i) {
                return new Apple(s, s2, i);
            }
        };
        apple = appleTriFunction.apply("Red", "Puebla", 350);
        System.out.println(apple);

        //Using lambda
        appleTriFunction = (String color, String origin, Integer i) -> new Apple(color, origin, i);
        apple = appleTriFunction.apply("Red", "Puebla", 350);
        System.out.println(apple);
    }

    /**
     * Putting lambdas and method reference in practice
     * page 89
     */
    @Test
    public void methodReference() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5,7,4,6,4,3,1,9));

        //Using anonymous class
        List<Integer> anonymous = new ArrayList<>(numbers);
        anonymous.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(anonymous);

        //Using lambda
        List<Integer> lambda = new ArrayList<>(numbers);
        lambda.sort((Integer i1, Integer i2) -> i1.compareTo(i2));
        System.out.println(lambda);

        //Using method reference
        List<Integer> methodReference = new ArrayList<>(numbers);
        methodReference.sort(Integer::compare);
        System.out.println(methodReference);

        Assert.assertEquals(anonymous, lambda);
        Assert.assertEquals(lambda, methodReference);
    }

    /**
     * Testing predicate, consumer and function but using method reference instead
     * lambdas
     */
    @Test
    public void moreMethodReferenceTest() {
        //### Predicate
        List<String> listString = Arrays.asList("A", "", "B", "C", "D", "1", "2");

        //Empty
        List<String> result = FunctionalInterfaces.filter(listString, String::isEmpty);
        System.out.println(result);

        //Non Empty
        result = FunctionalInterfaces.filter(listString, MoreMethodReference::isNotEmpty);
        System.out.println(result);

        //### Consumer
        List<Integer> listInteger = Arrays.asList(1,2,3,4,5);
        FunctionalInterfaces.forEach(listInteger, System.out::print);
        System.out.println();

        //### Function
        List<String> list = Arrays.asList("a", "", "abc", "ones", "twice", "example");
        List<Integer> length = FunctionalInterfaces.map(list, String::length);
        System.out.println(length);
    }

    /**
     * Testing composing Comparators using
     *      Comparator.comparing
     *      Comparator.reversed
     *      Comparator.thenComparing
     *
     * Page 91
     */
    @Test
    public void composingComparatorTest() {
        // Comparators: reversed
        List<String> listString = Arrays.asList("A", "", "B", "C", "D", "1", "2");
        listString.sort(Comparator.comparing(String::toString));//Using the toString method for having the current value
        System.out.println(listString);

        listString.sort(Comparator.comparing(String::toString).reversed());
        System.out.println(listString);

        // Chaining comparators
        List<Apple> apples = new ArrayList<>(
          Arrays.asList(
                  new Apple("A", "C", 1),
                  new Apple("A", "D", 2),
                  new Apple("A", "E", 3),
                  new Apple("B", "C", 1),
                  new Apple("B", "D", 2),
                  new Apple("B", "E", 3)
          )
        );
        apples.sort(Comparator.comparing(Apple::getColor)
                .thenComparing(Apple::getOrigin)
                .thenComparingInt(Apple::getWeight));
        System.out.println(apples);
    }

    /**
     * Testing composing predicates using
     *      negate
     *      and
     *      or
     *
     * Page 92
     */
    @Test
    public void composingPredicateTest() {
        List<Apple> apples = new ArrayList<>(
                Arrays.asList(
                        new Apple("red", "mexico", 1),
                        new Apple("red", "mexico", 2),
                        new Apple("red", "usa", 3),
                        new Apple("green", "mexico", 1),
                        new Apple("green", "usa", 2),
                        new Apple("green", "usa", 3)
                )
        );

        //Base
        Predicate<Apple> redApples = (Apple apple) -> "red".equalsIgnoreCase(apple.getColor());
        System.out.println("BASE");
        System.out.println(FunctionalInterfaces.filter(apples, redApples));

        //Negate
        Predicate<Apple> notRedApples = redApples.negate();
        System.out.println("NEGATE");
        System.out.println(FunctionalInterfaces.filter(apples, notRedApples));

        //And
        Predicate<Apple> notRedAndMexican = notRedApples.and((Apple apple) -> "mexico".equalsIgnoreCase(apple.getOrigin()));
        System.out.println("AND");
        System.out.println(FunctionalInterfaces.filter(apples, notRedAndMexican));

        //Or
        Predicate<Apple> notRedAndMexicanOrHeavy = notRedAndMexican.or((Apple apple) -> apple.getWeight() >= 3);
        System.out.println("OR");
        System.out.println(FunctionalInterfaces.filter(apples, notRedAndMexicanOrHeavy));
    }

    /**
     * Testing composing functions using
     *      andThen
     *      compose
     *
     * Page 93
     */
    @Test
    public void composingFunctionsTest() {
        Function<Integer, Integer> f = (Integer input) -> input + 1;
        Function<Integer, Integer> g = (Integer input) -> input * 2;

        //AndThen
        Function<Integer, Integer> h = f.andThen(g);
        System.out.println(h.apply(1));

        //compose
        h = f.compose(g);
        System.out.println(h.apply(1));
    }
}
