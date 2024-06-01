import processing.core.*;

public class Main extends PApplet {
    private NeuralNetwork nn;
    private Button feedForwardButton;
    private Button backPropButton;
    private TextBox inputBox;
    private TextBox targetBox;
    private double learningRate = 0.3;
    private int numRight;
    private int numTotal;
    private int tempRight;
    private int tempTotal;

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

        nn.feedForward(new double[] { x, y });
        if (x > 0 == y > 0) {
            output = 1;
        } else {
            output = -1;
        }

        if (nn.getOutput()[0] > 0 == output > 0) {
            numRight++;
            tempRight++;
        }
        numTotal++;
        tempTotal++;

        textSize(50);
        text("Overall percentage: " + percentage(numRight, numTotal) + "%", width / 2, height / 2 - 100);
        text("Runnimg percentage: " + percentage(tempRight, tempTotal) + "%", width / 2, height / 2);
        text("Total: " + numTotal, width / 2, height / 2 - 200);
        textSize(15);

        nn.backProp(new double[] { output }, learningRate);

        if (tempTotal == 250) {
            tempTotal = 0;
            tempRight = 0;
        }

        // Draw neural network visualization
        // drawNeuralNetwork();
    }

    public void setup() {
        nn = new NeuralNetwork(new int[] { 2, 5, 2, 1 }, Function.TANH);
        feedForwardButton = new Button("Feed Forward", 50, 50, 100, 30, this);
        backPropButton = new Button("Back Propagate", 200, 50, 120, 30, this);
        inputBox = new TextBox(50, 100, 100, 30, this);
        targetBox = new TextBox(200, 100, 100, 30, this);
        System.out.println(nn.toString());
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

    private double percentage(int num, int den) {
        return Math.floor((double) num / den * 1000) / 10;
    }
}
