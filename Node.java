import java.util.Random;

import processing.core.*;

public class Node {
    protected double z;
    protected double a;
    protected double bias;
    protected double[] weights;
    protected double inputError;
    protected double outputError;
    protected double[] weightErrors;
    private PApplet parent;

    protected Node(int numInputs, PApplet parent) {
        weights = new double[numInputs];
        weightErrors = new double[numInputs];
        this.parent = parent;
    }

    public Node(int numInputs, Random random, PApplet parent) {
        weights = new double[numInputs];
        weightErrors = new double[numInputs];
        for (int i = 0; i < numInputs; i++) {
            weights[i] = random.nextDouble() * 2 - 1;
        }
        this.parent = parent;
    }

    public void visualization(int x, int y) {
        parent.rect(x, y, 25, 25);
        parent.fill(0);
    }
}
