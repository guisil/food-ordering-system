package exercises.xf.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.util.Lists.*;

/**
 * Created by guisil on 31/01/2017.
 */
public class OrderTest {

    private static final Cuisine cuisine1 = new Cuisine("Polish");
    private static final Cuisine cuisine2 = new Cuisine("Portuguese");

    private static final Supply mainCourse1 = new MainCourse("Bigos", new BigDecimal("30.0"), cuisine1);
    private static final Supply mainCourse2 = new MainCourse("Bacalhau", new BigDecimal("25.0"), cuisine2);

    private static final Supply dessert1 = new Dessert("Sernik", new BigDecimal("10.0"), cuisine1);
    private static final Supply dessert2 = new Dessert("Pastel de Nata", new BigDecimal("11.5"), cuisine2);

    private static final Meal meal1 = new Meal(mainCourse1, dessert1);
    private static final Meal meal2 = new Meal(mainCourse2, dessert2);

    private static final Drink drink1 = new Drink("Beer", new BigDecimal("5.0"), cuisine1);
    private static final Drink drink2 = new Drink("Wine", new BigDecimal("20.0"), cuisine2);

    private static final Order order1 = new Order(newArrayList(meal1, drink1));
    private static final Order order2 = new Order(newArrayList(meal1, drink1));
    private static final Order order3 = new Order(newArrayList(meal1, drink1));
    private static final Order order4 = new Order(newArrayList(meal1, drink2));
    private static final Order order5 = new Order(newArrayList(meal2, drink1));

    @Test
    public void testEquals() throws Exception {
        assertThat(order1).as("Checking if equals is reflexive").isEqualTo(order1);
        assertThat(order1.equals(order2) && order2.equals(order1)).as("Checking if equals is symmetric").isTrue();
        assertThat(order1.equals(order2) && order2.equals(order3) && order1.equals(order3)).as("Checking if equals is transitive").isTrue();
        assertThat(order1.equals(order2) && order1.equals(order2) && order1.equals(order2)).as("Checking if equals is consistent").isTrue();
        assertThat(order1).as("Checking equals with null parameter").isNotEqualTo(null);
        assertThat(order1).as("Checking equals for different objects").isNotEqualTo(order4);
        assertThat(order4).as("Checking equals for different objects").isNotEqualTo(order5);
    }

    @Test
    public void testHashCode() throws Exception {
        assertThat(order1.hashCode()).as("Checking hashCode for the same object").isEqualTo(order1.hashCode());
        assertThat(order1.hashCode()).as("Checking hashCode for equal objects").isEqualTo(order2.hashCode());
    }

    @Test
    public void shouldGetNewOrder() throws Exception {
        Cuisine polishCuisine = new Cuisine("Polish");
        Cuisine italianCuisine = new Cuisine("Italian");
        List<PricedItem> expectedItems = newArrayList(
                new Meal(
                        SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pierogi", new BigDecimal("14.0"), polishCuisine),
                        SupplyFactory.getNewSupply(SupplyType.DESSERT, "Naleśniki", new BigDecimal("12.0"), polishCuisine)),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", new BigDecimal("30.0"), polishCuisine),
                new Meal(
                        SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Minestrone", new BigDecimal("10.0"), italianCuisine),
                        SupplyFactory.getNewSupply(SupplyType.DESSERT, "Panna Cotta", new BigDecimal("8.0"), italianCuisine)),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Juice", new BigDecimal("3.5"), null)
        );
        Order created = new Order(expectedItems);
        assertThat(created.getItems()).as("Checking factory creation of an order").isEqualTo(expectedItems);
    }

    @Test
    public void shouldFailWithNullListOfItems() throws Exception {
        Throwable thrown = catchThrowable(() -> new Order(null));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Empty order.");
    }

    @Test
    public void shouldFailWithEmptyListOfItems() throws Exception {
        Throwable thrown = catchThrowable(() -> new Order(newArrayList()));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Empty order.");
    }

    @Test
    public void priceShouldBeSumOfAllItems() throws Exception {
        Cuisine polishCuisine = new Cuisine("Polish");
        Cuisine italianCuisine = new Cuisine("Italian");
        BigDecimal price1 = new BigDecimal("29.99");
        BigDecimal price2 = new BigDecimal("12.5");
        BigDecimal price3 = new BigDecimal("30.0");
        BigDecimal price4 = new BigDecimal("10.0");
        BigDecimal price5 = new BigDecimal("8.0");
        BigDecimal price6 = new BigDecimal("3.5");
        BigDecimal expectedPrice = new BigDecimal("93.99");
        List<PricedItem> items = newArrayList(
                new Meal(
                        SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", price1, polishCuisine),
                        SupplyFactory.getNewSupply(SupplyType.DESSERT, "Naleśniki", price2, polishCuisine)),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", price3, polishCuisine),
                new Meal(
                        SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Minestrone", price4, italianCuisine),
                        SupplyFactory.getNewSupply(SupplyType.DESSERT, "Panna Cotta", price5, italianCuisine)),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Juice", price6, null)
        );
        Order order = new Order(items);
        assertThat(order.getPrice()).as("Checking price of meal").isEqualTo(expectedPrice);
    }
}