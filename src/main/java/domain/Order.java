package domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private final String orderId;

    private String userId;
    private int grams;
    private BigDecimal price;
    private OrderType type;

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

    public String getUserId() { return userId; }

    public int getGrams() { return grams; }

    public BigDecimal getPrice() { return price; }

    public OrderType getType() { return type; }

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
