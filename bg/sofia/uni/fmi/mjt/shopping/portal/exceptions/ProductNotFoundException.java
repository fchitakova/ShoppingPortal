package bg.sofia.uni.fmi.mjt.shopping.portal.exceptions;

public class ProductNotFoundException extends Exception {
    public static final String PRODUCT_NOT_FOUND = "There is not any product with this name";

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String errMessage, Throwable err) {
        super(errMessage, err);
    }
}
