class TextBox {
  int x, y, w, h;
  String text = "";
  
  TextBox(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }
  
  void display() {
    fill(255);
    rect(x, y, w, h);
    fill(0);
    textAlign(LEFT, CENTER);
    text(text, x + 5, y + h/2);
  }
  
  void appendText(char c) {
    text += c;
  }
  
  void removeLastChar() {
    if (text.length() > 0) {
      text = text.substring(0, text.length() - 1);
    }
  }
}
