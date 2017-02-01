package exercises.xf.engine;

import exercises.xf.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.util.Lists.*;

/**
 * Created by guisil on 31/01/2017.
 */
public class OrderFillerTest {

    private OrderFiller orderFiller;

    @Before
    public void setUp() throws Exception {
        orderFiller = new OrderFiller(supplies());
    }

    @Test
    public void shouldCreateOrderFromValidInput() throws Exception {
        Order expected = new Order(
                newArrayList(
                        new Meal(
                                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", 29.99, new Cuisine("Polish")),
                                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Tiramisù", 12.5, new Cuisine("Italian"))),
                        SupplyFactory.getNewSupply(SupplyType.DRINK, "Mead", 25.0, new Cuisine("Polish"))
                )
        );
        Optional<Order> filled = orderFiller
                .getOrderFromInput(getClass().getClassLoader()
                        .getResourceAsStream("exercises/xf/engine/valid_simulated_input_1.txt"));
        assertThat(filled.get()).as("Checking if filled order is as expected").isEqualTo(expected);
    }

    @Test
    public void shouldCreateOrderWithEmptyItemsWhenNoMainCourseIsSelected() throws Exception {
        Order expected = new Order(
                newArrayList(
                        new Meal(
                                null,
                                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Churros", 9.0, new Cuisine("Mexican"))),
                        SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", 30.0, new Cuisine("Polish"))
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
                        SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", 30.0, new Cuisine("Polish"))
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
        Order expected = new Order(
                newArrayList(
                        new Meal(
                                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pierogi", 14.0, new Cuisine("Polish")),
                                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Churros", 9.0, new Cuisine("Mexican"))),
                        SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", 30.0, new Cuisine("Polish")),
                        new Meal(
                                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Burrito", 20.3, new Cuisine("Mexican")),
                                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Panna Cotta", 8.0, new Cuisine("Italian"))),
                        SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", 30.0, new Cuisine("Polish"))
                )
        );
        Optional<Order> filled = orderFiller
                .getOrderFromInput(getClass().getClassLoader()
                        .getResourceAsStream("exercises/xf/engine/valid_simulated_input_5.txt"));
        assertThat(filled.get()).as("Checking if filled order is as expected").isEqualTo(expected);
    }

    @Test
    public void shouldSkipInputNotMatchingChoicesUntilValidInput() throws Exception {
        Order expected = new Order(
                newArrayList(
                        new Meal(
                                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pierogi", 14.0, new Cuisine("Polish")),
                                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Churros", 9.0, new Cuisine("Mexican"))),
                        SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", 30.0, new Cuisine("Polish"))
                )
        );
        Optional<Order> filled = orderFiller
                .getOrderFromInput(getClass().getClassLoader()
                        .getResourceAsStream("exercises/xf/engine/invalid_simulated_input_2.txt"));
        assertThat(filled.get()).as("Checking if filled order is as expected, after skipping invalid input").isEqualTo(expected);
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
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Barszcz", 10.0, polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", 29.99, polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pierogi", 14.0, polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Placki Ziemniaczane", 20.5, polishCuisine),

                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Burrito", 20.3, mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Enchilada", 30.5, mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Tacos", 24.4, mexicanCuisine),

                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Lasagna", 22.0, italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Minestrone", 10.0, italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pasta al Pesto", 15.0, italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pizza Margherita", 12.5, italianCuisine)
        );
    }

    private List<Supply> desserts() {

        Cuisine polishCuisine = new Cuisine("Polish");
        Cuisine mexicanCuisine = new Cuisine("Mexican");
        Cuisine italianCuisine = new Cuisine("Italian");

        return newArrayList(
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Naleśniki", 12.0, polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Sernik", 11.0, polishCuisine),

                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Churros", 9.0, mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Jericalla", 10.0, mexicanCuisine),

                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Panna Cotta", 8.0, italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Tiramisù", 12.5, italianCuisine)
        );
    }

    private List<Supply> drinks() {

        Cuisine polishCuisine = new Cuisine("Polish");
        Cuisine mexicanCuisine = new Cuisine("Mexican");
        Cuisine italianCuisine = new Cuisine("Italian");

        return newArrayList(
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Beer", 5.0, polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Mead", 25.0, polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", 30.0, polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Tequila", 30.0, mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Wine", 20.0, italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Water", 30.0, italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Juice", 3.5, italianCuisine)
        );
    }
}