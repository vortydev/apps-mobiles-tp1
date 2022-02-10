package GJIM;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PGMPixel implements Pixel {

    private int val;

    public PGMPixel(int v) {
        val = v;
    }

    /**
     * Returns the pixel value of the pixel
     * @return Pixel value
     */
    public int getVal() {
        return val;
    }

    /**
     * Sets the pixel value of the pixel
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
     * Sets the pixel value as the value inside pVals
     * @param pVals Array of values with the new pixel value
     */
    public void setPixel(int[] pVals) {
        this.val = pVals[0];
    }

    /**
     * Writes a pixel using the fileWriter
     * @param fW FileWriter linked to a file to write to
     */
    public void writePixel(FileWriter fW) {

        try {
            fW.write(val);
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Lightens or darkens a pixel by a value val
     * @param val Amount to add to the pixel value
     */
    public void lightenDarken(int val, int maxVal) {
        this.val += val;
        if (this.val < 0)
            this.val = 0;
        else if (this.val > maxVal)
            this.val = maxVal;
    }
}
