import java.util.function.DoubleFunction;

public class Function {
    private DoubleFunction<Double> function;
    private DoubleFunction<Double> prime;

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
