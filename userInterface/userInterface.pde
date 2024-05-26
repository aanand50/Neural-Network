import java.util.function.DoubleFunction;
NeuralNetwork nn;
Function activationFunction;
Button feedForwardButton, backPropButton;
TextBox inputBox, targetBox;
NeuralNetwork nn;

void setup() {
  size(800, 600);
  //activationFunction=
  //nn
  feedForwardButton = new Button("Feed Forward", 50, 500, 100, 30);
  backPropButton = new Button("Back Propagate", 200, 500, 120, 30);
  inputBox = new TextBox(50, 450, 100, 30);
  targetBox = new TextBox(200, 450, 100, 30);
}

void draw() {
  background(255);
  
  // Draw UI components
  feedForwardButton.display();
  backPropButton.display();
  inputBox.display();
  targetBox.display();
  
  // Draw neural network visualization
 // drawNeuralNetwork();
}
