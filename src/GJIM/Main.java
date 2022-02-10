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

        // LECTURE
        System.out.println("Tests lecture");
        Image pgm = new Image(); // image vide
        Image ppm = new Image();
        File f1 = new File("./src/img/test.ppm");   // file doesnt exist
        File f2 = new File("./src/img/test.pgm");   // bad magic number
        File f3 = new File("./src/img/Sherbrooke_Frontenac_nuit.pgm");
        File f4 = new File("./src/img/Sherbrooke_Frontenac_nuit.ppm");  // valid

        lire(pgm, f1);  // fail
        lire(pgm, f2);  // fail
        lire(pgm, f3);  // pass
        lire(ppm, f4);  // pass

        // ÉCRITURE
        System.out.println("\nTests écriture");
        File w1 = new File("./src/img/write.pgm");
        File w2 = new File("./src/img/write.ppm");

        ecrire(w1, ppm);    // fail: pgm file ppm img
        ecrire(w2, pgm);    // fail: ppm file pgm img
        ecrire(w1, pgm);    // pass: pgm file pgm img
        ecrire(w2, ppm);    // pass: ppm file ppm img

        // COPIER
        System.out.println("\nTests copie");
        Image i1 = new Image();
        Image i2 = new Image();
        copier(ppm, ppm);   // pass: fait rien
        copier(ppm, i1);    // pass
        copier(pgm, i2);

        // COULEUR PRÉPONDÉRANTE
        System.out.println("\nTests couleur prépondérante");

        int[] col = couleur_preponderante(ppm);
        System.out.println("Image ppm");
        for (int b = 0; b < col.length; b++) {
            System.out.println(col[b] + " ");
        }

        // ECLAIRCIR NOIRCIR
        System.out.println("Image pgm");
        eclaircir_noircir(i2, 10);
        int[] en1 = couleur_preponderante(i2);
        for (int a = 0; a < en1.length; a++) {
            System.out.println(en1[a] + " ");
        }

        eclaircir_noircir(i2, 43);
        int[] en2 = couleur_preponderante(i2);
        for (int a = 0; a < en2.length; a++) {
            System.out.println(en2[a] + " ");
        }

        eclaircir_noircir(i2, -10000);
        int[] en3 = couleur_preponderante(i2);
        for (int a = 0; a < en3.length; a++) {
            System.out.println(en3[a] + " ");
        }

        // extraction
        ppm = extraire(ppm, 125, 75, 220, 120);
        // réduction
        reduire(ppm);

        File e1 = new File("./src/img/poggies.ppm");
        ecrire(e1, i2);

        reduire(pgm);
        reduire(pgm);
        // rotation
        pivoter90(pgm);
        pivoter90(pgm);
        ecrire(f2, pgm);

        // IDENTIQUES
        System.out.println("\nImages identiques");
        System.out.println(sont_identiques(ppm, i1));

        Image identique = new Image();
        copier(ppm, identique);
        System.out.println(sont_identiques(ppm, identique));
    }

    /**
     * Opens a File of PGM or PPM format and loads its data in an Image.
     * @param i Image to load.
     * @param f File to read.
     */
    public static void lire(Image i, File f) {
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
