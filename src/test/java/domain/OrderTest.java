package domain;

import org.junit.Test;

import java.math.BigDecimal;

import static domain.OrderType.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void shouldBeEqualIfIdsMatch() throws Exception {
        assertThat(Order.forId("testId"), is(Order.forId("testId")));

        Order testOrder1 = new Order("userId", 23, BigDecimal.valueOf(5), BUY);
        assertThat(testOrder1, is(testOrder1));

        Order testOrder2 = new Order("userId", 23, BigDecimal.valueOf(5), BUY);
        assertThat(testOrder2, not(is(testOrder1)));
    }

    @Test
    public void shouldHaveSameHashIdIdsAreEqual() throws Exception {
        assertThat(Order.forId("testId").hashCode(), is(Order.forId("testId").hashCode()));

        Order testOrder1 = new Order("userId", 23, BigDecimal.valueOf(5), BUY);
        assertThat(testOrder1.hashCode(), is(testOrder1.hashCode()));

        Order testOrder2 = new Order("userId", 23, BigDecimal.valueOf(5), BUY);
        assertThat(testOrder2.hashCode(), not(is(testOrder1.hashCode())));
    }
}