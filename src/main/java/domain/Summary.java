package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Summary {

    private List<SummaryItem> sellOrders = new ArrayList<>();
    private List<SummaryItem> buyOrders = new ArrayList<>();

    public Summary(Collection<SummaryItem> summaryItems) {
        this.buyOrders = filterOrders(summaryItems, BuySummaryItem.class);
        this.sellOrders = filterOrders(summaryItems, SellSummaryItem.class);
    }

    public List<SummaryItem> buyOrders() {
       return Collections.unmodifiableList(buyOrders);
    }

    public List<SummaryItem> sellOrders() {
        return Collections.unmodifiableList(sellOrders);
    }

    private List<SummaryItem> filterOrders(Collection<SummaryItem> summaryItems, Class<? extends SummaryItem> summaryType) {
        return summaryItems.stream().filter(o -> o.getClass() == summaryType).sorted().collect(Collectors.toList());
    }
}
