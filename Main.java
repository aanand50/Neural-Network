import processing.core.*;

public class Main extends PApplet {
    private NeuralNetwork nn;
    private Button finishTrainingButton;
    private Button randomButton;
    private boolean trainDone;
    private String nnOutput;
    private int digitIndex;
    private double learningRate = 0.3;
    private int numRight;
    private int numTotal;
    private int tempRight;
    private int tempTotal;
    private Image[] images;

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "Main" }, new Main());
    }

    public void settings() {
        // fullScreen();
        size(1920,1080);
    }

    public void draw() {
        background(255);

        // Draw UI components
        rectMode(CORNER);
        stroke(255);
        fill(19, 131, 137);
        rect(0, 0, 205, 215);
        fill(0);
        textSize(24);
        text("NN Interface", 100, 50);
        finishTrainingButton.display();
        randomButton.display();
        // update method

        textSize(50);
        text("Total Iterations: " + numTotal, width / 2 + 500, height / 2 - 200);
        text("Overall percentage: " + percentage(numRight, numTotal) + "%", width / 2 + 500, height / 2 - 100);
        text("Running percentage: " + percentage(tempRight, tempTotal) + "%", width / 2 + 500, height / 2);
        textSize(30);
        text("NN Guess: " + nnOutput, 900, 500);
        textSize(15);

        if (images != null) {
            images[digitIndex].draw(82, 380, 5, 5);
        }

        // neural network visualization
        nn.visualization();
    }

    public void setup() {
        nn = new NeuralNetwork(new int[] { 28 * 28, 15, 13, 10 }, Function.SIGMOID, this);
        finishTrainingButton = new Button("Finish", 100, 100, 120, 30, this);
        randomButton = new Button("Random Digit", 100, 150, 120, 30, this);
        System.out.println(nn.toString());
        thread("train");
    }

    public void train() {
        images = Train.load(this);
        while (!trainDone) {
            int i = (int) (Math.random() * images.length);
            int correct = images[i].getLabel();

            nn.feedForward(images[i].getPixels());

            double[] outputs = nn.getOutput();
            int max = 0;
            for (int j = 0; j < 10; j++) {
                if (outputs[j] > outputs[max]) {
                    max = j;
                }
            }

            if (max == correct) {
                numRight++;
                tempRight++;
            }
            numTotal++;
            tempTotal++;

            double[] targets = new double[10];
            targets[correct] = 1;
            nn.backProp(targets, learningRate);

            if (tempTotal == 250) {
                tempTotal = 0;
                tempRight = 0;
            }
        }
    }

    public void mouseClicked() {
        if (finishTrainingButton.isClicked()) {
            trainDone = true;
        }
        if (randomButton.isClicked()) {
            digitIndex = (int) (Math.random() * images.length);
            nn.feedForward(images[digitIndex].getPixels());
            String output = "\n";
            int max = 0;
            double[] outputs = nn.getOutput();
            for (int i = 0; i < 10; i++) {
                output += i + ": " + percentage((int) (outputs[i] * 1000), 1000) + "%\n";
                if (outputs[i] > outputs[max]) {
                    max = i;
                }
            }
            output = max + output;
            nnOutput = output;
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
