void MouseClicked() {
  if (feedForwardButton.isClicked(mouseX, mouseY)) {
    double[] inputs = parseInputs(inputBox.text);
    nn.feedForward(inputs);
  }
  if (backPropButton.isClicked(mouseX, mouseY)) {
    double[] targets = parseInputs(targetBox.text);
    nn.backProp(targets);
  }
}
