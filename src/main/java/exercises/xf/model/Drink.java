package exercises.xf.model;

import java.util.Objects;

/**
 * Created by guisil on 31/01/2017.
 */
public class Drink implements PricedItem {

    private final String name;
    private final double price;

    Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Drink)) {
            return false;
        }
        Drink other = (Drink) obj;
        return Objects.equals(other.name, name)
                && other.price == price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Drink(Name: " + name + ", Price: " + price + ")";
    }
}
