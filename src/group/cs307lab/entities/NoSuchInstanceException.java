package group.cs307lab.entities;

/**
 * @author AnGuangyan
 * @date 2021/3/11
 */
public class NoSuchInstanceException extends RuntimeException {
    private static final String EXCEPTION = "No Such %s, Please add %s Or check your input!";
    private NoSuchInstanceException(String fieldName) {
        super(String.format(EXCEPTION, fieldName, fieldName));
    }

    public static NoSuchInstanceException getInstance(String fieldName) {
        return new NoSuchInstanceException(fieldName);
    }
}
