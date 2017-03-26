package domain;

import java.util.ArrayList;
import java.util.List;

public class Summary {

    private List<SummaryItem> sellOrders = new ArrayList<>();
    private List<SummaryItem> buyOrders = new ArrayList<>();

    public Summary(List<SummaryItem> buyOrders, List<SummaryItem> sellOrders) {
        this.buyOrders = buyOrders;
        this.sellOrders = sellOrders;
    }

    public List<SummaryItem> buyOrders() {
       return buyOrders;
    }

    public List<SummaryItem> sellOrders() {
        return sellOrders;
    }
}
