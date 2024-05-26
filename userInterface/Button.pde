class Button {
  String label;
  int x, y, w, h;
  
  Button(String label, int x, int y, int w, int h) {
    this.label = label;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }
  
  void display() {
    fill(200);
    rect(x, y, w, h);
    fill(0);
    textAlign(CENTER, CENTER);
    text(label, x + w/2, y + h/2);
  }
  
  boolean isClicked(int mx, int my) {
    return mx > x && mx < x + w && my > y && my < y + h;
  }
}
