package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

import static domain.OrderType.BUY;
import static domain.OrderType.SELL;

public class SummaryItem implements Comparable<SummaryItem> {
    private int quantity;
    private BigDecimal price;
    private OrderType type;

    private static final double GRAMS_IN_KG = 100d;

    public SummaryItem(int quantity, BigDecimal price, OrderType type) {
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    @Override
    public String toString() {
        return (quantity / GRAMS_IN_KG) + " kg" + " for £" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SummaryItem summaryItem = (SummaryItem) o;

        return new EqualsBuilder()
                .append(quantity, summaryItem.quantity)
                .append(price, summaryItem.price)
                .append(type, summaryItem.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(quantity)
                .append(price)
                .append(type)
                .toHashCode();
    }

    @Override
    public int compareTo(SummaryItem o) {
        return 0;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderType getType() {
        return type;
    }

    public SummaryItem combineQuantities(SummaryItem other) {
        assert price == other.price;
        return (type == other.type) ? new SummaryItem(other.quantity + quantity, price, type) :
                                             combineMismatchingQuantities(this, other);
    }

    private static SummaryItem combineMismatchingQuantities(SummaryItem si1, SummaryItem si2) {
        int total = getQuantityAndNegateForSell(si1) + getQuantityAndNegateForSell(si2);
        return total < 0 ? new SummaryItem(Math.abs(total), si1.price, SELL) : new SummaryItem(total, si1.price, BUY);
    }

    private static int getQuantityAndNegateForSell(SummaryItem summaryItem) {
        return summaryItem.type == BUY ? summaryItem.quantity : -summaryItem.quantity;
    }

    public static SummaryItem from(int quantity, BigDecimal price, OrderType type) {
        return new SummaryItem(quantity, price, type);
    }
}
