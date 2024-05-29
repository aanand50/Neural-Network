import processing.core.*;

public class Main extends PApplet {
    private NeuralNetwork nn;
    private Function activationFunction;
    private Button feedForwardButton;
    private Button backPropButton;
    private TextBox inputBox;
    private TextBox targetBox;
    private double learningRate = 0.1;

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "Main" }, new Main());
    }

    public void settings() {
        size(800, 800);
    }

    public void draw() {
        background(255);

        // Draw UI components
        feedForwardButton.display();
        backPropButton.display();
        inputBox.display();
        targetBox.display();

        // Draw neural network visualization
        // drawNeuralNetwork();
    }

    public void setup() {
        // activationFunction=
        // nn
        feedForwardButton = new Button("Feed Forward", 50, 500, 100, 30, this);
        backPropButton = new Button("Back Propagate", 200, 500, 120, 30, this);
        inputBox = new TextBox(50, 450, 100, 30, this);
        targetBox = new TextBox(200, 450, 100, 30, this);
    }

    public void keyPressed() {
        if (key == '\b') {
            if (inputBox.isActive())
                inputBox.removeLastChar();
            if (targetBox.isActive())
                targetBox.removeLastChar();
        } else {
            if (inputBox.isActive())
                inputBox.appendText(key);
            if (targetBox.isActive())
                targetBox.appendText(key);
        }
    }

    public void mouseClicked() {
        if (feedForwardButton.isClicked(mouseX, mouseY)) {
            double[] inputs = parseInputs(inputBox.getText());
            nn.feedForward(inputs);
        }
        if (backPropButton.isClicked(mouseX, mouseY)) {
            double[] targets = parseInputs(targetBox.getText());
            nn.backProp(targets, learningRate);
        }
    }

    private double[] parseInputs(String input) {
        String[] parts = input.split(",");
        double[] result = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Double.parseDouble(parts[i]);
        }
        return result;
    }
}
