package functional;

import java.util.function.BiFunction;
import java.util.function.Function;

public class BiFunctionTest {
    public static void main(String[] args) {
        BiFunction<String, String, String> f = (x, y) -> x.toLowerCase().concat(y);

        System.out.println(f.apply("DFDF", " her"));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + Integer.MIN_VALUE);

//        System.out.println(1 / 0);
        assert 1 == 2;

        Function<Integer, Integer> g = x -> x + 2;
        ((Function<Integer, Integer>) x -> x + 2).apply(2);
    }

}
