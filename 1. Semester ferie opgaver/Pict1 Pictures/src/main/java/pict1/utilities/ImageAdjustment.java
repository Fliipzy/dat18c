package pict1.utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageAdjustment
{
    public static BufferedImage getTintedImage(BufferedImage image, Color tintColor)
    {
        int rgb;
        int r,g,b;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) 
            {
                rgb = image.getRGB(x, y);
                r = (((rgb >> 16) & 0xff) + tintColor.getRed()) / 2;
                g = (((rgb >> 8) & 0xff) + tintColor.getGreen()) / 2;
                b = ((rgb & 0xff) + tintColor.getBlue()) / 2;

                image.setRGB(x, y, (r << 16) | (g << 8) | b);
            }
        }
        return image;
    }
}