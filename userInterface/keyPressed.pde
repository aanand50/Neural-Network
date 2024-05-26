void keyPressed() {
  if (key == '\b') {
    if (inputBox.isActive()) inputBox.removeLastChar();
    if (targetBox.isActive()) targetBox.removeLastChar();
  } else {
    if (inputBox.isActive()) inputBox.appendText(key);
    if (targetBox.isActive()) targetBox.appendText(key);
  }
}

double[] parseInputs(String input) {
  String[] parts = input.split(",");
  double[] result = new double[parts.length];
  for (int i = 0; i < parts.length; i++) {
    result[i] = Double.parseDouble(parts[i]);
  }
  return result;
}
