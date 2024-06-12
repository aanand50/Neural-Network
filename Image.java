import processing.core.PApplet;

public class Image {
    private int label;
    private double[] pixels;
    private PApplet parent;

    public Image(int label, double[] pixels, PApplet parent) {
        this.label = label;
        this.pixels = pixels;
        this.parent = parent;
    }

    public void draw(int x, int y, int w, int h) {
        for (int i = 0; i < pixels.length; i++) {
            double val = pixels[i];
            parent.fill((int) (val * 255));
            parent.rect(x + (i % 28) * w, y + (i / 28) * w, w, h);
        }
        parent.textSize(32);
        parent.text("Image", x + w * 14, y - 30);
        parent.text("Number", x + w * 14, y + w * 28 + 40);
        parent.textSize(48);
        parent.text(label, x + w * 14, y + w * 28 + 90);
    }

    public void update(int x, int y, int w, int h) {
        if (parent.mousePressed) {
            int mx = parent.mouseX;
            int my = parent.mouseY;
            if (mx > x && mx < x + w * 28 && my > y && my < y + h * 28) {
                int i = (mx - x) / w + (my - y) / h * 28;
                if (parent.mouseButton == PApplet.LEFT) {
                    pixels[i] = 1;
                } else if (parent.mouseButton == PApplet.RIGHT) {
                    pixels[i] = 0;
                }
            }
        }
    }

    public int getLabel() {
        return label;
    }

    public double[] getPixels() {
        return pixels;
    }
}
