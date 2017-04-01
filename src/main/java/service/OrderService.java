package service;

import domain.Order;
import domain.OrderType;
import domain.Summary;
import domain.SummaryItem;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.OrderType.*;

public class OrderService {

    Map<BigDecimal, Order> orders = new HashMap<>();

    public void register(Order order) {
        orders.compute(order.getPrice(), (k,v) -> collateOrders(v, order));
    }

    private Order collateOrders(Order existingOrder, Order newOrder) {
        return newOrder;
    }

    public Summary summary() {
        return new Summary(filterOrders(BUY), filterOrders(SELL));
    }

    private List<SummaryItem> filterOrders(OrderType orderType) {
        return orders.values().stream()
                .filter(order -> order.getType() == orderType)
                .map(o -> new SummaryItem(o.getQuantity(), o.getPrice()))
                .sorted()
                .collect(Collectors.toList());
    }
}
