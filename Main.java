import processing.core.*;

public class Main extends PApplet {
    private NeuralNetwork nn;
    private Function activationFunction;
    private Button feedForwardButton;
    private Button backPropButton;
    private TextBox inputBox;
    private TextBox targetBox;
    private double learningRate = 0.1;
    private int numRight;
    private int numTotal;

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "Main" }, new Main());
    }

    public void settings() {
        fullScreen();
    }

    public void draw() {
        background(255);

        // Draw UI components
        feedForwardButton.display();
        backPropButton.display();
        inputBox.display();
        targetBox.display();
        // update method
        inputBox.update();
        targetBox.update();
        
        double x = Math.random() * 2 - 1;
        double y = Math.random() * 2 - 1;

        double output = 0;

        nn.feedForward(new double[] {x, y});
        if (x > 0 == y > 0) {
            output = 1;
        }
        else {
            output = -1;
        }

        if (nn.getOutput()[0] > 0 == output > 0) {
            numRight++;
        }
        numTotal++;

        textSize(50);
        text(numRight / numTotal, width/2, height/2);
        

        nn.backProp(new double[] {output}, learningRate);

        // Draw neural network visualization
        // drawNeuralNetwork();
    }

    public void setup() {
        nn = new NeuralNetwork(new int[] {2, 5, 1}, Function.SIGMOID);
        feedForwardButton = new Button("Feed Forward", 50, 50, 100, 30, this);
        backPropButton = new Button("Back Propagate", 200, 50, 120, 30, this);
        inputBox = new TextBox(50, 100, 100, 30, this);
        targetBox = new TextBox(200, 100, 100, 30, this);
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
        if (feedForwardButton.isClicked()) {
            double[] inputs = parseInputs(inputBox.getText());
            nn.feedForward(inputs);
        }
        if (backPropButton.isClicked()) {
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
