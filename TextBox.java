import processing.core.*;

public class TextBox {
    private int x, y, w, h;
    private boolean isActive;
    private String text = "";
    private PApplet parent;

    public TextBox(int x, int y, int w, int h, PApplet parent) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.parent = parent;
    }

    public void display() {
        if (isActive()) {
            parent.fill(200);
        }
        else {
            parent.fill(255);
        }
        parent.rect(x, y, w, h);
        parent.fill(0);
        parent.textSize(15);
        parent.textAlign(PApplet.LEFT, PApplet.CENTER);
        parent.text(text, x + 5, y + h / 2);
         
    }
    
    public void update() {
        if (parent.mousePressed) {
            isActive = parent.mouseX > x && parent.mouseX < x + w && parent.mouseY > y && parent.mouseY < y + h;
        }
    }

    public void appendText(char c) {
        text += c;
    }

    public void removeLastChar() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

    public String getText() {
        return text;
    }

    public boolean isActive() {
        return isActive;
    }
}
