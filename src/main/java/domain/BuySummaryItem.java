package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

public class BuySummaryItem implements SummaryItem, Comparable<BuySummaryItem>{
    private int quantity;
    private BigDecimal price;

    public BuySummaryItem(int quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return (quantity / GRAMS_IN_KG) + " kg" + " for £" + price;
    }

    @Override
    public int compareTo(BuySummaryItem o) {
        return o.getPrice().subtract(price).intValue();
    }

    public SummaryItem combineQuantities(SummaryItem other) {
        assert price == other.getPrice();
        if (other instanceof BuySummaryItem) {
            return new BuySummaryItem(other.getQuantity() + quantity, price);
        } else {
            int total = quantity - other.getQuantity();
            return total < 0 ? new SellSummaryItem(Math.abs(total), price) : new BuySummaryItem(total, price);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BuySummaryItem summaryItem = (BuySummaryItem) o;

        return new EqualsBuilder()
                .append(quantity, summaryItem.quantity)
                .append(price, summaryItem.price)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(quantity)
                .append(price)
                .toHashCode();
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }
}
