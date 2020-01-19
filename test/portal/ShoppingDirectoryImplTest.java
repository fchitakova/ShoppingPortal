package test.portal;

import bg.sofia.uni.fmi.mjt.shopping.portal.ShoppingDirectory;
import bg.sofia.uni.fmi.mjt.shopping.portal.ShoppingDirectoryImpl;
import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.NoOfferFoundException;
import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.OfferAlreadySubmittedException;
import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.ProductNotFoundException;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.Offer;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.PremiumOffer;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.RegularOffer;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.Set;

public class ShoppingDirectoryImplTest {

    @Test
    public void testGettingAllOffersForTheLastMonth() throws OfferAlreadySubmittedException, ProductNotFoundException {
        ShoppingDirectory testShoppingDir = new ShoppingDirectoryImpl();
        Offer offer1 = new RegularOffer("product1", LocalDate.now(), "description1", 6.55, 0.015);
        Offer offer2 = new PremiumOffer("product1", LocalDate.of(2019, Month.NOVEMBER, 2), "", 123.21, 21, 0.11);
        Offer offer3 = new PremiumOffer("product1", LocalDate.of(2017, Month.NOVEMBER, 14), "", 121, 12.11, 34);
        testShoppingDir.submitOffer(offer1);
        testShoppingDir.submitOffer(offer2);
        testShoppingDir.submitOffer(offer3);
        Collection<Offer> offers = testShoppingDir.findAllOffers("product1");
        Assert.assertArrayEquals(Set.of(offer1, offer2).toArray(), offers.toArray());
    }

    @Test(expected = OfferAlreadySubmittedException.class)
    public void testAddingAlreadySubmittedException() throws OfferAlreadySubmittedException {
        ShoppingDirectory testShoppingDir = new ShoppingDirectoryImpl();
        Offer offer2 = new PremiumOffer("product1", LocalDate.of(2019, Month.NOVEMBER, 2), "", 123.21, 21, 0.11);
        Offer offer3 = new PremiumOffer("product1", LocalDate.of(2019, Month.NOVEMBER, 2), "DESCRIPTION", 141, 3.21, 0.11);
        testShoppingDir.submitOffer(offer2);
        testShoppingDir.submitOffer(offer3);
    }

    @Test
    public void testFindingBestOffer() throws OfferAlreadySubmittedException, ProductNotFoundException, NoOfferFoundException {
        ShoppingDirectory testShoppingDir = new ShoppingDirectoryImpl();
        Set<Offer> testOffers = Set.of(new PremiumOffer("product1", LocalDate.of(2019, Month.NOVEMBER, 24), "", 123.21, 21, 0.11),
                new PremiumOffer("product1", LocalDate.of(2019, Month.NOVEMBER, 2), "DESCRIPTION", 141, 3.21, 0.11),
                new RegularOffer("product1", LocalDate.now(), "description1", 6.55, 0.015),
                new PremiumOffer("product1", LocalDate.of(2019, Month.NOVEMBER, 8), "", 123.21, 21, 0.11),
                new RegularOffer("product1", LocalDate.of(2017, Month.NOVEMBER, 14), "", 121, 12.11));

        for (Offer offer : testOffers) {
            testShoppingDir.submitOffer(offer);
        }
        Offer bestOffer = testShoppingDir.findBestOffer("product1");
        Assert.assertEquals(bestOffer, new RegularOffer("product1", LocalDate.now(), "description1", 6.55, 0.015)
        );
    }
}