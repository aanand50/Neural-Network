import processing.core.*;

public class Button {
    private String label;
    private int x, y, w, h;
    private PApplet parent;

    Button(String label, int x, int y, int w, int h, PApplet parent) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.parent = parent;
    }

    public void display() {
        parent.fill(200);
        this.hover();
        parent.rectMode(PApplet.CENTER);
        parent.rect(x, y, w, h);
        parent.fill(0);
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);
        parent.textSize(15);
        parent.text(label, x, y - h / 2 + 15);
    }

    public void hover() {
        if (parent.mouseX > x && parent.mouseX < x + w && parent.mouseY > y && parent.mouseY < y + h) {
            parent.fill(180);
        }
        else {
            parent.fill(224);
        }
        // parent.rect(x, y, w, h);
    }

    public boolean isClicked() {
        return parent.mouseX > x && parent.mouseX < x + w && parent.mouseY > y && parent.mouseY < y + h;
    }
}
