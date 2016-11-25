package book.java8Lambdas.ch2.lambdaExpr;

import java.util.function.Predicate;

/**
 * Created by ahernandez on 10/14/16.
 */
public class Check {

    public boolean check (Predicate<Integer> predicate){
        return predicate.test(10);
    }

    public boolean check (IntPred predicate) {
        return predicate.test(1);
    }
}
