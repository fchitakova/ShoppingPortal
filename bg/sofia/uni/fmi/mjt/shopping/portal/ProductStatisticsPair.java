package bg.sofia.uni.fmi.mjt.shopping.portal;

import bg.sofia.uni.fmi.mjt.shopping.portal.comparators.PriceStatisticsComparator;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.Offer;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.Product;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Holds a pair of a Product and ordered Collection of PriceStatistics. Two ProductStatisticsPair
 * are semantically equal if the products for which they are is the same.
 */
public class ProductStatisticsPair {
    private Product product;
    private TreeSet<PriceStatistic> statistics;


    public ProductStatisticsPair(String productName) {
        this.product = new Product(productName);
        this.statistics = new TreeSet<>(new PriceStatisticsComparator());
    }

    /**
     * Takes a product and statistics comparator.
     *
     * @param product
     * @param statisticsComparator is used for ordering the statistics
     */
    public ProductStatisticsPair(Product product, Comparator<PriceStatistic> statisticsComparator) {
        this.product = product;
        this.statistics = new TreeSet<>(statisticsComparator);
    }

    public void addStatisticsForNewOffer(Offer offer) {
        LocalDate offerDate = offer.getDate();
        for (PriceStatistic ps : statistics) {
            if (ps.getDate().equals(offerDate)) {
                ps.addNewPrice(offer.getTotalPrice());
                return;
            }
        }
        PriceStatistic priceStatisticToAdd = new PriceStatistic(offerDate);
        priceStatisticToAdd.addNewPrice(offer.getTotalPrice());
        this.statistics.add(priceStatisticToAdd);
    }

    public Product getProduct() {
        return this.product;
    }


    public void addAll(List<PriceStatistic> priseStatistics) {
        this.statistics.addAll(priseStatistics);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductStatisticsPair that = (ProductStatisticsPair) o;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    public TreeSet<PriceStatistic> getStatistics() {
        return statistics;
    }
}
