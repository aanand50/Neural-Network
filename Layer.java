import java.util.Random;

import processing.core.*;

public class Layer {
    private Node[] nodes;
    private PApplet parent;

    public Layer(int size, int numInputs, Random random, PApplet parent) {
        nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(numInputs, random, parent);
        }
        this.parent = parent;
    }

    protected Layer(double[] inputs, PApplet parent) {
        nodes = new Node[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            nodes[i] = new Node(0, parent);
            nodes[i].a = inputs[i];
        }
        this.parent = parent;
    }

    public void setInputs(double[] inputs) {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].a = inputs[i];
        }
    }

    public void feedForward(Function activationFunction, Layer previousLayer) {
        for (Node node : nodes) {
            double sum = 0;
            for (int i = 0; i < previousLayer.nodes.length; i++) {
                sum += previousLayer.nodes[i].a * node.weights[i];
            }
            sum += node.bias;
            node.z = sum;
            node.a = activationFunction.get(sum);
        }
    }

    public void backProp(Function activationFunction, Layer previousLayer, boolean isSecondLayer) {
        for (int i = 0; i < nodes.length; i++) {
            Node node = nodes[i];
            node.inputError = node.outputError * activationFunction.getPrime(node.z);
        }

        for (int i = 0; i < nodes.length; i++) {
            Node node = nodes[i];
            for (int j = 0; j < previousLayer.nodes.length; j++) {
                node.weightErrors[j] = node.inputError * previousLayer.nodes[j].a;
            }
        }

        if (isSecondLayer) {
            return;
        }

        for (int i = 0; i < previousLayer.nodes.length; i++) {
            Node node = previousLayer.nodes[i];
            node.outputError = 0;
            for (int j = 0; j < nodes.length; j++) {
                node.outputError += nodes[j].weights[i] * nodes[j].inputError;
            }
        }
    }

    public void updateWeights(double learningRate, Layer previousLayer) {
        for (Node node : nodes) {
            node.bias -= learningRate * node.inputError;
            node.inputError = 0;

            for (int i = 0; i < previousLayer.nodes.length; i++) {
                node.weights[i] -= learningRate * node.weightErrors[i];
                node.weightErrors[i] = 0;
            }
        }
    }

    public void setErrors(double[] targets) {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].outputError = nodes[i].a - targets[i];
        }
    }

    public double[] getOutputs() {
        double[] outputs = new double[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            outputs[i] = nodes[i].a;
        }
        return outputs;
    }

    public String toString() {
        return nodes.length + " nodes\nfirst node has " + nodes[0].weights.length + " inputs\n";
    }

    public void visualization(int x, int y) {
        y = y + 40;
        for (int node = 0; node < nodes.length; node++) {
            nodes[node].visualization(x - 15, y + 50 * node);
        }
    }

    public void visualization(int x, int y, int prevX) {
        y = y + 40;
        for (int node = 0; node < nodes.length; node++) {
            nodes[node].visualization(x - 15, y + 50 * node);
            for (int weight = 0; weight < nodes[node].weights.length; weight++) {
                int rgb = (int) ((nodes[node].weights[weight] / 5) * 255);
                if (rgb > 0) {
                    parent.stroke(rgb * 3, rgb * 2, rgb);
                }
                else if (rgb < 0) {
                    rgb *= -1;
                    parent.stroke(rgb, rgb * 2, rgb * 3);
                }
                parent.line(x, y + 50 * node + 15, prevX, y + 50 * weight + 15);
            }
        }
    }
}
