package domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class KilogramsTest {

    @Test
    public void shouldReportToStringInKg() throws Exception {
        assertThat(new Kilograms(1244).toString(), is("12.44 kg"));
    }

    @Test
    public void shouldAdd() throws Exception {
        assertThat(new Kilograms(1244).plus(new Kilograms(300)).toString(), is("15.44 kg"));
        assertThat(new Kilograms(0).plus(new Kilograms(1)).toString(), is("0.01 kg"));
    }
}