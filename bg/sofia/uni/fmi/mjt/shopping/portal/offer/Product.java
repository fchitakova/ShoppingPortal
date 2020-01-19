package bg.sofia.uni.fmi.mjt.shopping.portal.offer;

import bg.sofia.uni.fmi.mjt.shopping.portal.PriceStatistic;

import java.util.Objects;

public class Product {
    private String name;
    private String description;
    private PriceStatistic productPriceStatistics;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    String getName() {
        return this.name;
    }

    String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return name.equalsIgnoreCase(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
