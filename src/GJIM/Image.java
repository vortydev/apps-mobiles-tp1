package GJIM;
//yo je teste voir si je vais broke
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class Image which implements an image
 * @author Isabelle Rioux
 */
public class Image {
    private String magicNumber;
    private int width;
    private int height;
    private int maxPixelValue;
    private Pixel[][] pixel;

    /**
     * Empty constructor
     */
    public Image() { }

    /**
     * Image constructor
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
     * Gets the Image's magic number
     * @return Image's magic number
     */
    public String getMagicNumber() { return magicNumber; }

    /**
     * Gets the Image's width
     * @return Image's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the Image's height
     * @return Image's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the Image's maxPixelValue
     * @return Image's maxPixelValue
     */
    public int getMaxPixelValue() {
        return maxPixelValue;
    }

    /**
     * Get the Image's pixels
     * @return 2D Pixel array
     */
    public Pixel[][] getPixel() {
        return pixel;
    }

    /**
     * Reads a File to get an Image
     * @param f File to read from to get the Image
     */
    public void readFile(File f){
        // TODO read data and slap into image
        // TODO make sure we have as much data as specified (exceptions)

        try {
            Scanner scan = new Scanner(f);  // opens the file

            magicNumber = scan.nextLine();  // set
            // TODO throw exception if magic number doesn't match file extension

            width = Integer.parseInt(scan.next());          // reads the image's width
            height = Integer.parseInt(scan.next());         // reads the image's height
            maxPixelValue = Integer.parseInt(scan.next());  // reads the image's max pixel value

            // TODO maybe a checksum?

            pixel = new Pixel[width][height];   // initialise 2D array

            switch (magicNumber) {
                case "P2":
                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < width; x++) {
                            pixel[x][y] = new PGMPixel(scan.nextInt());
                        }
                    }
                    break;
                case "P3":
                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < width; x++) {
                            pixel[x][y] = new PPMPixel(scan.nextInt(), scan.nextInt(), scan.nextInt());
                        }
                    }
                    break;
            }


            /*while (scan.hasNextLine()) {
                pixel.readPixel(scan);
//                String data = scan.nextLine();
//                System.out.println(data);
            }*/

            scan.close();   // closes the file
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Writes in a File to save an Image
     * @param f File to write into to save the Image
     */
    public void writeFile(File f) {

    }

    /**
     * Overwrites an IMage with another one
     * @param i Image to copy
     */
    public void copyImage(Image i) {
        i = new Image(magicNumber, width, height, maxPixelValue, pixel);
    }

    /**
     * Gets the value that is the most present into the Image's pixels
     * @return Image's most found value of pixel
     */
    public int[] getPreponderantCol(){
//        return pixel.getPixel();
        return null;
    }

    /**
     * Modifies either the Image neeed to lighten or darken
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
     * Extracts a part of the image to create a new one with given coordinates
     * @param p1 First coordinate of a corner of the area
     * @param c1 Second coordinate of a corner of the area
     * @param p2 First coordinate of the opposite corner of the area
     * @param c2 Second coordinate of the opposite corner of the area
     * @return i The extracted Image
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

    }

    /**
     * Checks if two Images are identical
     * @param i Image to compare
     * @return isTrue Are the Images the same
     */
    public boolean isIdentical(Image i){
        return height == i.getHeight() && width == i.getWidth() && maxPixelValue == i.getMaxPixelValue() && pixel == i.getPixel();
    }

    /**
     * Turns the Image by 90 degrees
     */
    public void pivot90(){
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
