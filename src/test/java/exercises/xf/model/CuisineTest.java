package exercises.xf.model;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by guisil on 31/01/2017.
 */
public class CuisineTest {

    private static final Cuisine cuisine1 = new Cuisine("Polish");
    private static final Cuisine cuisine2 = new Cuisine("Polish");
    private static final Cuisine cuisine3 = new Cuisine("Polish");
    private static final Cuisine cuisine4 = new Cuisine("Portuguese");

    @Test
    public void testEquals() throws Exception {
        assertThat(cuisine1).as("Checking if equals is reflexive").isEqualTo(cuisine1);
        assertThat(cuisine1.equals(cuisine2) && cuisine2.equals(cuisine1)).as("Checking if equals is symmetric").isTrue();
        assertThat(cuisine1.equals(cuisine2) && cuisine2.equals(cuisine3) && cuisine1.equals(cuisine3)).as("Checking if equals is transitive").isTrue();
        assertThat(cuisine1.equals(cuisine2) && cuisine1.equals(cuisine2) && cuisine1.equals(cuisine2)).as("Checking if equals is consistent").isTrue();
        assertThat(cuisine1).as("Checking equals with null parameter").isNotEqualTo(null);
        assertThat(cuisine1).as("Checking equals for different objects").isNotEqualTo(cuisine4);
    }

    @Test
    public void testHashCode() throws Exception {
        assertThat(cuisine1.hashCode()).as("Checking hashCode for the same object").isEqualTo(cuisine1.hashCode());
        assertThat(cuisine1.hashCode()).as("Checking hashCode for equal objects").isEqualTo(cuisine2.hashCode());
    }

    @Test
    public void shouldFailWhenNameIsMissing() throws Exception {
        Throwable thrown = catchThrowable(() -> new Cuisine(null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Empty name.");
    }
}