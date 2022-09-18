package vsu.cs.server.utils.image_processing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class BasicImageProcessingUtil implements ImageProcessing {
    @Override
    public BufferedImage getImage(String path) throws IOException {
        return ImageIO.read(new File(path));
    }

    @Override
    public BufferedImage editImage(BufferedImage image, Integer coefficient) {
        return replaceWithWhiteColor(image, coefficient);
    }

    public static BufferedImage replaceWithWhiteColor(BufferedImage image, Integer coefficient) {
        coefficient *= 2;
        int[] rgb = new int[3];
        int width = image.getWidth();
        int height = image.getHeight();
        int minx = image.getMinX();
        int miny = image.getMinY();
        for (int i = minx; i < width; i++)
            for (int j = miny; j < height; j++) {
                int pixel = image.getRGB(i, j);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println(i + " " + j);
                System.out.println(Arrays.toString(rgb));
                for (int k = 0; k < rgb.length; k++) {
                    rgb[k] = Math.min(rgb[k] * coefficient / 100, 255);
                }
                image.setRGB(i, j, new Color(rgb[0], rgb[1], rgb[2]).getRGB());
                System.out.println(Arrays.toString(rgb));
            }
        System.out.println(coefficient);
        return image;
    }
}
