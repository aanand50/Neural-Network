import java.util.Random;

public class Node {
    protected double z;
    protected double a;
    protected double bias;
    protected double[] weights;
    protected double inputError;
    protected double outputError;
    protected double[] weightErrors;

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
    }
}
