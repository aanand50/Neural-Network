import java.util.function.DoubleFunction;

public class Function {
    private DoubleFunction<Double> function;
    private DoubleFunction<Double> prime;

    public static final Function SIGMOID = new Function(
            x -> 1 / (1 + Math.exp(-x)),
            x -> (1 / (1 + Math.exp(-x))) * (1 - (1 / (1 + Math.exp(-x)))));

    public static final Function TANH = new Function(
            Math::tanh,
            x -> 1 - Math.pow(Math.tanh(x), 2));

    public static final Function RELU = new Function(
            x -> Math.max(0, x),
            x -> x < 0 ? 0.0 : 1);

    public Function(DoubleFunction<Double> function, DoubleFunction<Double> prime) {
        this.function = function;
        this.prime = prime;
    }

    public double get(double x) {
        return function.apply(x);
    }

    public double getPrime(double x) {
        return prime.apply(x);
    }
}
