package book.java8InAction.ch3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by ahernandez on 11/4/16.
 */
public class FunctionalInterfaces {

    /**
     * Using Predicate for filter in a list
     *Page 70
     * @param list
     * @param p
     * @param <T>
     * @return
     */
    public static <T>List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<T>();
        for(T t : list) {
            if(p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    /**
     * Using Consumer for doing an own forEach
     * Page 71
     * @param list
     * @param c
     * @param <T>
     */
    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for(T t : list) {
            c.accept(t);
        }
    }

    /**
     * Using Function for mapping string to length
     * Page 72
     * @param list
     * @param f
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<R>();
        for(T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }
}
