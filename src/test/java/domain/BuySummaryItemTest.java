package domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BuySummaryItemTest {

    @Test
    public void shouldCompareBuysAsHighestPriceFirst() throws Exception {
        assertThat(new BuySummaryItem(1244, new BigDecimal(100)).compareTo(new BuySummaryItem(1244, new BigDecimal(100))), is(0));
        assertThat(new BuySummaryItem(1244, new BigDecimal(300)).compareTo(new BuySummaryItem(1244, new BigDecimal(200))), is(-100));
        assertThat(new BuySummaryItem(1244, new BigDecimal(200)).compareTo(new BuySummaryItem(1244, new BigDecimal(300))), is(100));
    }
}