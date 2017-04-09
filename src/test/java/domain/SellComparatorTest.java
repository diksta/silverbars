package domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SellComparatorTest {

    @Test
    public void shouldCompareSellsAsLowestPriceFirst() throws Exception {
        assertThat(new SellComparator().compare(SummaryItem.sell(1244, new BigDecimal(100)), SummaryItem.sell(1244, new BigDecimal(100))), is(0));
        assertThat(new SellComparator().compare(SummaryItem.sell(1244, new BigDecimal(300)), SummaryItem.sell(1244, new BigDecimal(200))), is(100));
        assertThat(new SellComparator().compare(SummaryItem.sell(1244, new BigDecimal(200)), SummaryItem.sell(1244, new BigDecimal(300))), is(-100));
    }

}