package test.offer;

import bg.sofia.uni.fmi.mjt.shopping.portal.offer.PremiumOffer;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class OfferTest {

    @Test
    public void testCalculatingDiscountPrecisionForPremiumOffer() {
        PremiumOffer premiumOffer = new PremiumOffer("", LocalDate.now(), "", 2.65687, 0.200, 6.658);
        double initialPrice = 2.85697;
        double discount = (2.85687 * (6.68 / 100));
        double expectedPrice = initialPrice - discount;
        double actualPrice = premiumOffer.getTotalPrice();
        assertEquals("Discount should be calculated correctly", expectedPrice, actualPrice, 0.01);
    }

    //  @Test
    //  public void testSettingDiscountToPremiumOffer() {
    //    Offer premiumOffer = new PremiumOffer("", LocalDate.now(), "", 2.65687, 0.200, 0.658);
    //    System.out.println(premiumOffer.getTotalPrice());
    //    assertEquals("Test if premium offer price is calculated
    // correctly",premiumOffer.getTotalPrice(),2.85687-(0.7*2.85687),0.001);
    //  }
}
