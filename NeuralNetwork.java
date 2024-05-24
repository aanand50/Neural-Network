import java.util.Random;

public class NeuralNetwork {
    private Layer[] layers;
    private Function activationFunction;
    private Random random = new Random();

    public NeuralNetwork(int[] layerSizes, Function activationFunction) {
        this.activationFunction = activationFunction;
        layers = new Layer[layerSizes.length];
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new Layer(layerSizes[i], random);
        }
    }

    public void feedForward(double[] inputs) {
        Layer previousLayer = new Layer(inputs);
        for (Layer layer : layers) {
            layer.feedForward(activationFunction, previousLayer);
            previousLayer = layer;
        }
    }

    public void backProp(double[] targets) {
        double[] outputs = layers[layers.length - 1].getOutputs();
        double[] error = new double[outputs.length];
        for (int i = 0; i < outputs.length; i++) {
            error[i] = targets[i] - outputs[i];
        }
        Layer nextLayer = new Layer(error);
        for (int i = layers.length - 1; i >= 0; i--) {
            layers[i].backProp(activationFunction, nextLayer);
        }
    }
}
