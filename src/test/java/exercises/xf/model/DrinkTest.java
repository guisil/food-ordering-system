package exercises.xf.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

/**
 * Test class for Drink.
 *
 * Created by guisil on 31/01/2017.
 */
public class DrinkTest {

    private static final Drink drink1 = new Drink("Beer", new BigDecimal("5.0"), new Cuisine("Polish"));
    private static final Drink drink2 = new Drink("Beer", new BigDecimal("5.0"), new Cuisine("Polish"));
    private static final Drink drink3 = new Drink("Beer", new BigDecimal("5.0"), new Cuisine("Polish"));
    private static final Drink drink4 = new Drink("Wine", new BigDecimal("20.0"), new Cuisine("Portuguese"));

    @Test
    public void testEquals() throws Exception {
        assertThat(drink1).as("Checking if equals is reflexive").isEqualTo(drink1);
        assertThat(drink1.equals(drink2) && drink2.equals(drink1)).as("Checking if equals is symmetric").isTrue();
        assertThat(drink1.equals(drink2) && drink2.equals(drink3) && drink1.equals(drink3)).as("Checking if equals is transitive").isTrue();
        assertThat(drink1.equals(drink2) && drink1.equals(drink2) && drink1.equals(drink2)).as("Checking if equals is consistent").isTrue();
        assertThat(drink1).as("Checking equals with null parameter").isNotEqualTo(null);
        assertThat(drink1).as("Checking equals for different objects").isNotEqualTo(drink4);
    }

    @Test
    public void testHashCode() throws Exception {
        assertThat(drink1.hashCode()).as("Checking hashCode for the same object").isEqualTo(drink1.hashCode());
        assertThat(drink1.hashCode()).as("Checking hashCode for equal objects").isEqualTo(drink2.hashCode());
    }
}