package book.java8InAction.ch5;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import book.java8InAction.ch4.Ch4Test;
import book.java8InAction.domain.Dish;

/**
 * Created by ahernandez on 11/16/16.
 */
public class Ch5Test {

    /**
     * Filtering test
     * Page 119
     */
    @Test
    public void filteringTest() {
        List<Dish> menu = Ch4Test.getMenu();

        //Filter vegetarian dishes using an anonymous class
        List<Dish> vegetarianMenuAnonymousClass = menu.stream()
                .filter(new Predicate<Dish>() {
                    @Override
                    public boolean test(Dish dish) {
                        return dish.isVegetarian();
                    }
                })
                .collect(Collectors.toList());
        System.out.println(vegetarianMenuAnonymousClass);

        //Filter using a lambda
        List<Dish> vegetarianMenuLambda = menu.stream()
                .filter((Dish dish) -> dish.isVegetarian())
                .collect(Collectors.toList());
        System.out.println(vegetarianMenuLambda);

        //Filter using method reference
        List<Dish> vegetarianMenuMethodReference = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        System.out.println(vegetarianMenuMethodReference);

        Assert.assertEquals(vegetarianMenuAnonymousClass, vegetarianMenuLambda);
        Assert.assertEquals(vegetarianMenuLambda, vegetarianMenuMethodReference);
    }

    /**
     * Filtering distinct elements
     * Filter all distinct even numbers
     * Page 120
     */
    @Test
    public void distinctTest() {
        List<Integer> numbers = Arrays.asList(6, 1, 2, 1, 3, 3, 2, 4);
        numbers.stream().distinct().forEach(System.out::print);
        System.out.println();
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::print);
    }

    /**
     * Limit elements
     * Page 121
     */
    @Test
    public void limitTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.stream().limit(5).forEach(System.out::println);
    }

    /**
     * Skip test
     * Page 121
     */
    @Test
    public void skipTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.stream().skip(5).forEach(System.out::println);
    }

    /**
     * Filter 2 first meat dishes
     * Page 122
     */
    @Test
    public void quiz5Test() {
        List<Dish> menu = Ch4Test.getMenu();
        menu.stream().
                filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .forEach(System.out::print);
    }

    /**
     * Map test, converting from Dish -> String
     * Page 123
     */
    @Test
    public void mapTest() {
        List<Dish> menu = Ch4Test.getMenu();

        //Using anonymous class
        List<String> names = menu.stream()
                .map(new Function<Dish, String>() {
                    @Override
                    public String apply(Dish dish) {
                        return dish.getName();
                    }
                })
                .collect(Collectors.toList());
        System.out.println(names);

        //Using a lambda
        names = menu.stream()
                .map((Dish dish) -> dish.getName())
                .collect(Collectors.toList());
        System.out.println(names);

        //Using method reference
        names = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(names);
    }


    /**
     * Testing flatMap
     * We want to return a list of all unique characters of a list of words
     * example: ["Hello", "World"] => ["H", "e", "l", "o", "W", "r", "d"]
     *
     * Page 125
     */
    @Test
    public void flatMapTest() {
        List<String> words = Arrays.asList("Hello", "World");

        //First attempt, but this generates a List<String[]>
        System.out.println(
                words.stream()
                        .map(word -> word.split(""))
                        .distinct()
                        .collect(Collectors.toList())
        );

        //Using flatMap
        System.out.println(
            words.stream()
                    .map(word -> word.split(""))
                    .flatMap(arrayString -> Arrays.stream(arrayString))
                    .distinct()
                    .collect(Collectors.toList())
        );
    }

    /**
     * 1. Given a list of numbers return the square of each
     *      i.e. [1, 2, 3, 4, 5] => [1, 4, 9, 16, 25]
     * 2. Given 2 list of numbers return all pairs of numbers
     *      i.e. [1, 2, 3] and [3, 4] => [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
     * 3. Using exercise 2. Return only pairs sum is divisible by 3
     *
     * Page 127
     */
    @Test
    public void quiz5_2Test() {
        //#### 1.
        System.out.println("1.");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> square = numbers.stream()
                .map(i -> i * i)
                .collect(Collectors.toList());
        System.out.println(square);

        //### 2.
        System.out.println("2.");
        List<Integer> pairA = Arrays.asList(1, 2, 3);
        List<Integer> pairB = Arrays.asList(3, 4);
        List<int[]> pairs = pairA.stream()
                .flatMap(i -> pairB.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs.forEach(array -> System.out.println(Arrays.toString(array)));

        //### 3.
        System.out.println("3.");
        List<int[]> pairsDivisibleBy3 = pairA.stream()
                .flatMap(i -> pairB.stream().filter(j -> (j + i) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairsDivisibleBy3.forEach(array -> System.out.println(Arrays.toString(array)));
    }

    /**
     * Testing matching
     *
     *      anyMatch
     *      allMatch
     *      noneMatch
     *
     * Page 129
     */
    @Test
    public void matchingTest() {
        List<Dish> menu = Ch4Test.getMenu();

        //### anyMatch
        if(menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is somewhat vegetarian friendly");
        }

        //### allMatch
        if(menu.stream().allMatch(d -> d.getCalories() < 1000)) {
            System.out.println("The menu is healthy");
        }

        //### noneMatch
        if(menu.stream().noneMatch(d -> d.getCalories() >= 1000)) {
            System.out.println("The menu is healthy");
        }
    }

    /**
     * Testing finding
     *      findAny
     *      findFirst
     *
     * Page 130
     */
    @Test
    public void findingTest() {
        List<Dish> menu = Ch4Test.getMenu();

        //##findAny
        Optional<Dish> dish = menu.stream()
                .filter(dish1 -> dish1.getCalories() > 1000)
                .findAny();
        System.out.println(dish);

        dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(dish);

        //##findFirst
        dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findFirst();
        System.out.println(dish);
    }

    /**
     * Testing reducing
     *
     * Page 132
     */
    @Test
    public void reducingTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        //## Sum
        int sum = numbers.stream().reduce(0, (i, j) -> i + j);
        System.out.println(sum);

        //## Product
        int prod = numbers.stream().reduce(1, (i, j) -> i * j);
        System.out.println(prod);

        //No initial value
        Optional<Integer> sum2 = numbers.stream().reduce((i, j) -> i + j);
        System.out.println(sum2);

        //Using method reference
        Optional<Integer> sum3 = numbers.stream().reduce(Integer::sum);
        System.out.println(sum3);
    }

    /**
     * Testing max and min
     *
     * Page 135
     */
    @Test
    public void maxMinTest() {
        List<Integer> numbers = Arrays.asList(3, 4, 9, 6);

        //## Max using reduce
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println(max);

        //## Min using reduce
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        System.out.println(min);
    }

    /**
     * Testing quiz 5.3
     * Count number of dishes in a stream using map and reduce
     *
     * Page 136
     */
    @Test
    public void quiz_5_3_Reducing() {
        List<Dish> menu = Ch4Test.getMenu();

        int numberDishes = menu.stream().map(d -> 1).reduce(0, Integer::sum);
        System.out.println(numberDishes);
    }

    /**
     * Testing numeric streams
     *      sum
     *      max
     *      min
     *      range
     *      rangeClosed
     *
     * Page 145
     */
    @Test
    public void numericStreams() {
        List<Dish> menu = Ch4Test.getMenu();

        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(calories);

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        System.out.println(maxCalories);

        System.out.println("Range");
        IntStream intStream = IntStream.range(0, 5);
        intStream.forEach(System.out::println);

        System.out.println("Range closed");
        IntStream intStreamClosed = IntStream.rangeClosed(0, 5);
        intStreamClosed.forEach(System.out::println);
    }

    /**
     * Building streams test
     *      from values
     *      from arrays
     *      from files
     *
     *
     * Page 152
     */
    @Test
    public void buildingStreams() {
        System.out.println("_________________ From values");
        Stream<String> fromValues = Stream.of("Hello", "World");
        fromValues.forEach(System.out::println);

        System.out.println("_________________ From int array");
        int[] array = new int[]{1 ,2, 3, 4, 5};
        IntStream fromArray = Arrays.stream(array);
        System.out.println(fromArray.sum());

        System.out.println("_________________ From String array");
        String[] otherArray = new String[]{"Hello", "World"};
        Stream<String> streamString = Arrays.stream(otherArray);
        streamString.forEach(System.out::println);

        System.out.println("_________________ From file");
        try(
                Stream<String> lines = Files.lines(
                        Paths.get("/Users/ahernandez/git/PersonalTest/github/java8/src/test/resources/StreamFile.txt"),
                        Charset.defaultCharset()
                )

        ) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("_________________ Infinite stream");
        Stream.iterate(0, n -> n + 2).limit(5).forEach(System.out::println);
    }

    /**
     * Fibonacci series
     *
     * Page 154
     */
    @Test
    public void quiz_5_4_Fibonacci() {
        //(0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21)

        Stream.iterate(new int[]{0, 1}, t -> {
            int aux = t[0];
            t[0] = t[1];
            t[1] = aux + t[1];
            return t;
        })
                .limit(20)
//                .map(t -> t[0] + ", ")
//                .forEach(System.out::print);
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));

    }

    /**
     * Testing generate
     *
     * Page 155
     */
    @Test
    public void generateTest() {
        Stream.generate(() -> "Hello").limit(5).forEach(System.out::println);

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(20).forEach(System.out::println);
    }
}
