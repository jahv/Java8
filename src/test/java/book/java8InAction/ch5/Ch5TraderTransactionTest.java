package book.java8InAction.ch5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import book.java8InAction.domain.Trader;
import book.java8InAction.domain.Transaction;

/**
 * Solving Traders Transaction exercises
 *
 * Page 140
 *
 * Created by ahernandez on 11/24/16.
 */
public class Ch5TraderTransactionTest {

    final List<Transaction> transactions = mockTransactions();

    /**
     * Find all transactions in the year 2011 and sort them by value (small to high)
     */
    @Test
    public void ex1() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactionsExpected = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2011, 400)
        );


        List<Transaction> transactionResult = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()))
                .collect(Collectors.toList());
        System.out.println(transactionResult);

        Assert.assertEquals(transactionsExpected, transactionResult);
    }

    /**
     * Unique cities where the traders work
     */
    @Test
    public void ex2() {
        List<String> citiesExpected = Arrays.asList("Cambridge", "Milan");

        List<String> cities = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cities);

        Assert.assertEquals(citiesExpected, cities);
    }

    /**
     * Find all traders form Cambridge and sort them by name
     */
    @Test
    public void ex3() {
        List<Trader> tradersExpected = Arrays.asList(
                new Trader("Alan", "Cambridge"),
                new Trader("Brian", "Cambridge"),
                new Trader("Raoul", "Cambridge")
        );


        List<Trader> tradersFromCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted((trader1, trader2) -> trader1.getName().compareTo(trader2.getName()))
                .collect(Collectors.toList());
        System.out.println(tradersFromCambridge);

        Assert.assertEquals(tradersExpected, tradersFromCambridge);
    }

    /**
     * Return string of all traders' names sorted alphabetically
     */
    @Test
    public void ex4() {
        String expected = "AlanBrianMarioRaoul";

        String names = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .reduce("", (s1, s2) -> s1+s2);
        System.out.println(names);

        Assert.assertEquals(expected, names);
    }

    /**
     * Are any traders based in Milan?
     */
    @Test
    public void ex5() {
        boolean result = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> "Milan".equals(trader.getCity()));
        Assert.assertTrue(result);
    }

    /**
     * Print all transactions' values from traders living in Cambridge
     */
    @Test
    public void ex6() {
        List<Integer> expected = Arrays.asList(300, 1000, 400, 950);

        List<Integer> result = transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
        System.out.println(result);

        Assert.assertEquals(expected, result);
    }

    /**
     * The highest value of all transactions
     */
    @Test
    public void ex7() {
        Integer expected = 1000;

        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce((t1, t2) -> Integer.max(t1, t2));
        System.out.println(max);
        Assert.assertEquals(expected, max.get());
    }

    /**
     * The smallest value of all transactions
     */
    @Test
    public void ex8() {
        Integer expected = 300;

        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .reduce((t1, t2) -> Integer.min(t1, t2));
        System.out.println(min);
        Assert.assertEquals(expected, min.get());
    }

    /**
     * Mock transactions
     * @return
     */
    private List<Transaction> mockTransactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        return transactions;
    }
}
