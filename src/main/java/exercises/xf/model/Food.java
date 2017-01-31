package exercises.xf.model;

import java.util.Objects;

/**
 * Created by guisil on 31/01/2017.
 */
public abstract class Food implements PricedItem {

    private final String name;
    private final double price;

    private final Cuisine cuisine;

    Food(String name, double price, Cuisine cuisine) {
        this.name = name;
        this.price = price;
        this.cuisine = cuisine;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    Cuisine getCuisine() {
        return cuisine;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Food)) {
            return false;
        }
        Food other = (Food) obj;
        return Objects.equals(other.name, name)
                && other.price == price
                && Objects.equals(other.cuisine, cuisine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, cuisine);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Price: " + price + ", Cuisine: " + cuisine;
    }
}
