package domain;

import org.junit.Test;

import java.math.BigDecimal;

import static domain.OrderType.BUY;
import static domain.OrderType.SELL;
import static domain.SummaryItem.DEFAULT_SUMMARY_ITEM;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SummaryItemTest {

    @Test(expected = AssertionError.class)
    public void failIfPricesDoNotMatchAndOtherIsNotDefault() throws Exception {
        int quantity1 = 100;
        int quantity2 = 200;
        BigDecimal price1 = BigDecimal.TEN;
        BigDecimal price2 = BigDecimal.ONE;
        SummaryItem summaryItem1 = new BuySummaryItem(quantity1, price1);
        SummaryItem summaryItem2 = new BuySummaryItem(quantity2, price2);
        summaryItem1.combineQuantities(summaryItem2);
    }

    public void shouldCombineDifferentPricesIfOtherIsDefault() throws Exception {
        int quantity1 = 100;
        BigDecimal price1 = BigDecimal.TEN;
        SummaryItem summaryItem1 = new BuySummaryItem(quantity1, price1);
        summaryItem1.combineQuantities(DEFAULT_SUMMARY_ITEM);
    }

    @Test
    public void shouldCombineBuyQuantities() throws Exception {
        int quantity1 = 100;
        int quantity2 = 200;
        BigDecimal price = BigDecimal.TEN;
        SummaryItem summaryItem1 = new BuySummaryItem(quantity1, price);
        SummaryItem summaryItem2 = new BuySummaryItem(quantity2, price);
        assertThat(summaryItem1.combineQuantities(summaryItem2), is(new BuySummaryItem(300, price)));
    }

    @Test
    public void shouldCombineSellQuantities() throws Exception {
        int quantity1 = 100;
        int quantity2 = 200;
        BigDecimal price = BigDecimal.TEN;
        SummaryItem summaryItem1 = new SellSummaryItem(quantity1, price);
        SummaryItem summaryItem2 = new SellSummaryItem(quantity2, price);
        assertThat(summaryItem1.combineQuantities(summaryItem2), is(new SellSummaryItem(300, price)));
    }

    @Test
    public void shouldCombineBuyAndSellQuantities() throws Exception {
        int buyQuantity =  100;
        int sellQuantity = 400;
        BigDecimal price = BigDecimal.TEN;
        SummaryItem summaryItem1 = new BuySummaryItem(buyQuantity, price);
        SummaryItem summaryItem2 = new SellSummaryItem(sellQuantity, price);
        assertThat(summaryItem1.combineQuantities(summaryItem2), is(new SellSummaryItem(300, price)));
    }

    @Test
    public void shouldReportToStringCorrectly() throws Exception {
        assertThat(new BuySummaryItem(1244, new BigDecimal(234)).toString(), is("12.44 kg for £234"));
    }

}