package test.statistics;

import bg.sofia.uni.fmi.mjt.shopping.portal.comparators.PriceStatisticsComparator;
import bg.sofia.uni.fmi.mjt.shopping.portal.PriceStatistic;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class PriceStatisticsComparatorTest {
  @Test
  public void testIfComparingIsCorrect() {
    PriceStatistic statistic1 = new PriceStatistic(LocalDate.now());
    PriceStatistic statistic2 = new PriceStatistic(LocalDate.of(2018, Month.APRIL, 26));
    System.out.println(LocalDate.now().compareTo(LocalDate.of(2018, Month.APRIL, 26)));
    System.out.println(new PriceStatisticsComparator().compare(statistic1, statistic2));
    Assert.assertTrue(
        "Two price statistics classes are compared in descending order.",
        new PriceStatisticsComparator().compare(statistic1, statistic2) < 0);
  }
}
