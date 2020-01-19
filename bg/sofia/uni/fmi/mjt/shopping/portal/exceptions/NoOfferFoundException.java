package bg.sofia.uni.fmi.mjt.shopping.portal.exceptions;

public class NoOfferFoundException extends Exception {
    public static final String NOT_ANY_OFFER_FOUND = "There are not any submitted offers " +
            "for this product in the last 30 days!";

    public NoOfferFoundException(String message) {
        super(message);
    }

    public NoOfferFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
