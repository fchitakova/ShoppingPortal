package test.statistics;

import bg.sofia.uni.fmi.mjt.shopping.portal.PriceStatistic;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PriceStatisticsTest {

  @Test
  public void givenPriceStatisticWhenNoPriceIsAddedThenAverageIsZero() {
    PriceStatistic priceStatistic = new PriceStatistic(LocalDate.now());
    assertEquals("Average price should be zero", priceStatistic.getAveragePrice(), 0.00, 0.00);
  }

  @Test
  public void testCalculatingAveragePrice() {
    PriceStatistic priceStatistic = new PriceStatistic(LocalDate.now());
    priceStatistic.addAll(Arrays.asList(4.32, 232.12, 23.1, 123.11, 12.11));
    assertEquals(
        "Price statistics should be calculated correctly",
        78.952,
        priceStatistic.getAveragePrice(),
        0.01);
  }

  @Test
  public void testGettingLowestPrice() {
    PriceStatistic priceStatistic = new PriceStatistic(LocalDate.now());
    priceStatistic.addAll(Arrays.asList(4.32, 232.12, 23.1, 123.11, 12.11));
    assertEquals(
        "The correct lowest price should be returned", 4.32, priceStatistic.getLowestPrice(), 0.01);
  }
}
