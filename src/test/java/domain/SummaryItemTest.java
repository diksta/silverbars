package domain;

import org.junit.Test;

import java.math.BigDecimal;

import static domain.OrderType.BUY;
import static domain.OrderType.SELL;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SummaryItemTest {

    @Test(expected = AssertionError.class)
    public void failIfPricesDoNotMatch() throws Exception {
        int quantity1 = 100;
        int quantity2 = 200;
        BigDecimal price1 = BigDecimal.TEN;
        BigDecimal price2 = BigDecimal.ONE;
        SummaryItem summaryItem1 = new SummaryItem(quantity1, price1, BUY);
        SummaryItem summaryItem2 = new SummaryItem(quantity2, price2, BUY);
        summaryItem1.combineQuantities(summaryItem2);
    }

    @Test
    public void shouldCombineBuyQuantities() throws Exception {
        int quantity1 = 100;
        int quantity2 = 200;
        BigDecimal price = BigDecimal.TEN;
        SummaryItem summaryItem1 = new SummaryItem(quantity1, price, BUY);
        SummaryItem summaryItem2 = new SummaryItem(quantity2, price, BUY);
        assertThat(summaryItem1.combineQuantities(summaryItem2), is(new SummaryItem(300, price, BUY)));
    }

    @Test
    public void shouldCombineSellQuantities() throws Exception {
        int quantity1 = 100;
        int quantity2 = 200;
        BigDecimal price = BigDecimal.TEN;
        SummaryItem summaryItem1 = new SummaryItem(quantity1, price, SELL);
        SummaryItem summaryItem2 = new SummaryItem(quantity2, price, SELL);
        assertThat(summaryItem1.combineQuantities(summaryItem2), is(new SummaryItem(300, price, SELL)));
    }

    @Test
    public void shouldCombineBuyAndSellQuantities() throws Exception {
        int buyQuantity =  100;
        int sellQuantity = 400;
        BigDecimal price = BigDecimal.TEN;
        SummaryItem summaryItem1 = new SummaryItem(buyQuantity, price, BUY);
        SummaryItem summaryItem2 = new SummaryItem(sellQuantity, price, SELL);
        assertThat(summaryItem1.combineQuantities(summaryItem2), is(new SummaryItem(300, price, SELL)));
    }

    @Test
    public void shouldReportToStringCorrectly() throws Exception {
        assertThat(new SummaryItem(1244, new BigDecimal(234), BUY).toString(), is("12.44 kg for £234"));
    }


}