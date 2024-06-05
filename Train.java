import java.io.IOException;
import java.io.InputStream;

import processing.core.PApplet;

public class Train {
    private static final String IMAGES_PATH = "MNIST_Dataset/train-images.idx3-ubyte";
    private static final String LABELS_PATH = "MNIST_Dataset/train-labels.idx1-ubyte";
    private static final int NUM_IMAGES = 60000;
    private static final int IMAGE_SIZE = 28;

    public static Image[] load(PApplet parent) {
        try {
            InputStream imgIn = parent.createInput(IMAGES_PATH);
            InputStream lblIn = parent.createInput(LABELS_PATH);

            byte[] tempBuffer = new byte[16];
            imgIn.read(tempBuffer, 0, 16);
            lblIn.read(tempBuffer, 0, 8);

            byte[] dataBuffer = new byte[1];
            Image[] images = new Image[NUM_IMAGES];
            for (int i = 0; i < NUM_IMAGES; i++) {
                lblIn.read(dataBuffer, 0, 1);
                int label = dataBuffer[0] & 0xFF;

                double[] pixels = new double[IMAGE_SIZE * IMAGE_SIZE];
                for (int j = 0; j < IMAGE_SIZE * IMAGE_SIZE; j++) {
                    imgIn.read(dataBuffer, 0, 1);
                    double pixelVal = (dataBuffer[0] & 0xFF) / 255.f;
                    pixels[j] = pixelVal;
                }

                images[i] = new Image(label, pixels, parent);
            }
            imgIn.close();
            lblIn.close();
            return images;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
