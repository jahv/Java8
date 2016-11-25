package book.java8InAction.ch2.quiz2;

import book.java8InAction.domain.Apple;

/**
 * Created by ahernandez on 10/28/16.
 */
public class WeightApplePrinter implements ApplePrinter {

    @Override
    public String format(Apple apple) {
        return "Apple weight: " + apple.getWeight();
    }
}
