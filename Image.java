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
        parent.text(label, x, y - 40);
    }

    public int getLabel() {
        return label;
    }

    public double[] getPixels() {
        return pixels;
    }
}
