package functional;

import java.util.function.BiFunction;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

public class Curry {
    public static void main(String[] args) {

        IntFunction<IntUnaryOperator> curriedAdd = (a) -> {
            return (b) -> {
                return a + b;
            };
        };

        IntFunction<IntUnaryOperator> curriedAdd1 = new IntFunction<IntUnaryOperator>() {
            @Override
            public IntUnaryOperator apply(final int value) {
                IntUnaryOperator op = new IntUnaryOperator() {
                    @Override
                    public int applyAsInt(int operand) {
                        return operand + value;
                    }
                };
                return op;
            }
        };


        IntFunction<IntUnaryOperator> curriedAdd3 = a -> b -> a + b;
        IntFunction<IntUnaryOperator> curriedAdd4 = a -> (b -> a + b);
        BiFunction<Integer, Integer, Integer> add = (a, b) -> (a + b);

        System.out.println(curriedAdd.apply(1).applyAsInt(12));
        System.out.println(curriedAdd1.apply(1).applyAsInt(12));
        System.out.println(curriedAdd3.apply(1).applyAsInt(12));
        System.out.println(curriedAdd4.apply(1).applyAsInt(12));
        System.out.println(add.apply(1, 12));
    }
}
