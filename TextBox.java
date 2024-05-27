import processing.core.*;

public class TextBox {
    private int x, y, w, h;
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
        parent.fill(255);
        parent.rect(x, y, w, h);
        parent.fill(0);
        textAlign(LEFT, CENTER);// TODO find out what these numbers are
        parent.text(text, x + 5, y + h / 2);
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
}
