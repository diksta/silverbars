package domain;

import org.junit.Test;

import java.math.BigDecimal;

import static domain.OrderType.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void shouldBeEqualIdIdsMatch() throws Exception {
        assertThat(Order.forId("testId"), is(Order.forId("testId")));

        Order testOrder1 = new Order("userId", 23, BigDecimal.valueOf(5), BUY);
        assertThat(testOrder1, is(testOrder1));

        Order testOrder2 = new Order("userId", 23, BigDecimal.valueOf(5), BUY);
        assertThat(testOrder2, not(is(testOrder1)));
    }
}