package domain;

import java.math.BigDecimal;

import static domain.OrderType.BUY;

public interface SummaryItem {

    public static final double GRAMS_IN_KG = 100d;

    public static SummaryItem from(int quantity, BigDecimal price, OrderType type) {
        return type == BUY ? new BuySummaryItem(quantity, price) : new SellSummaryItem(quantity, price);
    }

    public SummaryItem combineQuantities(SummaryItem other);

    BigDecimal getPrice();

    int getQuantity();
}
