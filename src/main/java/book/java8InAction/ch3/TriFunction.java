package book.java8InAction.ch3;

/**
 * Created by ahernandez on 11/16/16.
 */
@FunctionalInterface
public interface TriFunction<T, U, V, R> {

    R apply(T t, U u, V v);
}
