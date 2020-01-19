package bg.sofia.uni.fmi.mjt.shopping.portal.offer;

import java.time.LocalDate;
import java.util.Objects;

public class RegularOffer implements Offer {
    public static final double PRICE_COMPARE_DELTA = 0.0001;
    protected Product product;
    protected LocalDate date;
    protected double price;
    protected double shippingPrice;

    public RegularOffer(
            String productName, LocalDate date, String description, double price, double shippingPrice) {
        this.product = new Product(productName, description);
        this.date = date;
        this.price = price;
        this.shippingPrice = shippingPrice;
    }

    @Override
    public String getProductName() {
        return product.getName();
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String getDescription() {
        return this.toString();
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getShippingPrice() {
        return shippingPrice;
    }

    @Override
    public double getTotalPrice() {
        return price + shippingPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegularOffer other = (RegularOffer) o;
        if (product.equals(other.product)
                && (this.getTotalPrice() - other.getTotalPrice() < PRICE_COMPARE_DELTA)
                && this.date.isEqual(other.date)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, date, getTotalPrice());
    }

}
