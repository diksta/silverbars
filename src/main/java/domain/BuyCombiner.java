package domain;

import static domain.SummaryItem.*;

public class BuyCombiner implements Combiner {
    public SummaryItem combine(SummaryItem first, SummaryItem second) {
        assert first.isSell() == false;
        if (second.isSell()) {
            int total = first.getQuantity() - second.getQuantity();
            return total < 0 ? sell(Math.abs(total), first.getPrice()) : buy(total, first.getPrice());
        } else {
            return buy(second.getQuantity() + first.getQuantity(), first.getPrice());
        }
    }
}
