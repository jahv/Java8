package book.java8InAction.ch2;

import book.java8InAction.domain.Apple;

/**
 * Created by ahernandez on 10/28/16.
 */
public class AppleHeavierThan2Predicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 2;
    }
}
