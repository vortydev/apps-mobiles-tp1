package GJIM;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public interface Pixel {

    int[] getPixel();
    void setPixel(int[] pVals);

    void writePixel(FileWriter fW);

    void lightenDarken(int val, int maxVal);
}
