package book.java8InAction.ch2;

import book.java8InAction.domain.Apple;

/**
 * Created by ahernandez on 10/28/16.
 */
public class AppleGreenColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
