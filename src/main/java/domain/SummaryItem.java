package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

public class SummaryItem implements Comparable<SummaryItem> {
    private Kilograms quantity;
    private BigDecimal price;

    public SummaryItem(Kilograms quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return quantity + " for £" + price;
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

    public Kilograms getQuantity() {
        return quantity;
    }
}
