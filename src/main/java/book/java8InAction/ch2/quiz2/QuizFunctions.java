package book.java8InAction.ch2.quiz2;

import java.util.List;

import book.java8InAction.domain.Apple;

/**
 * Created by ahernandez on 10/28/16.
 */
public class QuizFunctions {

    public static void prettyPrintApple(List<Apple> inventory, ApplePrinter applePrinter) {
        for(Apple apple : inventory) {
            String output = applePrinter.format(apple);
            System.out.println(output);
        }
    }
}
