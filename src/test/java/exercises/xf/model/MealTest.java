package exercises.xf.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

/**
 * Test class for Meal.
 *
 * Created by guisil on 31/01/2017.
 */
public class MealTest {

    private static final Cuisine cuisine1 = new Cuisine("Polish");
    private static final Cuisine cuisine2 = new Cuisine("Portuguese");

    private static final Supply mainCourse1 = new MainCourse("Bigos", new BigDecimal("30.0"), cuisine1);
    private static final Supply mainCourse2 = new MainCourse("Bacalhau", new BigDecimal("25.0"), cuisine2);

    private static final Supply dessert1 = new Dessert("Sernik", new BigDecimal("10.0"), cuisine1);
    private static final Supply dessert2 = new Dessert("Pastel de Nata", new BigDecimal("11.5"), cuisine2);

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
        Cuisine polishCuisine = new Cuisine("Polish");
        Supply expectedMainCourse =
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", new BigDecimal("29.99"), polishCuisine);
        Supply expectedDessert =
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Sernik", new BigDecimal("10.0"), polishCuisine);
        Meal created = new Meal(
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", new BigDecimal("29.99"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Sernik", new BigDecimal("10.0"), polishCuisine));
        assertThat(created.getMainCourse().get()).as("Checking factory creation of a meal - main course").isEqualTo(expectedMainCourse);
        assertThat(created.getDessert().get()).as("Checking factory creation of a meal - dessert").isEqualTo(expectedDessert);
    }

    @Test
    public void shouldGetNewMealWithOnlyMainCourse() throws Exception {
        Cuisine polishCuisine = new Cuisine("Polish");
        Supply expectedMainCourse =
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", new BigDecimal("29.99"), polishCuisine);
        Meal created = new Meal(
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", new BigDecimal("29.99"), polishCuisine),
                null);
        assertThat(created.getMainCourse().get()).as("Checking factory creation of a meal - main course").isEqualTo(expectedMainCourse);
        assertThat(created.getDessert().isPresent()).as("Checking factory creation of a meal - dessert").isFalse();
    }

    @Test
    public void shouldGetNewMealWithOnlyDessert() throws Exception {
        Cuisine italianCuisine = new Cuisine("Italian");
        Supply expectedDessert =
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Tiramisù", new BigDecimal("12.5"), italianCuisine);
        Meal created = new Meal(
                null,
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Tiramisù", new BigDecimal("12.5"), italianCuisine));
        assertThat(created.getMainCourse().isPresent()).as("Checking factory creation of a meal - main course").isFalse();
        assertThat(created.getDessert().get()).as("Checking factory creation of a meal - dessert").isEqualTo(expectedDessert);
    }

    @Test
    public void shouldFailWhenBothMainCourseAndDessertAreMissing() throws Exception {
        Throwable thrown = catchThrowable(() -> new Meal(null, null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Empty meal.");
    }

    @Test
    public void priceShouldBeSumOfBothItems() throws Exception {
        Cuisine polishCuisine = new Cuisine("Polish");
        BigDecimal price1 = new BigDecimal("29.99");
        BigDecimal price2 = new BigDecimal("10.0");
        BigDecimal expectedPrice = new BigDecimal("39.99");
        Meal meal = new Meal(
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", price1, polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Sernik", price2, polishCuisine));
        assertThat(meal.getPrice()).as("Checking price of meal").isEqualTo(expectedPrice);
    }

    @Test
    public void priceShouldBeSumOfBothItems_WithOneOfThemEmpty() throws Exception {
        BigDecimal expectedPrice = new BigDecimal("29.99");
        Meal meal = new Meal(
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", expectedPrice, new Cuisine("Polish")),
                null);
        assertThat(meal.getPrice()).as("Checking price of meal").isEqualTo(expectedPrice);
    }
}