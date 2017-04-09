package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static domain.OrderType.*;

public class Summary {

    private List<SummaryItem> sellOrders = new ArrayList<>();
    private List<SummaryItem> buyOrders = new ArrayList<>();

    public Summary(Collection<SummaryItem> summaryItems) {
        this.buyOrders = filterAndSortOrders(summaryItems, BUY);
        this.sellOrders = filterAndSortOrders(summaryItems, SELL);
    }

    public List<SummaryItem> buyOrders() {
       return Collections.unmodifiableList(buyOrders);
    }

    public List<SummaryItem> sellOrders() {
        return Collections.unmodifiableList(sellOrders);
    }

    private List<SummaryItem> filterAndSortOrders(Collection<SummaryItem> summaryItems, OrderType orderType) {
        return summaryItems.stream().filter(o -> o.getOrderType() == orderType).sorted().collect(Collectors.toList());
    }
}
