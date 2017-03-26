package service;

import domain.Order;
import domain.OrderType;
import domain.Summary;
import domain.SummaryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    List<Order> buyOrders = new ArrayList<>();
    List<Order> sellOrders = new ArrayList<>();

    public void register(Order order) {
        if (order.getType() == OrderType.BUY) {
            buyOrders.add(order);
        } else {
            sellOrders.add(order);
        }
    }

    public Summary summary() {
        return new Summary(
           buyOrders.stream().map(o -> new SummaryItem(o.getQuantity(), o.getPrice())).collect(Collectors.toList()),
           sellOrders.stream().map(o -> new SummaryItem(o.getQuantity(), o.getPrice())).collect(Collectors.toList()));
    }
}
