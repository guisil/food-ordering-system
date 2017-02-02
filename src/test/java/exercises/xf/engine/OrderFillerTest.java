package exercises.xf.engine;

import exercises.xf.model.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.util.Lists.*;

/**
 * Test class for OrderFiller.
 *
 * Created by guisil on 31/01/2017.
 */
public class OrderFillerTest {

    private OrderFiller orderFiller;

    @Before
    public void setUp() throws Exception {
        orderFiller = new OrderFiller(menu());
    }

    @Test
    public void shouldCreateOrderFromValidInput() throws Exception {
        Cuisine polishCuisine = new Cuisine("Polish");
        Order expected = new Order(
                newArrayList(
                        new Meal(
                                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", new BigDecimal("29.99"), polishCuisine),
                                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Sernik", new BigDecimal("11.0"), polishCuisine)),
                        SupplyFactory.getNewSupply(SupplyType.DRINK, "Mead", new BigDecimal("25.0"), polishCuisine)
                )
        );
        Optional<Order> filled = orderFiller
                .getOrderFromInput(getClass().getClassLoader()
                        .getResourceAsStream("exercises/xf/engine/valid_simulated_input_1.txt"));
        assertThat(filled.get()).as("Checking if filled order is as expected").isEqualTo(expected);
    }

    @Test
    public void shouldCreateOrderWithEmptyItemsWhenNoMainCourseIsSelected() throws Exception {
        Cuisine mexicanCuisine = new Cuisine("Mexican");
        Order expected = new Order(
                newArrayList(
                        new Meal(
                                null,
                                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Churros", new BigDecimal("9.0"), mexicanCuisine)),
                        SupplyFactory.getNewSupply(SupplyType.DRINK, "Tequila", new BigDecimal("30.0"), mexicanCuisine)
                )
        );
        Optional<Order> filled = orderFiller
                .getOrderFromInput(getClass().getClassLoader()
                        .getResourceAsStream("exercises/xf/engine/valid_simulated_input_2.txt"));
        assertThat(filled.get()).as("Checking if filled order is as expected, when no main course is selected").isEqualTo(expected);
    }

    @Test
    public void shouldCreateOrderWithEmptyItemsWhenNoMealIsSelected() throws Exception {
        Order expected = new Order(
                newArrayList(
                        SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", new BigDecimal("30.0"), new Cuisine("Polish"))
                )
        );
        Optional<Order> filled = orderFiller
                .getOrderFromInput(getClass().getClassLoader()
                        .getResourceAsStream("exercises/xf/engine/valid_simulated_input_3.txt"));
        assertThat(filled.get()).as("Checking if filled order is as expected, when no meal is selected").isEqualTo(expected);
    }

    @Test
    public void shouldNotCreateOrderWhenNothingIsSelected() throws Exception {
        Optional<Order> filled = orderFiller
                .getOrderFromInput(getClass().getClassLoader().
                        getResourceAsStream("exercises/xf/engine/valid_simulated_input_4.txt"));
        assertThat(filled.isPresent()).as("Checking if filled order is as expected, when no meal is selected").isFalse();
    }

    @Test
    public void shouldCreateOrderFromLongerValidInput() throws Exception {
        Cuisine polishCuisine = new Cuisine("Polish");
        Cuisine italianCuisine = new Cuisine("Italian");
        Order expected = new Order(
                newArrayList(
                        new Meal(
                                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pierogi", new BigDecimal("14.0"), polishCuisine),
                                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Naleśniki", new BigDecimal("12.0"), polishCuisine)),
                        SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", new BigDecimal("30.0"), polishCuisine),
                        new Meal(
                                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Minestrone", new BigDecimal("10.0"), italianCuisine),
                                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Panna Cotta", new BigDecimal("8.0"), italianCuisine)),
                        SupplyFactory.getNewSupply(SupplyType.DRINK, "Juice", new BigDecimal("3.5"), null)
                )
        );
        Optional<Order> filled = orderFiller
                .getOrderFromInput(getClass().getClassLoader()
                        .getResourceAsStream("exercises/xf/engine/valid_simulated_input_5.txt"));
        assertThat(filled.get()).as("Checking if filled order is as expected").isEqualTo(expected);
    }

    @Test
    public void shouldSkipInputNotMatchingChoicesUntilValidInput() throws Exception {
        Cuisine polishCuisine = new Cuisine("Polish");
        Order expected = new Order(
                newArrayList(
                        new Meal(
                                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pierogi", new BigDecimal("14.0"), polishCuisine),
                                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Sernik", new BigDecimal("11.0"), polishCuisine)),
                        SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", new BigDecimal("30.0"), polishCuisine)
                )
        );
        Optional<Order> filled = orderFiller
                .getOrderFromInput(getClass().getClassLoader()
                        .getResourceAsStream("exercises/xf/engine/invalid_simulated_input_1.txt"));
        assertThat(filled.get()).as("Checking if filled order is as expected, after skipping invalid input").isEqualTo(expected);
    }


    private Menu menu() {
        return new Menu(cuisines(), supplies());
    }

    private List<Cuisine> cuisines() {
        return newArrayList(
                new Cuisine("Italian"),
                new Cuisine("Mexican"),
                new Cuisine("Polish")
        );
    }

    private Map<SupplyType, List<Supply>> supplies() {
        Map<SupplyType, List<Supply>> supplies = new HashMap<>();
        supplies.put(SupplyType.MAIN_COURSE, mainCourses());
        supplies.put(SupplyType.DESSERT, desserts());
        supplies.put(SupplyType.DRINK, drinks());
        return supplies;
    }

    private List<Supply> mainCourses() {

        Cuisine polishCuisine = new Cuisine("Polish");
        Cuisine mexicanCuisine = new Cuisine("Mexican");
        Cuisine italianCuisine = new Cuisine("Italian");

        return newArrayList(
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Barszcz", new BigDecimal("10.0"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", new BigDecimal("29.99"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pierogi", new BigDecimal("14.0"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Placki Ziemniaczane", new BigDecimal("20.5"), polishCuisine),

                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Burrito", new BigDecimal("20.3"), mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Enchilada", new BigDecimal("30.5"), mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Tacos", new BigDecimal("24.4"), mexicanCuisine),

                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Lasagna", new BigDecimal("22.0"), italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Minestrone", new BigDecimal("10.0"), italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pasta al Pesto", new BigDecimal("15.0"), italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pizza Margherita", new BigDecimal("12.5"), italianCuisine)
        );
    }

    private List<Supply> desserts() {

        Cuisine polishCuisine = new Cuisine("Polish");
        Cuisine mexicanCuisine = new Cuisine("Mexican");
        Cuisine italianCuisine = new Cuisine("Italian");

        return newArrayList(
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Naleśniki", new BigDecimal("12.0"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Sernik", new BigDecimal("11.0"), polishCuisine),

                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Churros", new BigDecimal("9.0"), mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Jericalla", new BigDecimal("10.0"), mexicanCuisine),

                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Panna Cotta", new BigDecimal("8.0"), italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Tiramisù", new BigDecimal("12.5"), italianCuisine)
        );
    }

    private List<Supply> drinks() {

        Cuisine polishCuisine = new Cuisine("Polish");
        Cuisine mexicanCuisine = new Cuisine("Mexican");
        Cuisine italianCuisine = new Cuisine("Italian");

        return newArrayList(
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Beer", new BigDecimal("5.0"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Mead", new BigDecimal("25.0"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", new BigDecimal("30.0"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Tequila", new BigDecimal("30.0"), mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Wine", new BigDecimal("20.0"), italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Water", new BigDecimal("30.0"), null),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Juice", new BigDecimal("3.5"), null)
        );
    }
}