package exercises.xf.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.util.Arrays.*;

/**
 * Test class for SupplyFactory.
 *
 * Created by guisil on 01/02/2017.
 */
public class SupplyFactoryTest {

    @Test
    public void shouldGetNewMainCourse() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        final MainCourse expectedMainCourse = new MainCourse("Bigos", new BigDecimal("30.0"), cuisine1);
        Supply created = SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", new BigDecimal("30.0"), cuisine1);
        assertThat(created).as("Checking factory creation of a main course").isEqualTo(expectedMainCourse);
    }

    @Test
    public void shouldGetNewDessert() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        final Dessert expectedDessert = new Dessert("Sernik", new BigDecimal("10.0"), cuisine1);
        Supply created = SupplyFactory.getNewSupply(SupplyType.DESSERT, "Sernik", new BigDecimal("10.0"), cuisine1);
        assertThat(created).as("Checking factory creation of a dessert").isEqualTo(expectedDessert);
    }

    @Test
    public void shouldGetNewDrink() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        final Drink expectedDrink = new Drink("Beer", new BigDecimal("5.0"), cuisine1);
        Supply created = SupplyFactory.getNewSupply(SupplyType.DRINK, "Beer", new BigDecimal("5.0"), cuisine1);
        assertThat(created).as("Checking factory creation of a main course").isEqualTo(expectedDrink);
    }

    @Test
    public void shouldThrowExceptionForIllegalType() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        Throwable thrown = catchThrowable(() -> SupplyFactory.getNewSupply(null, "Bigos", new BigDecimal("30.0"), cuisine1));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Illegal supply type.");
    }

    @Test
    public void shouldThrowExceptionForNullName() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        Throwable thrown = catchThrowable(() -> SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, null, new BigDecimal("30.0"), cuisine1));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Illegal supply name.");
    }

    @Test
    public void shouldParseNewMainCourse() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        final MainCourse expectedMainCourse = new MainCourse("Bigos", new BigDecimal("30.0"), cuisine1);
        Supply created = SupplyFactory.parseNewSupply(
                SupplyType.MAIN_COURSE,
                array("Bigos", "30.0", "Polish"));
        assertThat(created).as("Checking factory parsing of a main course").isEqualTo(expectedMainCourse);
    }

    @Test
    public void shouldParseNewDessert() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        final Dessert expectedDessert = new Dessert("Sernik", new BigDecimal("10.0"), cuisine1);
        Supply created = SupplyFactory.parseNewSupply(
                SupplyType.DESSERT,
                array("Sernik", "10.0", "Polish"));
        assertThat(created).as("Checking factory parsing of a dessert").isEqualTo(expectedDessert);
    }

    @Test
    public void shouldParseNewDrink() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        final Drink expectedDrink = new Drink("Beer", new BigDecimal("5.0"), cuisine1);
        Supply created = SupplyFactory.parseNewSupply(
                SupplyType.DRINK,
                array("Beer", "5.0", "Polish"));
        assertThat(created).as("Checking factory parsing of a drink").isEqualTo(expectedDrink);
    }

    @Test
    public void shouldParseNewDrink_WithoutCuisine() throws Exception {
        final Drink expectedDrink = new Drink("Water", new BigDecimal("30.0"), null);
        Supply created = SupplyFactory.parseNewSupply(
                SupplyType.DRINK,
                array("Water", "30.0"));
        assertThat(created).as("Checking factory parsing of a drink").isEqualTo(expectedDrink);
    }
}