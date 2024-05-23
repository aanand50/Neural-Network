public class Layer {
    private Node[] nodes;

    public Layer(int size) {
        nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node();
        }
    }

    protected Layer(double[] inputs) {
        //TODO make dummy layer
    }

    public void feedForward(Function activationFunction, Layer previousLayer) {
        //TODO
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
