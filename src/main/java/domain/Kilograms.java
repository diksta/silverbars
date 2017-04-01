package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Kilograms {

    private static final double GRAMS_IN_KG = 100d;
    private int grams;

    public Kilograms(int grams) {
        this.grams = grams;
    }

    @Override
    public String toString() {
        return (grams / GRAMS_IN_KG) + " kg";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Kilograms kilograms = (Kilograms) o;

        return new EqualsBuilder()
                .append(grams, kilograms.grams)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(grams)
                .toHashCode();
    }

    public Kilograms plus(Kilograms other) {
        return new Kilograms(other.grams + grams);
    }

    public int getGrams() { return grams; }
}
