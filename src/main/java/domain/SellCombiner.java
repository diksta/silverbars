package domain;

import static domain.SummaryItem.buy;
import static domain.SummaryItem.sell;

public class SellCombiner implements Combiner {

    @Override
    public SummaryItem combine(SummaryItem first, SummaryItem second) {
        assert first.isSell() == true;
        if (second.isSell()) {
            return sell(second.getQuantity() + first.getQuantity(), first.getPrice());
        } else {
            int total = second.getQuantity() - first.getQuantity();
            return total < 0 ? sell(Math.abs(total), first.getPrice()) : buy(total, first.getPrice());
        }
    }

}
