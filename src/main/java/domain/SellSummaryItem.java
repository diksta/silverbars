package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

public class SellSummaryItem implements SummaryItem, Comparable<SellSummaryItem> {
    private int quantity;
    private BigDecimal price;

    public SellSummaryItem(int quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return (quantity / GRAMS_IN_KG) + " kg" + " for £" + price;
    }

    @Override
    public int compareTo(SellSummaryItem o) {
        return price.subtract(o.getPrice()).intValue();
    }

    public SummaryItem combineQuantities(SummaryItem other) {
        assert price == other.getPrice();
        if (other instanceof SellSummaryItem) {
            return new SellSummaryItem(other.getQuantity() + quantity, price);
        } else {
            int total = other.getQuantity() - quantity;
            return total < 0 ? new SellSummaryItem(Math.abs(total), price) : new BuySummaryItem(total, price);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SellSummaryItem summaryItem = (SellSummaryItem) o;

        return new EqualsBuilder()
                .append(quantity, summaryItem.getQuantity())
                .append(price, summaryItem.getPrice())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(quantity)
                .append(price)
                .toHashCode();
    }

    @Override
    public BigDecimal getPrice() { return price; }

    @Override
    public int getQuantity() { return quantity; }
}
