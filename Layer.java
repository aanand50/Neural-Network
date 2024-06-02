import java.util.Random;

public class Layer {
    private Node[] nodes;

    public Layer(int size, int numInputs, Random random) {
        nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(numInputs, random);
        }
    }

    protected Layer(double[] inputs) {
        nodes = new Node[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            nodes[i] = new Node(0);
            nodes[i].a = inputs[i];
        }
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
            node.outputError = 0;
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
                node.inputError += nodes[j].weights[i] * nodes[j].inputError;
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
}
