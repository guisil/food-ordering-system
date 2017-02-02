package exercises.xf.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by guisil on 02/02/2017.
 */
public class Menu {

    private final List<Cuisine> cuisines;
    private final Map<SupplyType, List<Supply>> supplies;

    public Menu(List<Cuisine> cuisines, Map<SupplyType, List<Supply>> supplies) {
        this.cuisines = cuisines;
        this.supplies = supplies;
    }


    public List<Cuisine> getCuisines() {
        return Collections.unmodifiableList(cuisines);
    }

    public Map<SupplyType, List<Supply>> getSupplies() {
        return Collections.unmodifiableMap(supplies);
    }
}
