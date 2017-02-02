package exercises.xf.engine;

import exercises.xf.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.util.Lists.*;

/**
 * Test class for DataLoader.
 *
 * Created by guisil on 31/01/2017.
 */
public class DataLoaderTest {

    private DataLoader loader;

    @Before
    public void setUp() throws Exception {
        loader = new DataLoader();
    }

    @Test
    public void loadSupplies() throws Exception {

        Map<SupplyType, File> supplyFiles = new HashMap<>();
        supplyFiles.put(SupplyType.MAIN_COURSE, new File("exercises/xf/engine/test_main_courses.txt"));
        supplyFiles.put(SupplyType.DESSERT, new File("exercises/xf/engine/test_desserts.txt"));
        supplyFiles.put(SupplyType.DRINK, new File("exercises/xf/engine/test_drinks.txt"));

        Menu loaded = loader.loadMenu(supplyFiles);
        assertThat(loaded.getCuisines()).as("Checking cuisines loaded from files").isEqualTo(expectedCuisines());
        assertThat(loaded.getSupplies().get(SupplyType.MAIN_COURSE)).as("Checking main courses loaded from file").isEqualTo(expectedMainCourses());
        assertThat(loaded.getSupplies().get(SupplyType.DESSERT)).as("Checking desserts loaded from file").isEqualTo(expectedDesserts());
        assertThat(loaded.getSupplies().get(SupplyType.DRINK)).as("Checking drinks loaded from file").isEqualTo(expectedDrinks());
    }


    private List<Cuisine> expectedCuisines() {
        return newArrayList(
                new Cuisine("Italian"),
                new Cuisine("Mexican"),
                new Cuisine("Polish")
        );
    }

    private List<Supply> expectedMainCourses() {

        Cuisine polishCuisine = new Cuisine("Polish");
        Cuisine mexicanCuisine = new Cuisine("Mexican");
        Cuisine italianCuisine = new Cuisine("Italian");

        return newArrayList(
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Lasagna", new BigDecimal("22.0"), italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Minestrone", new BigDecimal("10.0"), italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pasta al Pesto", new BigDecimal("15.0"), italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pizza Margherita", new BigDecimal("12.5"), italianCuisine),

                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Burrito", new BigDecimal("20.3"), mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Enchilada", new BigDecimal("30.5"), mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Tacos", new BigDecimal("24.4"), mexicanCuisine),

                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Barszcz", new BigDecimal("10.0"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Bigos", new BigDecimal("29.99"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Pierogi", new BigDecimal("14.0"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.MAIN_COURSE, "Placki Ziemniaczane", new BigDecimal("20.5"), polishCuisine)
        );
    }

    private List<Supply> expectedDesserts() {

        Cuisine polishCuisine = new Cuisine("Polish");
        Cuisine mexicanCuisine = new Cuisine("Mexican");
        Cuisine italianCuisine = new Cuisine("Italian");

        return newArrayList(
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Panna Cotta", new BigDecimal("8.0"), italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Tiramisù", new BigDecimal("12.5"), italianCuisine),

                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Churros", new BigDecimal("9.0"), mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Jericalla", new BigDecimal("10.0"), mexicanCuisine),

                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Naleśniki", new BigDecimal("12.0"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DESSERT, "Sernik", new BigDecimal("11.0"), polishCuisine)
        );
    }

    private List<Supply> expectedDrinks() {

        Cuisine polishCuisine = new Cuisine("Polish");
        Cuisine mexicanCuisine = new Cuisine("Mexican");
        Cuisine italianCuisine = new Cuisine("Italian");

        return newArrayList(
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Wine", new BigDecimal("20.0"), italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Tequila", new BigDecimal("30.0"), mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Beer", new BigDecimal("5.0"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK,"Mead", new BigDecimal("25.0"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", new BigDecimal("30.0"), polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Juice", new BigDecimal("3.5"), null),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Water", new BigDecimal("30.0"), null)
        );
    }
}