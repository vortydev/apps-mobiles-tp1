package GJIM;

import java.io.File;

/**
 * Main program calling the Grape Juice Image Manipulation library.
 * @author Étienne Ménard
 */
public class Main
{
    /**
     * Initial method ran in the terminal.
     * @param args
     */
    public static void main(String[] args) {
        // test
        File f1 = new File("./src/img/Sherbrooke_Frontenac_nuit.ppm");
        Image i = new Image();
        i.readFile(f1);

        File f2 = new File("./src/img/poggies.ppm");
        i.writeFile(f2);
    }

    /**
     * Opens a File of PGM or PPM format and loads its data in an Image.
     * @param i Image to load.
     * @param f File to read.
     */
    public void lire(Image i, File f) {
        i.readFile(f);
    }

    /**
     * Writes the data from an Image to a File of PGM or PPM format.
     * @param f File to write to.
     * @param i Image containing data.
     */
    public static void ecrire(File f, Image i) {
        i.writeFile(f);
    }

    /**
     * Copies an image to another.
     * @param i1 Image to copy.
     * @param i2 Copy of i1.
     */
    public static void copier(Image i1, Image i2) {
        i1.copyImage(i2);
    }

    /**
     * Returns le most common color of an Image.
     * @param i Image to read data from.
     */
    public static int[] couleur_preponderante(Image i) {
        return i.getPreponderantCol();
    }

    /**
     * Modifies the value of each Pixel of an Image.
     * @param i Image to manipulate.
     * @param v Amount to increase or decrease Pixels' value by.
     */
    public static void eclaircir_noircir(Image i, int v) {
        i.lightenDarken(v);
    }

    /**
     * Extracts a section of an Image.
     * @param i Image to manipulate.
     * @param p1 Point 1's X position.
     * @param c1 Point 1's Y position.
     * @param p2 Point 2' X position.
     * @param c2 Point 2's Y position.
     * @return Extracted Image.
     */
    public static Image extraire(Image i, int p1, int c1, int p2, int c2) {
        return i.extract(p1, c1, p2, c2);
    }

    /**
     * Reduces the Image by combining pixels.
     * @param i Image to manipulate.
     */
    public static void reduire(Image i) {
        i.reduceImage();
    }

    /**
     * Compares if 2 Images are identical.
     * @param i1 First Image to compare.
     * @param i2 Second Image to compare.
     * @returns if the 2 Images are identical.
     */
    public static boolean sont_identiques(Image i1, Image i2) {
        return i1.isIdentical(i2);
    }

    /**
     * Rotates an Image 90 degrees.
     * @param i Image to manipulate.
     */
    public static void pivoter90(Image i) {
        i.pivot90();
    }
}
