package domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Comparator;

import static domain.OrderType.BUY;
import static domain.OrderType.SELL;
import static domain.SummaryItem.DEFAULT_SUMMARY_ITEM;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class SummaryItemTest {

    @Mock
    private Comparator mockComparator;
    @Mock
    private Combiner mockCombiner;

    @Before
    public void init() {
        initMocks(this);
    }

    @Test(expected = AssertionError.class)
    public void failIfPricesDoNotMatchAndOtherIsNotDefault() throws Exception {

        SummaryItem summaryItem1 = new SummaryItem(100, BigDecimal.TEN, mockComparator, mockCombiner, BUY);
        SummaryItem summaryItem2 = new SummaryItem(200, BigDecimal.ONE, mockComparator, mockCombiner, SELL);

        summaryItem1.combineQuantities(summaryItem2);
    }

    @Test
    public void shouldCombineDifferentPricesIfOtherIsDefault() throws Exception {
        SummaryItem summaryItem = new SummaryItem(100, BigDecimal.TEN, mockComparator, mockCombiner, BUY);
        summaryItem.combineQuantities(DEFAULT_SUMMARY_ITEM);

        verify(mockCombiner).combine(summaryItem, DEFAULT_SUMMARY_ITEM);
    }

    @Test
    public void shouldCombineQuantities() throws Exception {
        SummaryItem summaryItem1 = new SummaryItem(100, BigDecimal.TEN, mockComparator, mockCombiner, BUY);
        SummaryItem summaryItem2 = new SummaryItem(200, BigDecimal.TEN, mockComparator, mockCombiner, BUY);

        SummaryItem combinedSummaryItem = new SummaryItem(300, BigDecimal.TEN, mockComparator, mockCombiner, BUY);
        when(mockCombiner.combine(summaryItem1, summaryItem2)).thenReturn(combinedSummaryItem);

        assertThat(summaryItem1.combineQuantities(summaryItem2), is(combinedSummaryItem));

        verify(mockCombiner).combine(summaryItem1, summaryItem2);
    }


    @Test
    public void shouldReportToStringCorrectly() throws Exception {
        assertThat(SummaryItem.buy(1244, new BigDecimal(234)).toString(), is("12.44 kg for £234"));
    }

    @Test
    public void equalsShouldMatchOnQuantityPriceAndIsSellFlag() throws Exception {
        SummaryItem summaryItem1 = new SummaryItem(100, BigDecimal.TEN, mockComparator, mockCombiner, BUY);
        SummaryItem summaryItem2 = new SummaryItem(100, BigDecimal.TEN, null, null, BUY);
        SummaryItem summaryItem3 = new SummaryItem(150, BigDecimal.TEN, null, null, BUY);
        SummaryItem summaryItem4 = new SummaryItem(100, BigDecimal.ONE, null, null, BUY);
        SummaryItem summaryItem5 = new SummaryItem(100, BigDecimal.TEN, null, null, SELL);

        assertThat(summaryItem1, is(summaryItem2));

        assertThat(summaryItem1, is(not(summaryItem3)));
        assertThat(summaryItem1, is(not(summaryItem4)));
        assertThat(summaryItem1, is(not(summaryItem5)));
    }
}