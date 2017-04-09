package domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BuyComparatorTest {

    @Test
    public void shouldCompareBuysAsHighestPriceFirst() throws Exception {
        assertThat(new BuyComparator().compare(SummaryItem.buy(1244, new BigDecimal(100)), SummaryItem.buy(1244, new BigDecimal(100))), is(0));
        assertThat(new BuyComparator().compare(SummaryItem.buy(1244, new BigDecimal(300)), SummaryItem.buy(1244, new BigDecimal(200))), is(-100));
        assertThat(new BuyComparator().compare(SummaryItem.buy(1244, new BigDecimal(200)), SummaryItem.buy(1244, new BigDecimal(300))), is(100));
    }

}