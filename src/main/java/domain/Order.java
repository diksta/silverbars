package domain;

import java.math.BigDecimal;

public class Order {

    private UserId userId;
    private int quantity;
    private BigDecimal price;
    private OrderType type;

    public Order(UserId userId, int quantity, BigDecimal price, OrderType type) {
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderType getType() {
        return type;
    }
}
