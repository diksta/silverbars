package domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private final String orderId;

    protected String userId;
    protected int grams;
    protected BigDecimal price;
    protected OrderType type;

    public Order(String userId, int grams, BigDecimal price, OrderType type) {
        this.orderId = UUID.randomUUID().toString();
        this.userId = userId;
        this.grams = grams;
        this.price = price;
        this.type = type;
    }

    private Order(String orderId) {
        this.orderId = orderId;
    }

    public static Order forId(String orderId) {
        return new Order(orderId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", grams=" + grams +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
