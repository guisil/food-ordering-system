package exercises.xf.model;

import java.math.BigDecimal;

/**
 * Factory class for supplies.
 *
 * Created by guisil on 01/02/2017.
 */
public class SupplyFactory {

    /**
     * Creates a new supply with the fields already passed.
     * @param type type of the supply
     * @param name name of the supply
     * @param price price of the supply
     * @param cuisine cuisine to which the supply is associated
     * @return new supply
     * @throws IllegalArgumentException if the type is not valid
     */
    public static Supply getNewSupply(SupplyType type, String name, BigDecimal price, Cuisine cuisine)
            throws IllegalArgumentException {

        if (SupplyType.MAIN_COURSE.equals(type)) {
            return new MainCourse(name, price, cuisine);
        }
        if (SupplyType.DESSERT.equals(type)) {
            return new Dessert(name, price, cuisine);
        }
        if (SupplyType.DRINK.equals(type)) {
            return new Drink(name, price, cuisine);
        }

        throw new IllegalArgumentException("Invalid supply type.");
    }

    /**
     * Creates a new supply by parsing a string containing the fields
     * @param type type of the supply
     * @param fields string containing the fields
     * @return new supply
     * @throws IllegalArgumentException if the type is not valid
     */
    public static Supply parseNewSupply(SupplyType type, String[] fields) throws IllegalArgumentException {
        return getNewSupply(
                type,
                fields[0],
                new BigDecimal(fields[1]),
                fields.length == 3 ? new Cuisine(fields[2]) : null);
    }
}
