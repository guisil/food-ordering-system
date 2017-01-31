package exercises.xf.model;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by guisil on 31/01/2017.
 */
public class MealTest {

    private static final Cuisine cuisine1 = new Cuisine("Polish");
    private static final Cuisine cuisine2 = new Cuisine("Portuguese");

    private static final MainCourse mainCourse1 = new MainCourse("Bigos", 30.0, cuisine1);
    private static final MainCourse mainCourse2 = new MainCourse("Bacalhau", 25.0, cuisine2);

    private static final Dessert dessert1 = new Dessert("Sernik", 10.0, cuisine1);
    private static final Dessert dessert2 = new Dessert("Pastel de Nata", 11.5, cuisine2);

    private static final Meal meal1 = new Meal(mainCourse1, dessert1);
    private static final Meal meal2 = new Meal(mainCourse1, dessert1);
    private static final Meal meal3 = new Meal(mainCourse1, dessert1);
    private static final Meal meal4 = new Meal(mainCourse1, dessert2);
    private static final Meal meal5 = new Meal(mainCourse2, dessert1);

    @Test
    public void testEquals() throws Exception {
        assertThat(meal1).as("Checking if equals is reflexive").isEqualTo(meal1);
        assertThat(meal1.equals(meal2) && meal2.equals(meal1)).as("Checking if equals is symmetric").isTrue();
        assertThat(meal1.equals(meal2) && meal2.equals(meal3) && meal1.equals(meal3)).as("Checking if equals is transitive").isTrue();
        assertThat(meal1.equals(meal2) && meal1.equals(meal2) && meal1.equals(meal2)).as("Checking if equals is consistent").isTrue();
        assertThat(meal1).as("Checking equals with null parameter").isNotEqualTo(null);
        assertThat(meal1).as("Checking equals for different objects").isNotEqualTo(meal4);
        assertThat(meal4).as("Checking equals for different objects").isNotEqualTo(meal5);
    }

    @Test
    public void testHashCode() throws Exception {
        assertThat(meal1.hashCode()).as("Checking hashCode for the same object").isEqualTo(meal1.hashCode());
        assertThat(meal1.hashCode()).as("Checking hashCode for equal objects").isEqualTo(meal2.hashCode());
    }
}