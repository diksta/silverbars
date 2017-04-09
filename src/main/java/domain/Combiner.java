package domain;

public interface Combiner {
    SummaryItem combine(SummaryItem first, SummaryItem second);
}
