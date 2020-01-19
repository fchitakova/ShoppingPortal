package bg.sofia.uni.fmi.mjt.shopping.portal.comparators;

import bg.sofia.uni.fmi.mjt.shopping.portal.PriceStatistic;

import java.util.Comparator;

public class PriceStatisticsComparator implements Comparator<PriceStatistic> {
    @Override
    public int compare(PriceStatistic o1, PriceStatistic o2) {
        if (o1.getDate().compareTo(o2.getDate()) > 0) {
            return -1;
        }
        if (o1.getDate().compareTo(o2.getDate()) < 0) {
            return 1;
        }
        return 0;
    }

}
