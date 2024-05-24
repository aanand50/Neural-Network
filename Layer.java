import java.util.Random;

public class Layer {
    private Node[] nodes;

    public Layer(int size, Random random) {
        nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(size, random);
        }
    }

    protected Layer(double[] inputs) {
        nodes = new Node[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            nodes[i] = new Node(inputs.length);
            nodes[i].output = inputs[i];
        }
    }

    public void feedForward(Function activationFunction, Layer previousLayer) {
        for (Node node : nodes) {
            double sum = 0;
            for (int i = 0; i < previousLayer.nodes.length; i++) {
                sum += previousLayer.nodes[i].output * node.weights[i];
            }
            sum += node.bias;
            node.z = sum;
            node.output = activationFunction.get(sum);
        }
    }

    public void backProp(Function activationFunction, Layer nextLayer) {
        //TODO
    }

    public double[] getOutputs() {
        double[] outputs = new double[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            outputs[i] = nodes[i].output;
        }
        return outputs;
    }
}
