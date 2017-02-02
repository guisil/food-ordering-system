package exercises.xf.model;

import java.math.BigDecimal;

/**
 * Class representing a drink.
 *
 * Created by guisil on 31/01/2017.
 */
public class Drink extends Supply {

    /**
     * Constructs a drink.
     * @param name name of the drink
     * @param price price of the drink
     * @param cuisine cuisine to which the drink is associated (if any)
     */
    Drink(String name, BigDecimal price, Cuisine cuisine) {
        super(name, price, cuisine);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return SupplyType.DRINK.getDescription();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Drink)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
