package domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BuyCombinerTest {

    @Test(expected = AssertionError.class)
    public void failIfFirstArgumentIsNotSellSummaryItem() throws Exception {
        SummaryItem summaryItem1 = SummaryItem.sell(100, BigDecimal.TEN);
        SummaryItem summaryItem2 = SummaryItem.sell(200, BigDecimal.TEN);

        new SellCombiner().combine(summaryItem1, summaryItem2);
    }

    @Test
    public void shouldCombineBuyQuantities() throws Exception {
        SummaryItem summaryItem1 = SummaryItem.buy(100, BigDecimal.TEN);
        SummaryItem summaryItem2 = SummaryItem.buy(200, BigDecimal.TEN);
        assertThat(new BuyCombiner().combine(summaryItem1, summaryItem2), is(SummaryItem.buy(300, BigDecimal.TEN)));
    }

    @Test
    public void shouldCombineBuyAndSellQuantities() throws Exception {
        SummaryItem summaryItem1 = SummaryItem.buy(100, BigDecimal.TEN);
        SummaryItem summaryItem2 = SummaryItem.sell(400, BigDecimal.TEN);
        assertThat(new BuyComparator().compare(summaryItem1, summaryItem2), is(SummaryItem.sell(300, BigDecimal.TEN)));
    }
}