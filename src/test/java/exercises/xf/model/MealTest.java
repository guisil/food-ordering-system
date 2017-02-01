package exercises.xf.model;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by guisil on 31/01/2017.
 */
public class MealTest {

    private static final Cuisine cuisine1 = new Cuisine("Polish");
    private static final Cuisine cuisine2 = new Cuisine("Portuguese");

    private static final Supply mainCourse1 = new MainCourse("Bigos", 30.0, cuisine1);
    private static final Supply mainCourse2 = new MainCourse("Bacalhau", 25.0, cuisine2);

    private static final Supply dessert1 = new Dessert("Sernik", 10.0, cuisine1);
    private static final Supply dessert2 = new Dessert("Pastel de Nata", 11.5, cuisine2);

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

    @Test
    public void shouldGetNewMeal() throws Exception {
        Supply expectedMainCourse =
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", 29.99, new Cuisine("Polish"));
        Supply expectedDessert =
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Tiramisù", 12.5, new Cuisine("Italian"));
        Meal created = new Meal(
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", 29.99, new Cuisine("Polish")),
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Tiramisù", 12.5, new Cuisine("Italian")));
        assertThat(created.getMainCourse().get()).as("Checking factory creation of a meal - main course").isEqualTo(expectedMainCourse);
        assertThat(created.getDessert().get()).as("Checking factory creation of a meal - dessert").isEqualTo(expectedDessert);
    }

    @Test
    public void shouldGetNewMealWithOnlyMainCourse() throws Exception {
        Supply expectedMainCourse =
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", 29.99, new Cuisine("Polish"));
        Meal created = new Meal(
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", 29.99, new Cuisine("Polish")),
                null);
        assertThat(created.getMainCourse().get()).as("Checking factory creation of a meal - main course").isEqualTo(expectedMainCourse);
        assertThat(created.getDessert().isPresent()).as("Checking factory creation of a meal - dessert").isFalse();
    }

    @Test
    public void shouldGetNewMealWithOnlyDessert() throws Exception {
        Supply expectedDessert =
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Tiramisù", 12.5, new Cuisine("Italian"));
        Meal created = new Meal(
                null,
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Tiramisù", 12.5, new Cuisine("Italian")));
        assertThat(created.getMainCourse().isPresent()).as("Checking factory creation of a meal - main course").isFalse();
        assertThat(created.getDessert().get()).as("Checking factory creation of a meal - dessert").isEqualTo(expectedDessert);
    }

    @Test
    public void shouldFailWhenBothMainCourseAndDessertAreMissing() throws Exception {
        Throwable thrown = catchThrowable(() -> new Meal(null, null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Empty meal.");
    }
}