package exercises.xf.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Class representing the menu.
 *
 * Created by guisil on 02/02/2017.
 */
public class Menu {

    private final List<Cuisine> cuisines;
    private final Map<SupplyType, List<Supply>> supplies;

    /**
     * Constructs a menu.
     * @param cuisines list of cuisines available in the menu
     * @param supplies map of supplies available in the menu
     */
    public Menu(List<Cuisine> cuisines, Map<SupplyType, List<Supply>> supplies) {
        this.cuisines = cuisines;
        this.supplies = supplies;
    }


    /**
     * Returns the list of available cuisines.
     * @return list of available cuisines
     */
    public List<Cuisine> getCuisines() {
        return Collections.unmodifiableList(cuisines);
    }

    /**
     * Returns the map of available supplies.
     * @return map of available supplies
     */
    public Map<SupplyType, List<Supply>> getSupplies() {
        return Collections.unmodifiableMap(supplies);
    }
}
