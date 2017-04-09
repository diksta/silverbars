package domain;

import java.util.Comparator;

public class BuyComparator implements Comparator<SummaryItem> {

    @Override
    public int compare(SummaryItem first, SummaryItem second) {
        return second.getPrice().subtract(first.getPrice()).intValue();
    }
}
