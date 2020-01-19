package bg.sofia.uni.fmi.mjt.shopping.portal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Holds price statistics about lowest price and average price for a given date.
 */
public class PriceStatistic {

    private LocalDate date;
    private List<Double> prices;
    private double averagePrice;

    public PriceStatistic(LocalDate date) {
        this.date = date;
        this.prices = new ArrayList<>();
    }

    /**
     * Returns the date for which the statistic is collected.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the lowest total price from the offers for this product for the specific date.
     */
    public double getLowestPrice() {
        return this.calculateLowestPrice();
    }

    private double calculateLowestPrice() {
        double min = this.prices.get(0);
        for (Double price : prices) {
            if (price < min) {
                min = price;
            }
        }
        return min;
    }

    /**
     * Return the average total price from the offers for this product for the specific date.
     */
    public double getAveragePrice() {
        return this.averagePrice;
    }

    /**
     * Adds prices to statistic.
     *
     * @param prices list holding prices
     */
    public void addAll(List<Double> prices) {
        this.prices.addAll(prices);
        calculateAveragePrice();
    }

    /**
     * Adds new price to the statistic and recalculates the average.
     *
     * @param price
     */
    public void addNewPrice(double price) {
        this.prices.add(price);
        this.calculateAveragePrice();
    }

    private void calculateAveragePrice() {
        double average = 0.0;
        for (Double price : prices) {
            average += price;
        }
        this.averagePrice = average / prices.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PriceStatistic statistic = (PriceStatistic) o;
        return Objects.equals(date, statistic.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    public List<Double> getPrices() {
        return prices;
    }
}
