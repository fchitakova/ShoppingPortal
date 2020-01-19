package bg.sofia.uni.fmi.mjt.shopping.portal.offer;

import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.InvalidDiscountNumberException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class PremiumOffer extends RegularOffer {

    private static final String DISCOUNT_PRECISION_REGEX = "\\d+(\\.\\d{2})";
    private static final double MIN_DISCOUNT = 0.00;
    private static final double MAX_DISCOUNT = 100.00;

    private double discountPercent;

    public PremiumOffer(
            String productName,
            LocalDate date,
            String description,
            double price,
            double shippingPrice,
            double discount) {
        super(productName, date, description, price, shippingPrice);
        this.setDiscount(discount);
    }

    public static double getDiscountWithRightPresition(double discount) {
        if (Double.toString(discount).matches(DISCOUNT_PRECISION_REGEX)) {
            return discount;
        }
        BigDecimal discountWithPrecision = new BigDecimal(discount).setScale(2, RoundingMode.HALF_UP);
        return discountWithPrecision.doubleValue();
    }

    private void setDiscount(double discount) {
        if (discount < MIN_DISCOUNT || discount > MAX_DISCOUNT) {
            throw new InvalidDiscountNumberException(
                    "Price discount should be in the range of [0.00,100.00]");
        }
        this.discountPercent = getDiscountWithRightPresition(discount);
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = this.price + this.shippingPrice;
        double discount = totalPrice * (discountPercent / MAX_DISCOUNT);
        return totalPrice - discount;
    }
}
