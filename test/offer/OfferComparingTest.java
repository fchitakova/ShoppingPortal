package test.offer;

import bg.sofia.uni.fmi.mjt.shopping.portal.comparators.OfferPriceComparator;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.Offer;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.PremiumOffer;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.RegularOffer;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class OfferComparingTest {
    @Test
    public void testComparingRegularOffersOffersWithOfferPriceComparator() {
        Offer offer1 = new RegularOffer("dummyName", LocalDate.now(), "dummyDescription", 3.14, 1.11);
        Offer offer2 = new RegularOffer("dummyName", LocalDate.now(), "dummyDescription", 3.15, 1.11);
        Assert.assertTrue(
                "The comparison is in ascending order",
                new OfferPriceComparator().compare(offer1, offer2) < 0);
    }

    @Test
    public void testComparingPremiumOffersWithOfferPriceComparator() {
        Offer offer1 =
                new PremiumOffer("dummyName", LocalDate.now(), "dummyDescription", 3.14, 1.11, 0.2);
        Offer offer2 =
                new PremiumOffer("dummyName", LocalDate.now(), "dummyDescription", 3.15, 1.11, 0.4);

        System.out.println(offer1.getTotalPrice());
        System.out.println(offer2.getTotalPrice());
        Assert.assertTrue(
                "Correct comparison of premium offers",
                new OfferPriceComparator().compare(offer1, offer2) < 0);
    }
}
