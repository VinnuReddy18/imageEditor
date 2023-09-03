import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.util.*;
import java.lang.*;

public class imageEditor {

    // Printing the pixels of a given Image

    public static void printpixelvalues(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(inImage.getRGB(j, i) + " ");
            }
        }
    }

    // convert to grayscale

    public static BufferedImage convertToGrayScale(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(j, i, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }

    // Rotating the image by 90 degrees to the “RIGHT”

    public static BufferedImage Righttransposeimage(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotatedImage.setRGB(i, j, inImage.getRGB(j, i));
            }
        }
        int index = rotatedImage.getWidth() - 1;
        for (int i = 0; i < rotatedImage.getHeight(); i++) {
            for (int j = 0; j < rotatedImage.getWidth() / 2; j++) {
                Color temp = new Color(rotatedImage.getRGB(j, i));
                rotatedImage.setRGB(j, i, rotatedImage.getRGB(index - j, i));
                rotatedImage.setRGB(index - j, i, temp.getRGB());

            }
        }
        return rotatedImage;
    }

    // Rotating the image by 90 degrees to the “LEFT”

    public static BufferedImage Lefttransposeimage(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotatedImage.setRGB(i, j, inImage.getRGB(j, i));
            }
        }
        int index = rotatedImage.getHeight() - 1;
        for (int j = 0; j < rotatedImage.getWidth(); j++) {
            for (int i = 0; i < rotatedImage.getHeight() / 2; i++) {
                Color temp = new Color(rotatedImage.getRGB(j, i));
                rotatedImage.setRGB(j, i, rotatedImage.getRGB(j, index - i));
                rotatedImage.setRGB(j, index - i, temp.getRGB());

            }
        }

        return rotatedImage;
    }

    // Flipping the image “Horizontally”

    public static BufferedImage horizontallyinvert(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage HinvertImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                HinvertImage.setRGB(width - j - 1, i, inImage.getRGB(j, i));
            }
        }

        return HinvertImage;
    }

    // Flipping the image “VERTICALLY”

    public static BufferedImage verticallyinvert(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage VinvertImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {

                VinvertImage.setRGB(j, height - i - 1, inImage.getRGB(j, i));
            }
        }

        return VinvertImage;
    }

    // brightness
    public static BufferedImage Ibrightness(BufferedImage inImage, int a) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inImage.getRGB(j, i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                red += (a * red) / 100;
                green += (a * green) / 100;
                blue += (a * blue) / 100;
                if (red > 255)
                    red = 255;
                if (green > 255)
                    green = 255;
                if (blue > 255)
                    blue = 255;
                if (red < 0)
                    red = 0;
                if (green < 0)
                    green = 0;
                if (blue < 0)
                    blue = 0;
                Color newpixel = new Color(red, green, blue);
                inImage.setRGB(j, i, newpixel.getRGB());

            }
        }
        return inImage;
    }

    // image blurring
    public static BufferedImage blur(BufferedImage input, int pixels) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < height / pixels; i++) {
            for (int j = 0; j < width / pixels; j++) {

                int red = 0;
                int green = 0;
                int blue = 0;

                for (int k = i * pixels; k < i * pixels + pixels; k++) {
                    for (int l = j * pixels; l < j * pixels + pixels; l++) {
                        Color pixel = new Color(input.getRGB(l, k));
                        red += pixel.getRed();
                        blue += pixel.getBlue();
                        green += pixel.getGreen();
                    }
                }

                int finalRed = red / (pixels * pixels);
                int finalGreen = green / (pixels * pixels);
                int finalBlue = blue / (pixels * pixels);

                for (int k = i * pixels; k < i * pixels + pixels; k++) {
                    for (int l = j * pixels; l < j * pixels + pixels; l++) {
                        Color newPixel = new Color(finalRed, finalGreen, finalBlue);
                        outputImage.setRGB(l, k, newPixel.getRGB());
                    }
                }
            }
        }

        return outputImage;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the correct name of the file: ");
        String S = sc.next();

        File inputFile = new File(S);
        BufferedImage inputImage = ImageIO.read(inputFile);

        System.out.println("Enter 1 for flipping the image VERTICALLY");
        System.out.println("Enter 2 for flipping the image HORIZONTALLY");
        System.out.println("Enter 3 for rotating the image by 90 Deg to LEFT");
        System.out.println("Enter 4 for rotating the image by 90 Deg to RIGHT");
        System.out.println("Enter 5 for PIXEL VALUES of image");
        System.out.println("Enter 6 for increasing the BRIGHTNESS of the Image");
        System.out.println("Enter 7 for converting the Image into GrayScale(or)Black & White");
        System.out.println("Enter 8 for blurring the image");

        int operation = sc.nextInt();
        if (operation == 1) {
            BufferedImage result = verticallyinvert(inputImage);
            File output = new File("output.jpg");
            ImageIO.write(result, "jpg", output);
        } else if (operation == 2) {
            BufferedImage result = horizontallyinvert(inputImage);
            File output = new File("output.jpg");
            ImageIO.write(result, "jpg", output);

        } else if (operation == 3) {
            BufferedImage result = Lefttransposeimage(inputImage);
            File output = new File("output.jpg");
            ImageIO.write(result, "jpg", output);
        } else if (operation == 4) {
            BufferedImage result = Righttransposeimage(inputImage);
            File output = new File("output.jpg");
            ImageIO.write(result, "jpg", output);
        } else if (operation == 5) {
            printpixelvalues(inputImage);
        } else if (operation == 6) {
            System.out.println("Could you please mention the percentage of Brightness by which you need to increase?");
            int a = sc.nextInt();
            BufferedImage result = Ibrightness(inputImage, a);
            File output = new File("output.jpg");
            ImageIO.write(result, "jpg", output);

        } else if (operation == 7) {
            BufferedImage result = convertToGrayScale(inputImage);
            File output = new File("output.jpg");
            ImageIO.write(result, "jpg", output);
        } else if (operation == 8) {
            System.out.println("Type the Number of pixels that you want to Blur");
            int pixels = sc.nextInt();
            BufferedImage BlurredImage = blur(inputImage, pixels);
            File output = new File("output.jpg");
            ImageIO.write(BlurredImage, "jpg", output);

        }

    }
}