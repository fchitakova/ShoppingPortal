package bg.sofia.uni.fmi.mjt.shopping.portal;

import bg.sofia.uni.fmi.mjt.shopping.portal.comparators.OfferPriceComparator;
import bg.sofia.uni.fmi.mjt.shopping.portal.comparators.PriceStatisticsComparator;
import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.NoOfferFoundException;
import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.OfferAlreadySubmittedException;
import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.ProductNotFoundException;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.Offer;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.Product;

import java.time.LocalDate;
import java.util.*;

public class ShoppingDirectoryImpl implements ShoppingDirectory {

    List<Offer> offers = new ArrayList<>();
    private Map<ProductStatisticsPair, List<Offer>> data;

    public ShoppingDirectoryImpl() {
        data = new HashMap<>();
    }

    @Override
    public Collection<Offer> findAllOffers(String productName)
            throws IllegalArgumentException, ProductNotFoundException {
        if (productName == null) {
            throw new IllegalArgumentException();
        }
        ProductStatisticsPair key = new ProductStatisticsPair(productName);
        if (!this.data.containsKey(key)) {
            throw new ProductNotFoundException(ProductNotFoundException.PRODUCT_NOT_FOUND);
        }
        List<Offer> offers = this.data.get(key);
        Set<Offer> result = new TreeSet<>(new OfferPriceComparator());
        LocalDate previousMonth = LocalDate.now().minusMonths(1);
        for (Offer offer : offers) {
            if (offer.getDate().compareTo(previousMonth) > 0) {
                result.add(offer);
            }
        }
        return result;
    }

    @Override
    public Offer findBestOffer(String productName)
            throws IllegalArgumentException, ProductNotFoundException, NoOfferFoundException {
        TreeSet<Offer> offers;
        try {
            offers = (TreeSet<Offer>) findAllOffers(productName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        } catch (ProductNotFoundException e) {
            throw e;
        }
        if (offers.isEmpty()) {
            throw new NoOfferFoundException(NoOfferFoundException.NOT_ANY_OFFER_FOUND);
        }
        return offers.first();
    }

    @Override
    public Collection<PriceStatistic> collectProductStatistics(String productName)
            throws ProductNotFoundException {
        if (productName == null) {
            throw new IllegalArgumentException();
        }
        Product product = new Product(productName);
        for (ProductStatisticsPair psp : data.keySet()) {
            if (psp.getProduct().equals(product)) {
                return psp.getStatistics();
            }
        }
        throw new ProductNotFoundException(ProductNotFoundException.PRODUCT_NOT_FOUND);
    }

    @Override
    public void submitOffer(Offer offer) throws OfferAlreadySubmittedException, NullPointerException {
        if (offer == null) {
            throw new IllegalArgumentException();
        }
        Product product = new Product(offer.getProductName(), offer.getDescription());
        ProductStatisticsPair key = new ProductStatisticsPair(product, new PriceStatisticsComparator());

        for (ProductStatisticsPair psp : this.data.keySet()) {
            if (psp.equals(key)) {
                if (this.data.get(psp).contains(offer)) {
                    throw new OfferAlreadySubmittedException("This offer is already submitted!");
                }
                this.data.get(psp).add(offer);
                psp.addStatisticsForNewOffer(offer);
                return;
            }
        }

        offers.add(offer);
        key.addStatisticsForNewOffer(offer);
        this.data.put(key, offers);
    }
}