package service;

import domain.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static domain.SummaryItem.DEFAULT_SUMMARY_ITEM;

public class OrderService {

    private Set<Order> orders = new LinkedHashSet<>();

    /**
    * Get list of current registered orders in registration order 
    */
    public Set<Order> orders() { return Collections.unmodifiableSet(orders); }

    /**
     * Add a new order(s) to current registered orders
     * @param newOrders
     */
    public void register(Order... newOrders) { Collections.addAll(orders, newOrders); }

    /**
     * Cancel an Order
     * @param Unique orderId Id of the order to be cancelled
     * @return Summary of orders
     */
    public void cancel(String orderId) { orders.remove(Order.forId(orderId)); }

    /**
     * Generates a summary of combined Buy and Sell orders
     * @return Summary of orders
     */
    public Summary summary() {
        return new Summary(createSummaryItems());
    }

    private Collection<SummaryItem> createSummaryItems() {
        return orders.stream()
                .map(convertToSummaryItem())
                .collect(Collectors.groupingBy(
                        si -> si.getPrice(),
                        Collectors.reducing(DEFAULT_SUMMARY_ITEM, (a, b) -> b.combineQuantities(a))
                )).values();
    }

    private Function<Order, SummaryItem> convertToSummaryItem() {
        return order -> SummaryItem.from(order);
    }

}
