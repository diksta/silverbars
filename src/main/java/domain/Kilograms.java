package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Kilograms {
    double amount;

    public Kilograms(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return amount + " kg";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Kilograms kilograms = (Kilograms) o;

        return new EqualsBuilder()
                .append(amount, kilograms.amount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(amount)
                .toHashCode();
    }
}
