package exercises.xf.model;

/**
 * Created by guisil on 31/01/2017.
 */
public class Dessert extends Food {

    Dessert(String name, double price, Cuisine cuisine) {
        super(name, price, cuisine);
    }

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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Dessert(" + super.toString() + ")";
    }
}