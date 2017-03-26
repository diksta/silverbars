package service;

import domain.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class OrderServiceTest {

    OrderService orderService;

    @Before
    public void setUp() throws Exception {
        orderService = new OrderService();
    }

    @Test
    public void shouldAddRegisteredBuyOrderToSummary() throws Exception {
        //Given
        Kilograms quantity = new Kilograms(1.2);
        BigDecimal price = BigDecimal.valueOf(310);
        Order order = new Order(new UserId("user1"), quantity, price, OrderType.BUY);

        //When
        orderService.register(order);

        //Then
        assertThat(orderService.summary().buyOrders(), hasItems(new SummaryItem(quantity, price)));
    }

    @Test
    public void shouldAddRegisteredSellOrderToSummary() throws Exception {
        //Given
        OrderService orderService = new OrderService();

        Kilograms quantity = new Kilograms(3.5);
        BigDecimal price = BigDecimal.valueOf(250);
        Order order = new Order(new UserId("user1"), quantity, price, OrderType.SELL);

        //When
        orderService.register(order);

        //Then
        assertThat(orderService.summary().sellOrders(), hasItems(new SummaryItem(quantity, price)));
    }

}