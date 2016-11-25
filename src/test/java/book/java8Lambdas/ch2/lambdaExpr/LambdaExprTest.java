package book.java8Lambdas.ch2.lambdaExpr;

import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ahernandez on 10/13/16.
 */
public class LambdaExprTest {

    /**
     * Using Runnable functional interface with lambda
     */
    @Test
    public void functionalInterfaceTest() {
        Runnable oldWay = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        };
        Thread t = new Thread(oldWay);
        t.run();

        Runnable newWay = ()-> System.out.println("Hello World");
        t = new Thread(newWay);
        t.run();
    }

    /**
     * Check if data type can be inferred
     */
    @Test
    public void IntPredTest() {
        Check check = new Check();

        //Ambiguos
        //boolean inferred = check.check(x -> x > 5);

        //OK
        boolean inferred1 = check.check((Predicate<Integer>) x -> x > 5);
        Assert.assertTrue(inferred1);

        //Ok
        boolean inferred2 = check.check((IntPred)x -> x > 5);
        Assert.assertFalse(inferred2);
    }
}
