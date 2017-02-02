package exercises.xf.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by guisil on 31/01/2017.
 */
public class DessertTest {

    private static final Cuisine cuisine1 = new Cuisine("Polish");
    private static final Cuisine cuisine2 = new Cuisine("Portuguese");
    private static final Dessert dessert1 = new Dessert("Sernik", new BigDecimal("10.0"), cuisine1);
    private static final Dessert dessert2 = new Dessert("Sernik", new BigDecimal("10.0"), cuisine1);
    private static final Dessert dessert3 = new Dessert("Sernik", new BigDecimal("10.0"), cuisine1);
    private static final Dessert dessert4 = new Dessert("Sernik", new BigDecimal("11.5"), cuisine1);
    private static final Dessert dessert5 = new Dessert("Pastel de Nata", new BigDecimal("11.5"), cuisine2);

    @Test
    public void testEquals() throws Exception {
        assertThat(dessert1).as("Checking if equals is reflexive").isEqualTo(dessert1);
        assertThat(dessert1.equals(dessert2) && dessert2.equals(dessert1)).as("Checking if equals is symmetric").isTrue();
        assertThat(dessert1.equals(dessert2) && dessert2.equals(dessert3) && dessert1.equals(dessert3)).as("Checking if equals is transitive").isTrue();
        assertThat(dessert1.equals(dessert2) && dessert1.equals(dessert2) && dessert1.equals(dessert2)).as("Checking if equals is consistent").isTrue();
        assertThat(dessert1).as("Checking equals with null parameter").isNotEqualTo(null);
        assertThat(dessert1).as("Checking equals for different objects").isNotEqualTo(dessert4);
        assertThat(dessert4).as("Checking equals for different objects").isNotEqualTo(dessert5);
    }

    @Test
    public void testHashCode() throws Exception {
        assertThat(dessert1.hashCode()).as("Checking hashCode for the same object").isEqualTo(dessert1.hashCode());
        assertThat(dessert1.hashCode()).as("Checking hashCode for equal objects").isEqualTo(dessert2.hashCode());
    }

    @Test
    public void shouldFailWhenCuisineIsMissing() throws Exception {
        Throwable thrown = catchThrowable(() -> new MainCourse("Tiramisù", new BigDecimal("12.5"), null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid cuisine.");
    }
}