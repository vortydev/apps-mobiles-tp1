package GJIM;

import java.io.FileWriter;

/**
 * Pixel interface.
 * @author François-Nicolas G.
 */
public interface Pixel {

    /**
     * Returns the pixel's value(s).
     * @return an array of values
     */
    int[] getPixel();

    /**
     * Sets the pixel's value(s).
     * @param pVals array of values
     */
    void setPixel(int[] pVals);

    /**
     * Writes a pixel's value(s) to a file.
     * @param fW FileWriter object
     */
    void writePixel(FileWriter fW);

    /**
     * Modifies the value of a pixel.
     * @param val amount added or subtracted to the pixel
     * @param maxVal maximum value allowed by the Image
     */
    void lightenDarken(int val, int maxVal);
}
