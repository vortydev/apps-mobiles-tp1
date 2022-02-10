//package GJIM;

/**
 * Object pointing to a file containing image data.
 * @author Étienne Ménard
 */
public class File
{
    String path;

    /**
     * File constructor.
     * @param p File's path.
     */
    public File(String p) {
        path = p;
    }

    /**
     * Gets the File's path.
     * @return File's path.
     */
    public String getPath() {
        return path;
    }
}
