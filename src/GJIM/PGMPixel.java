package GJIM;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Black and white Pixel.
 * @author François-Nicolas G.
 * @author Étienne Ménard
 */
public class PGMPixel implements Pixel
{
    private int val;

    /**
     * Pixel constructor.
     * @param v Pixel's value
     */
    public PGMPixel(int v) {
        val = v;
    }

    /**
     * Returns the pixel value of the pixel.
     * @return Pixel value
     */
    public int getVal() {
        return val;
    }

    /**
     * Sets the pixel value of the pixel.
     * @param val new value
     */
    public void setVal(int val) {
        this.val = val;
    }

    /**
     * Returns pixel info
     * @return Pixel Info
     */
    public int[] getPixel() {
        return new int[]{ this.val };
    }

    /**
     * Sets the pixel value as the value inside pVals.
     * @param pVals Array of values with the new pixel value
     */
    public void setPixel(int[] pVals) {
        this.val = pVals[0];
    }

    /**
     * Writes a pixel's value(s) to a file.
     * @param fW FileWriter object
     */
    public void writePixel(FileWriter fW) {
        try {
            fW.write(val + " ");
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
        this.val += val;
        if (this.val < 0)
            this.val = 0;
        else if (this.val > maxVal)
            this.val = maxVal;
    }
}
