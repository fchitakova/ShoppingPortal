package test.statistics;

import bg.sofia.uni.fmi.mjt.shopping.portal.comparators.PriceStatisticsComparator;
import bg.sofia.uni.fmi.mjt.shopping.portal.PriceStatistic;
import bg.sofia.uni.fmi.mjt.shopping.portal.ProductStatisticsPair;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.Product;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ProductStatisticsTest {

  private static ProductStatisticsPair productStatistics =
      new ProductStatisticsPair(
          new Product("productName", "productDescription"), new PriceStatisticsComparator());

  @Test
  public void testIfAddedElementsAreOrderedCorrectly() {
    PriceStatistic statistic1 = new PriceStatistic(LocalDate.now());
    statistic1.addAll(Arrays.asList(4.32, 232.12, 23.1, 123.11, 12.11));
    PriceStatistic statistic2 = new PriceStatistic(LocalDate.of(2017, Month.APRIL, 15));
    statistic2.addAll(Arrays.asList(8.42, 8.65, 8.6, 3.12, 12.87, 1813.12));
    PriceStatistic statistic3 = new PriceStatistic(LocalDate.of(2019, Month.AUGUST, 30));
    statistic3.addAll(Arrays.asList(1.24, 1234.00, 23.11));
    productStatistics.addAll(List.of(statistic1, statistic2, statistic3));
    assertEquals(
        productStatistics.getStatistics(), Set.of(statistic1, statistic2, statistic3));
  }
}
