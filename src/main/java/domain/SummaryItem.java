package domain;

import java.math.BigDecimal;

import static domain.OrderType.BUY;

public interface SummaryItem {

    double GRAMS_IN_KG = 100d;

    BuySummaryItem DEFAULT_SUMMARY_ITEM = new BuySummaryItem(0, BigDecimal.ZERO);

    static SummaryItem from(Order order) {
        return order.type == BUY ? new BuySummaryItem(order.grams, order.price) : new SellSummaryItem(order.grams, order.price);
    }

    SummaryItem combineQuantities(SummaryItem other);

    BigDecimal getPrice();

    int getQuantity();
}
