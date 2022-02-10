package GJIM;

import java.io.FileWriter;
import java.io.IOException;

/**
 * RGB Pixel.
 * @author François-Nicolas G.
 * @author Étienne Ménard
 */
public class PPMPixel implements Pixel {

    private int valR, valG, valB;

    /**
     * Pixel constructor.
     * @param r red value
     * @param g green value
     * @param b blue value
     */
    public PPMPixel(int r, int g, int b) {
        valR = r;
        valG = g;
        valB = b;
    }

    /**
     * Returns the red value of the pixel.
     * @return Red value
     */
    public int getValR() {
        return valR;
    }

    /**
     * Sets the red value of the pixel.
     * @param val new value
     */
    public void setValR(int val) {
        this.valR = val;
    }

    /**
     * Returns the blue value of the pixel.
     * @return Blue value
     */
    public int getValB() {
        return valB;
    }

    /**
     * Sets the blue value of the pixel
     * @param val new value
     */
    public void setValB(int val) {
        this.valB = val;
    }

    /**
     * Returns the green value of the pixel.
     * @return Green value
     */
    public int getValG() {
        return valG;
    }

    /**
     * Sets the green value of the pixel.
     * @param val new value
     */
    public void setValG(int val) {
        this.valG = val;
    }

    /**
     * Gets all the info of the pixel.
     * @return Pixel values array
     */
    public int[] getPixel() {
        return new int[]{ this.valR, this.valG, this.valB };
    }

    /**
     * Sets the pixel values as the values inside pVals.
     * @param pVals Array of values with the new pixel value
     */
    public void setPixel(int[] pVals) {
        this.valR = pVals[0];
        this.valG = pVals[1];
        this.valB = pVals[2];
    }

    /**
     * Writes a pixel's value(s) to a file.
     * @param fW FileWriter object
     */
    public void writePixel(FileWriter fW) {
        try {
            fW.write(valR + " " + valG + " " + valB + " ");
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Modifies the value of a pixel.
     * @param val amount added or subtracted to the pixel
     * @param maxVal maximum value allowed by the Image
     */
    public void lightenDarken(int val, int maxVal) {

        this.valR += val;
        this.valB += val;
        this.valG += val;

        // rouge
        if (valR < 0)
            valR = 0;
        else if (valR > maxVal)
            valR = maxVal;

        // bleu
        if (valB < 0)
            valB = 0;
        else if (valB > maxVal)
            valB = maxVal;

        // vert
        if (valG < 0)
            valG = 0;
        else if (valG > maxVal)
            valG = maxVal;
    }
}
