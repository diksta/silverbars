package domain;

import java.util.Comparator;

public class SellComparator implements Comparator<SummaryItem> {
    @Override
    public int compare(SummaryItem first, SummaryItem second) {
        return first.getPrice().subtract(second.getPrice()).intValue();
    }
}
