package service;

import domain.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
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
        Kilograms quantity = new Kilograms(120);
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
        Kilograms quantity = new Kilograms(350);
        BigDecimal price = BigDecimal.valueOf(250);
        Order order = new Order(new UserId("user1"), quantity, price, OrderType.SELL);

        //When
        orderService.register(order);

        //Then
        assertThat(orderService.summary().sellOrders(), hasItems(new SummaryItem(quantity, price)));
    }

    @Test
    public void shouldAddRegisteredSellAndBuyOrderToSummary() throws Exception {
        //Given
        BigDecimal buyPrice = BigDecimal.valueOf(250);
        Kilograms buyQuantity = new Kilograms(350);
        BigDecimal sellPrice = BigDecimal.valueOf(50);
        Kilograms sellQuantity = new Kilograms(120);
        Order order1 = new Order(new UserId("user1"), buyQuantity, buyPrice, OrderType.BUY);
        Order order2 = new Order(new UserId("user2"), sellQuantity, sellPrice, OrderType.SELL);

        //When
        orderService.register(order1, order2);

        //Then
        assertThat(orderService.summary().buyOrders().size(), is(1));
        assertThat(orderService.summary().buyOrders(), hasItems(new SummaryItem(buyQuantity, buyPrice)));
        assertThat(orderService.summary().sellOrders().size(), is(1));
        assertThat(orderService.summary().sellOrders(), hasItems(new SummaryItem(sellQuantity, sellPrice)));
    }

    @Test
    public void shouldCombineOrdersWhenTwoBuysForTheSamePriceAreRegistered() throws Exception {
        //Given
        BigDecimal price = BigDecimal.valueOf(250);
        Kilograms quantity1 = new Kilograms(350);
        Kilograms quantity2 = new Kilograms(120);
        Order order1 = new Order(new UserId("user1"), quantity1, price, OrderType.BUY);
        Order order2 = new Order(new UserId("user2"), quantity2, price, OrderType.BUY);

        //When
        orderService.register(order1, order2);

        //Then
        Kilograms expectedTotal = quantity1.plus(quantity2);
        assertThat(orderService.summary().buyOrders(), hasItems(new SummaryItem(expectedTotal, price)));
    }

    @Test
    public void shouldCombineOrdersWhenTwoSellsForTheSamePriceAreRegistered() throws Exception {
        //Given
        BigDecimal price = BigDecimal.valueOf(250);
        Kilograms quantity1 = new Kilograms(350);
        Kilograms quantity2 = new Kilograms(120);
        Order order1 = new Order(new UserId("user1"), quantity1, price, OrderType.SELL);
        Order order2 = new Order(new UserId("user2"), quantity2, price, OrderType.SELL);

        //When
        orderService.register(order1, order2);

        //Then
        Kilograms expectedTotal = quantity1.plus(quantity2);
        assertThat(orderService.summary().sellOrders(), hasItems(new SummaryItem(expectedTotal, price)));
    }
    

}