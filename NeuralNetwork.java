import java.util.Random;

public class NeuralNetwork {
    private Layer[] layers;
    private Function activationFunction;
    private Random random = new Random();

    public NeuralNetwork(int[] layerSizes, Function activationFunction) {
        this.activationFunction = activationFunction;
        layers = new Layer[layerSizes.length];
        int numInputs = 0;
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new Layer(layerSizes[i], numInputs, random);
            numInputs = layerSizes[i];
        }
    }

    public void feedForward(double[] inputs) {
        Layer previousLayer = layers[0];
        previousLayer.setInputs(inputs);
        for (int i = 1; i < layers.length; i++) {
            layers[i].feedForward(activationFunction, previousLayer);
            previousLayer = layers[i];
        }
    }

    public void backProp(double[] targets, double learningRate) {
        Layer outputLayer = layers[layers.length - 1];
        outputLayer.setErrors(targets);

        for (int l = layers.length - 1; l > 0; l--) {
            Layer layer = layers[l];
            Layer previousLayer = layers[l - 1];
            layer.backProp(activationFunction, previousLayer, l == 1);
        }

        for (int l = 1; l < layers.length; l++) {
            Layer layer = layers[l];
            Layer previousLayer = layers[l - 1];
            layer.updateWeights(learningRate, previousLayer);
        }
    }

    public double[] getOutput() {
        return layers[layers.length - 1].getOutputs();
    }
    
    public String toString() {
        String layerInfo = "";
        for (int i = 0; i < layers.length; i++) {
            if (i == 0) {
                layerInfo += "Input Layer: ";
            }
            else if (i == layers.length - 1) {
                layerInfo += "Output Layer: ";
            }
            else {
                layerInfo += "Hidden Layer: ";
            }
            layerInfo += layers[i].toString() + "\n";
        }
        return layerInfo;
    }
}
