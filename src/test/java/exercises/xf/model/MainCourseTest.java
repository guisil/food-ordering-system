package exercises.xf.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

/**
 * Test class for MainCourse.
 *
 * Created by guisil on 31/01/2017.
 */
public class MainCourseTest {

    private static final Cuisine cuisine1 = new Cuisine("Polish");
    private static final Cuisine cuisine2 = new Cuisine("Portuguese");
    private static final MainCourse mainCourse1 = new MainCourse("Bigos", new BigDecimal("30.0"), cuisine1);
    private static final MainCourse mainCourse2 = new MainCourse("Bigos", new BigDecimal("30.0"), cuisine1);
    private static final MainCourse mainCourse3 = new MainCourse("Bigos", new BigDecimal("30.0"), cuisine1);
    private static final MainCourse mainCourse4 = new MainCourse("Pierogi", new BigDecimal("15.0"), cuisine1);
    private static final MainCourse mainCourse5 = new MainCourse("Bacalhau", new BigDecimal("25.0"), cuisine2);

    @Test
    public void testEquals() throws Exception {
        assertThat(mainCourse1).as("Checking if equals is reflexive").isEqualTo(mainCourse1);
        assertThat(mainCourse1.equals(mainCourse2) && mainCourse2.equals(mainCourse1)).as("Checking if equals is symmetric").isTrue();
        assertThat(mainCourse1.equals(mainCourse2) && mainCourse2.equals(mainCourse3) && mainCourse1.equals(mainCourse3)).as("Checking if equals is transitive").isTrue();
        assertThat(mainCourse1.equals(mainCourse2) && mainCourse1.equals(mainCourse2) && mainCourse1.equals(mainCourse2)).as("Checking if equals is consistent").isTrue();
        assertThat(mainCourse1).as("Checking equals with null parameter").isNotEqualTo(null);
        assertThat(mainCourse1).as("Checking equals for different objects").isNotEqualTo(mainCourse4);
        assertThat(mainCourse4).as("Checking equals for different objects").isNotEqualTo(mainCourse5);
    }

    @Test
    public void testHashCode() throws Exception {
        assertThat(mainCourse1.hashCode()).as("Checking hashCode for the same object").isEqualTo(mainCourse1.hashCode());
        assertThat(mainCourse1.hashCode()).as("Checking hashCode for equal objects").isEqualTo(mainCourse2.hashCode());
    }

    @Test
    public void shouldFailWhenCuisineIsMissing() throws Exception {
        Throwable thrown = catchThrowable(() -> new MainCourse("Bigos", new BigDecimal("29.99"), null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid cuisine.");
    }
}