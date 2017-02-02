package exercises.xf.model;

import java.math.BigDecimal;

/**
 * Created by guisil on 31/01/2017.
 */
public class Drink extends Supply {

    Drink(String name, BigDecimal price, Cuisine cuisine) {
        super(name, price, cuisine);
    }

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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Drink(" + super.toString() + ")";
    }
}
