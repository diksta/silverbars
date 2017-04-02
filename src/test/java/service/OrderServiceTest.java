package service;

import domain.Order;
import domain.SummaryItem;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static domain.OrderType.BUY;
import static domain.OrderType.SELL;
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
        int quantity = 120;
        BigDecimal price = BigDecimal.valueOf(310);
        Order order = new Order("user1", quantity, price, BUY);

        //When
        orderService.register(order);

        //Then
        assertThat(orderService.summary().buyOrders(), hasItems(SummaryItem.from(quantity, price, BUY)));
    }

    @Test
    public void shouldAddRegisteredSellOrderToSummary() throws Exception {
        //Given
        int quantity = 350;
        BigDecimal price = BigDecimal.valueOf(250);
        Order order = new Order("user1", quantity, price, SELL);

        //When
        orderService.register(order);

        //Then
        assertThat(orderService.summary().sellOrders(), hasItems(SummaryItem.from(quantity, price, SELL)));
    }

    @Test
    public void shouldAddRegisteredSellAndBuyOrderToSummary() throws Exception {
        //Given
        BigDecimal buyPrice = BigDecimal.valueOf(250);
        int buyQuantity = 350;
        BigDecimal sellPrice = BigDecimal.valueOf(50);
        int sellQuantity = 120;
        Order order1 = new Order("user1", buyQuantity, buyPrice, BUY);
        Order order2 = new Order("user2", sellQuantity, sellPrice, SELL);

        //When
        orderService.register(order1, order2);

        //Then
        assertThat(orderService.summary().buyOrders().size(), is(1));
        assertThat(orderService.summary().buyOrders(), hasItems(SummaryItem.from(buyQuantity, buyPrice, BUY)));
        assertThat(orderService.summary().sellOrders().size(), is(1));
        assertThat(orderService.summary().sellOrders(), hasItems(SummaryItem.from(sellQuantity, sellPrice, SELL)));
    }

    @Test
    public void shouldCombineOrders() throws Exception {
        //Given
        BigDecimal price = BigDecimal.valueOf(250);
        int quantity1 = 350;
        int quantity2 = 120;
        Order order1 = new Order("user1", quantity1, price, BUY);
        Order order2 = new Order("user2", quantity2, price, BUY);

        //When
        orderService.register(order1, order2);

        //Then
        int expectedTotal = quantity1 + quantity2;
        assertThat(orderService.summary().buyOrders(), hasItems(SummaryItem.from(expectedTotal, price, BUY)));
    }

    @Test
    public void shouldCombineOrdersWhenTwoSellsForTheSamePriceAreRegistered() throws Exception {
        //Given
        BigDecimal price = BigDecimal.valueOf(250);
        int quantity1 = 350;
        int quantity2 = 120;
        Order order1 = new Order("user1", quantity1, price, SELL);
        Order order2 = new Order("user2", quantity2, price, SELL);

        //When
        orderService.register(order1, order2);

        //Then
        int expectedTotal = quantity1 + quantity2;
        assertThat(orderService.summary().sellOrders(), hasItems(SummaryItem.from(expectedTotal, price, SELL)));
    }

    @Test
    public void shouldCombineMoreThanTwoOrdersWithTheSameAndyType() throws Exception {
        //Given
        BigDecimal price = BigDecimal.valueOf(250);
        int quantity1 = 350;
        int quantity2 = 120;
        int quantity3 = 100;
        Order order1 = new Order("user1", quantity1, price, SELL);
        Order order2 = new Order("user2", quantity2, price, SELL);
        Order order3 = new Order("user2", quantity3, price, SELL);

        //When
        orderService.register(order1, order2, order3);

        //Then
        int expectedTotal = quantity1 + quantity2 + quantity3;
        assertThat(orderService.summary().sellOrders(), hasItems(SummaryItem.from(expectedTotal, price, SELL)));
    }

    @Test
    public void shouldCombineABuyAndSellAtSamePrice() throws Exception {
        //Given
        BigDecimal price = BigDecimal.valueOf(250);
        int sellQuantity = 350;
        int buyQuantity = 120;
        Order order1 = new Order("user1", sellQuantity, price, SELL);
        Order order2 = new Order("user2", buyQuantity, price, BUY);

        //When
        orderService.register(order1, order2);

        //Then
        int expectedTotal = 230;
        assertThat(orderService.summary().sellOrders(), hasItems(SummaryItem.from(expectedTotal, price, SELL)));
        assertThat(orderService.summary().buyOrders().size(), is(0));
    }

    @Test
    public void shouldCombineMultipleBuyAndSellsAtSamePrice() throws Exception {
        //Given
        BigDecimal price250 = BigDecimal.valueOf(250);
        BigDecimal price100 = BigDecimal.valueOf(100);

        Order sellAt250 = new Order("user1", 350, price250, SELL);
        Order buyAt250 = new Order("user1", 210, price250, BUY);
        Order buyAnotherAt250 = new Order("user2", 120, price250, BUY);
        Order sellAt100 = new Order("user2", 120, price100, SELL);
        Order buyAt100 = new Order("user2", 150, price100, BUY);

        //When
        orderService.register(sellAt250, sellAt100, buyAnotherAt250, buyAt100, buyAt250);

        //Then
        int expectedTotal = 230;
        assertThat(orderService.summary().sellOrders(), hasItems(SummaryItem.from(20, price250, SELL)));
        assertThat(orderService.summary().buyOrders(), hasItems(SummaryItem.from(30, price100, BUY)));
    }

    @Test
    public void shouldSortBuysWithHighestPriceFirst() throws Exception {
        //Given
        Order order1 = new Order("user2", 140, BigDecimal.valueOf(100), BUY);
        Order order2 = new Order("user1", 350, BigDecimal.valueOf(250), BUY);
        Order order3 = new Order("user2", 120, BigDecimal.valueOf(120), BUY);
        SummaryItem summaryForOrder1 = SummaryItem.from(140, BigDecimal.valueOf(100), BUY);
        SummaryItem summaryForOrder2 = SummaryItem.from(350, BigDecimal.valueOf(250), BUY);
        SummaryItem summaryForOrder3 = SummaryItem.from(120, BigDecimal.valueOf(120), BUY);

        //When
        orderService.register(order1, order2, order3);

        //Then
        List<SummaryItem> expectedOrders = Arrays.asList(summaryForOrder2, summaryForOrder3, summaryForOrder1);
        assertThat(orderService.summary().buyOrders(), is(expectedOrders));
    }

    @Test
    public void shouldSortSellsWithLowestPriceFirst() throws Exception {
        //Given
        Order order1 = new Order("user2", 140, BigDecimal.valueOf(100), SELL);
        Order order2 = new Order("user1", 350, BigDecimal.valueOf(250), SELL);
        Order order3 = new Order("user2", 120, BigDecimal.valueOf(120), SELL);
        SummaryItem summaryForOrder1 = SummaryItem.from(140, BigDecimal.valueOf(100), SELL);
        SummaryItem summaryForOrder2 = SummaryItem.from(350, BigDecimal.valueOf(250), SELL);
        SummaryItem summaryForOrder3 = SummaryItem.from(120, BigDecimal.valueOf(120), SELL);

        //When
        orderService.register(order1, order2, order3);

        //Then
        List<SummaryItem> expectedOrders = Arrays.asList(summaryForOrder1, summaryForOrder3, summaryForOrder2);
        assertThat(orderService.summary().sellOrders(), is(expectedOrders));
    }
}