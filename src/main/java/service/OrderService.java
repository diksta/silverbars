package service;

import domain.Order;
import domain.OrderType;
import domain.Summary;
import domain.SummaryItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static domain.OrderType.BUY;
import static domain.OrderType.SELL;

public class OrderService {

    List<Order> orders = new ArrayList<>();

    public void register(Order... newOrders) {
        Collections.addAll(orders, newOrders);
    }

    public Summary summary() {
        return new Summary(filterOrders(BUY), filterOrders(SELL));
    }

    private List<SummaryItem> filterOrders(OrderType orderType) {
        return orders.stream()
                .filter(o -> o.getType() == orderType)
                .map(o -> new SummaryItem(o.getQuantity(), o.getPrice()))
                .collect(Collectors.groupingBy(si -> si.getPrice(),
                        Collectors.reducing((a, b) -> new SummaryItem(a.getQuantity().plus(b.getQuantity()), a.getPrice()))))
                .values().stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }

}
