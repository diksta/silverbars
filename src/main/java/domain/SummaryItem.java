package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.Comparator;

import static domain.OrderType.*;
import static domain.OrderType.BUY;

public class SummaryItem implements Comparable<SummaryItem>  {

    double GRAMS_IN_KG = 100d;

    public static final SummaryItem DEFAULT_SUMMARY_ITEM = new SummaryItem(0, BigDecimal.ZERO, new BuyComparator(), new BuyCombiner(), BUY);

    private int quantity;
    private BigDecimal price;
    private OrderType orderType;

    private Comparator comparator;
    private Combiner combiner;

    public static SummaryItem from(Order order) {
        return order.getType() == BUY ? buy(order.getGrams(), order.getPrice()) : sell(order.getGrams(), order.getPrice());
    }

    public static SummaryItem sell(int sellQuantity, BigDecimal sellPrice) {
        return new SummaryItem(sellQuantity, sellPrice, new SellComparator(), new SellCombiner(), SELL);
    }

    public static SummaryItem buy(int buyQuantity, BigDecimal buyPrice) {
        return new SummaryItem(buyQuantity, buyPrice, new BuyComparator(), new BuyCombiner(), BUY);
    }

    protected SummaryItem(int quantity, BigDecimal price, Comparator comparator, Combiner combiner, OrderType orderType) {
        this.quantity = quantity;
        this.price = price;
        this.comparator = comparator;
        this.combiner = combiner;
        this.orderType = orderType;
    }

    public SummaryItem combineQuantities(SummaryItem other) {
        assert other == DEFAULT_SUMMARY_ITEM || price == other.getPrice();
        return combiner.combine(this, other);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderType getOrderType() {
        return orderType;
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
                .append(quantity, summaryItem.getQuantity())
                .append(price, summaryItem.getPrice())
                .append(orderType, summaryItem.getOrderType())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(quantity)
                .append(price)
                .append(orderType)
                .toHashCode();
    }

    @Override
    public int compareTo(SummaryItem o) {
        return comparator.compare(this, o);
    }

}
