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
        Tuple combined = (type == other.type) ? new Tuple(other.quantity + quantity, type) : combineMismatchingQuantities(quantity, type, other.quantity, other.type);
        return new SummaryItem(combined.total, price, combined.type);
    }

    private static Tuple combineMismatchingQuantities(int quantity1, OrderType type1, int quantity2, OrderType type2) {
        int first = type1 == BUY ? quantity1 : -quantity1;
        int second = type2 == BUY ? quantity2 : -quantity2;
        int total = first + second;
        return total < 0 ? new Tuple(Math.abs(total), SELL) : new Tuple(total, BUY);
    }

    private static class Tuple {
        int total;
        private OrderType type;

        public Tuple(int total, OrderType type) {
            this.total = total;
            this.type = type;
        }
    }
}
