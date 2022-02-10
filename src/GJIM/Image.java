package GJIM;
//yo je teste voir si je vais broke
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Object containing data of an Image.
 * @author Étienne Ménard
 * @author Isabelle Rioux
 */
public class Image {
    private String magicNumber;
    private int width;
    private int height;
    private int maxPixelValue;
    private Pixel[][] pixel;

    /**
     * Empty constructor.
     */
    public Image() { }

    /**
     * Constructor with parameters.
     * @param mN magic number
     * @param w image width
     * @param h image height
     * @param mPV image maxPixelValue
     * @param p 2D pixel array
     */
    public Image(String mN, int w, int h, int mPV, Pixel[][] p) {
        magicNumber = mN;
        width = w;
        height = h;
        maxPixelValue = mPV;
        pixel = p;
    }

    /**
     * Gets the Image's magic number.
     * @return Image's magic number
     */
    public String getMagicNumber() { return magicNumber; }

    /**
     * Gets the Image's width.
     * @return Image's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the Image's height.
     * @return Image's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the Image's maxPixelValue.
     * @return Image's maxPixelValue
     */
    public int getMaxPixelValue() {
        return maxPixelValue;
    }

    /**
     * Gets the Image's pixels.
     * @return 2D Pixel array
     */
    public Pixel[][] getPixel() {
        return pixel;
    }

    /**
     * Reads a File to load an Image.
     * @param f File to read from to get the Image
     */
    public void readFile(File f) {
        try {
            Scanner scan = new Scanner(f);  // opens the file

            magicNumber = scan.nextLine();  // set magic number

            MagicNumberVibeCheck(magicNumber, f);

            width = Integer.parseInt(scan.next());          // reads the image's width
            height = Integer.parseInt(scan.next());         // reads the image's height
            maxPixelValue = Integer.parseInt(scan.next());  // reads the image's max pixel value

            pixel = new Pixel[width][height];   // initialise 2D array

            // loads the correct type of pixels depending of the image's type
            switch (magicNumber) {
                // PGM
                case "P2":
                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < width; x++) {
                            pixel[x][y] = new PGMPixel(scan.nextInt());
                        }
                    }
                    break;
                // PPM
                case "P3":
                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < width; x++) {
                            pixel[x][y] = new PPMPixel(scan.nextInt(), scan.nextInt(), scan.nextInt());
                        }
                    }
                    break;
            }

            scan.close();   // closes the file
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Writes an Image's data to a file.
     * @param f File to write an Image to
     */
    public void writeFile(File f) {
        try {
            MagicNumberVibeCheck(magicNumber, f);
            FileWriter writer = null;

            // we try accessing the file
            try {
                 writer = new FileWriter(f.getPath());
            }
            // if it doesn't exist, create it
            catch (FileNotFoundException e) {
                if (f.createNewFile()) {
                    System.out.println("Creating file " + f.getName());
                }
                writer = new FileWriter(f.getPath());
            }

            // writes Image's parameters
            writer.write(magicNumber + "\n");
            writer.write(width + " " + height + "\n");
            writer.write(maxPixelValue + "\n");

            // writes the image's pixels
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pixel[x][y].writePixel(writer);
                }
                writer.write("\n");
            }

            writer.close(); // closes the writer
        }
        catch (IOException e) {
            System.out.println("oops, did a fucky wucky.");
            e.printStackTrace();
        }
    }

    /**
     * Validates the magic number matches the file's extension.
     * @param magic magic number
     * @param f file
     */
    public void MagicNumberVibeCheck(String magic, File f) {
        try {
            // trims file extension
            String fileExtension = f.getName().split("\\.")[1];

            // checks if magic number matches file extension
            switch (magic) {
                case "P2":
                    if (!fileExtension.equals("pgm")) {
                        throw new MagicNumberDiscrepencyException();
                    }
                    break;
                case "P3":
                    if (!fileExtension.equals("ppm")) {
                        throw new MagicNumberDiscrepencyException();
                    }
                    break;
            }
        }
        catch (MagicNumberDiscrepencyException e) {
            System.out.println("The magic number doesn't match the file's extension.");
            e.printStackTrace();
        }
    }

    /**
     * Overwrites an Image with another one.
     * @param i Image to copy onto
     */
    public void copyImage(Image i) {
        i = new Image(magicNumber, width, height, maxPixelValue, pixel);
    }

    /**
     * Gets the value that is the most present into the Image's pixels
     * @return Image's most common pixel value
     */
    public int[] getPreponderantCol() {
        // TODO fn
//        return pixel.getPixel();
        return null;
    }

    /**
     * Modifies the Image's pixels.
     * @param v Value of modification of the pixels
     */
    public void lightenDarken(int v) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel[x][y].lightenDarken(v, maxPixelValue);
            }
        }
    }

    /**
     * Extracts a part of the image to create a new one with given coordinates.
     * @param p1 First coordinate of a corner of the area
     * @param c1 Second coordinate of a corner of the area
     * @param p2 First coordinate of the opposite corner of the area
     * @param c2 Second coordinate of the opposite corner of the area
     * @return Image The extracted Image
     */
    public Image extract(int p1, int c1, int p2, int c2) {
        Image i = new Image(magicNumber, p2-p1, c2-c1, maxPixelValue, null);
        i.pixel = new Pixel[i.getWidth()][i.getHeight()];

        for (int y = c1; y <= c2; y++) {
            for (int x = p1; x <= p2; x++) {
                i.pixel[x - p1][y - c1] = pixel[x][y];
            }
        }

        return i;
    }

    /**
     * Reduces the size of the Image
     */
    public void reduceImage(){
        // TODO fn
    }

    /**
     * Checks if two Images are identical.
     * @param i Image to compare
     * @returns if the Images are identical
     */
    public boolean isIdentical(Image i){
        return height == i.getHeight() && width == i.getWidth() && maxPixelValue == i.getMaxPixelValue() && pixel == i.getPixel();
    }

    /**
     * Rotates the Image 90 degrees.
     */
    public void pivot90() {
        // TODO isa
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                // 4 x 6
                /*Pixel temp = pixel[y][x];
                pixel[y][x]=pixel[x][y];
                pixel[x][y]=temp;*/
            }
        }

        for (int i = 0; i < height; i++){
            for (int j = i; j < width/2; j++){
                Pixel temp = pixel[i][j];
                pixel[i][j]=pixel[i][width-j-1];
                pixel[i][width-j-1]=temp;
            }
        }
    }

}
