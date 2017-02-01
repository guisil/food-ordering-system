package exercises.xf.model;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by guisil on 01/02/2017.
 */
public class SupplyFactoryTest {

    @Test
    public void shouldGetNewMainCourse() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        final MainCourse expectedMainCourse = new MainCourse("Bigos", 30.0, cuisine1);
        Supply created = SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", 30.0, cuisine1);
        assertThat(created).as("Checking factory creation of a main course").isEqualTo(expectedMainCourse);
    }

    @Test
    public void shouldGetNewDessert() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        final Dessert expectedDessert = new Dessert("Sernik", 10.0, cuisine1);
        Supply created = SupplyFactory.getNewSupply(SupplyType.DESSERT, "Sernik", 10.0, cuisine1);
        assertThat(created).as("Checking factory creation of a dessert").isEqualTo(expectedDessert);
    }

    @Test
    public void shouldGetNewDrink() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        final Drink expectedDrink = new Drink("Beer", 5.0, cuisine1);
        Supply created = SupplyFactory.getNewSupply(SupplyType.DRINK, "Beer", 5.0, cuisine1);
        assertThat(created).as("Checking factory creation of a main course").isEqualTo(expectedDrink);
    }

    @Test
    public void shouldThrowExceptionForIllegalType() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        Throwable thrown = catchThrowable(() -> SupplyFactory.getNewSupply(null, "Bigos", 30.0, cuisine1));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Illegal supply type.");
    }

    @Test
    public void shouldThrowExceptionForNullName() throws Exception {
        final Cuisine cuisine1 = new Cuisine("Polish");
        Throwable thrown = catchThrowable(() -> SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, null, 30.0, cuisine1));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Illegal supply name.");
    }
}