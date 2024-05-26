import processing.core.*;

public class Main extends PApplet {
    public static void main(String[] args) {
        PApplet.runSketch(new String[]{"Main"}, new Main());
    }

    public void settings() {
        size(800, 800);
    }

    public void draw() {
        fill(255);
        ellipse(mouseX, mouseY, 50, 50);
    }
}
