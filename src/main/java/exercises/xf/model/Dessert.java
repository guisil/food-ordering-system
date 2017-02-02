package exercises.xf.model;

import java.math.BigDecimal;

/**
 * Class representing a dessert.
 *
 * Created by guisil on 31/01/2017.
 */
public class Dessert extends Supply {

    /**
     * Constructs a dessert.
     * @param name name of the dessert
     * @param price price of the dessert
     * @param cuisine cuisine to which the dessert is associated
     * @throws IllegalArgumentException if the cuisine is null
     */
    Dessert(String name, BigDecimal price, Cuisine cuisine) throws IllegalArgumentException {
        super(name, price, cuisine);

        if (cuisine == null) {
            throw new IllegalArgumentException("Invalid cuisine.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return SupplyType.DESSERT.getDescription();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Dessert)) {
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
