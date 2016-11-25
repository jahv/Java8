package book.java8InAction.ch2;

import book.java8InAction.domain.Apple;

/**
 * Created by ahernandez on 10/28/16.
 *
 * Interface for implementing the strategy pattern
 */
public interface ApplePredicate {
    boolean test(Apple apple);
}
