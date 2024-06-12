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
        parent.rectMode(PApplet.CORNER);
        parent.rect(x, y, 30, 30, 10);
        int rgb = (int) (bias / 4 * 255);
        System.out.println("Bias: " + bias + " RGB: " + rgb);
        if (rgb > 0) {
            if (rgb > 0) {
                parent.fill(rgb * 3, rgb * 2, rgb);
            }
            else if (rgb < 0) {
                rgb *= -1;
                parent.fill(rgb, rgb * 2, rgb * 3);
            }
        }
    }
}
