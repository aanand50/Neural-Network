import java.util.Random;

public class Node {
    protected double output;
    protected double z;
    protected double[] weights;
    protected double bias;
    protected double[] weightErrors;
    protected double biasError;

    protected Node(int numInputs) {
        weights = new double[numInputs];
        weightErrors = new double[numInputs];
    }

    public Node(int numInputs, Random random) {
        weights = new double[numInputs];
        weightErrors = new double[numInputs];
        for (int i = 0; i < numInputs; i++) {
            weights[i] = random.nextDouble() * 2 - 1;
        }
        bias = random.nextDouble() * 2 - 1;
    }
}
