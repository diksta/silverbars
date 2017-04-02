package service;

import domain.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderService {

    private List<Order> orders = new ArrayList<>();

    public void register(Order... newOrders) {
        Collections.addAll(orders, newOrders);
    }

    public Summary summary() {
        List<SummaryItem> summaryItems = createSummaryItems();
        return new Summary(filterOrders(summaryItems, BuySummaryItem.class), filterOrders(summaryItems, SellSummaryItem.class));
    }

    private List<SummaryItem> filterOrders(List<SummaryItem> summaryItems, Class<? extends SummaryItem> summaryType) {
        return summaryItems.stream().filter(o -> o.getClass() == summaryType).sorted().collect(Collectors.toList());
    }

    private List<SummaryItem> createSummaryItems() {
        return orders.stream()
                .map(convertToSummaryItem())
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(si -> si.getPrice(), Collectors.reducing((a, b) -> a.combineQuantities(b))),
                map -> removeUnnecessaryOptionalTyping(map)));
    }

    private List<SummaryItem> removeUnnecessaryOptionalTyping(Map<BigDecimal, Optional<SummaryItem>> map) {
        return map.values().stream().map(Optional::get).collect(Collectors.toList());
    }

    private Function<Order, SummaryItem> convertToSummaryItem() {
        return o -> SummaryItem.from(o.getQuantity(), o.getPrice(), o.getType());
    }

}
