package exercises.xf.model;

/**
 * Created by guisil on 01/02/2017.
 */
public class SupplyFactory {

    public static Supply getNewSupply(SupplyType type, String name, double price, Cuisine cuisine) {

        if (SupplyType.MAIN_COURSE.equals(type)) {
            return new MainCourse(name, price, cuisine);
        }
        if (SupplyType.DESSERT.equals(type)) {
            return new Dessert(name, price, cuisine);
        }
        if (SupplyType.DRINK.equals(type)) {
            return new Drink(name, price, cuisine);
        }

        throw new IllegalArgumentException("Illegal supply type.");
    }
}
