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
        parent.rect(x, y, w, h);
        parent.fill(0);
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);
        parent.text(label, x + w / 2, y + h / 2);
    }

    public boolean isClicked() {
        return parent.mouseX > x && parent.mouseX < x + w && parent.mouseY > y && parent.mouseY < y + h;
    }
}
