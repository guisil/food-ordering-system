package exercises.xf.engine;

import exercises.xf.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.util.Lists.*;

/**
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

        Map<SupplyType, List<Supply>> loaded = loader.loadSupplies(supplyFiles);
        assertThat(loaded.get(SupplyType.MAIN_COURSE)).as("Checking main courses loaded from file").isEqualTo(expectedMainCourses());
        assertThat(loaded.get(SupplyType.DESSERT)).as("Checking desserts loaded from file").isEqualTo(expectedDesserts());
        assertThat(loaded.get(SupplyType.DRINK)).as("Checking drinks loaded from file").isEqualTo(expectedDrinks());
    }


    private List<Supply> expectedMainCourses() {

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

    private List<Supply> expectedDesserts() {

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

    private List<Supply> expectedDrinks() {

        Cuisine polishCuisine = new Cuisine("Polish");
        Cuisine mexicanCuisine = new Cuisine("Mexican");
        Cuisine italianCuisine = new Cuisine("Italian");

        return newArrayList(
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Beer", 5.0, polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK,"Mead", 25.0, polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Wódka", 30.0, polishCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Tequila", 30.0, mexicanCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Wine", 20.0, italianCuisine),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Water", 30.0, null),
                SupplyFactory.getNewSupply(SupplyType.DRINK, "Juice", 3.5, null)
        );
    }
}