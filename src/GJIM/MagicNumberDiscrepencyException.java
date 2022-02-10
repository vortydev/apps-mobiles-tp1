package GJIM;

/**
 * Custom organic 100% biodegradable Exception for verifying an Image's magic number with it's file's extension.
 * @author Étienne Ménard
 */
public class MagicNumberDiscrepencyException extends Exception
{
    public MagicNumberDiscrepencyException() {}
    public MagicNumberDiscrepencyException(String message) {
        super(message);
    }
}