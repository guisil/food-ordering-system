package exercises.xf.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by guisil on 31/01/2017.
 */
public abstract class Supply implements PricedItem {

    private final String name;
    private final BigDecimal price;
    private final Cuisine cuisine;

    protected Supply(String name, BigDecimal price, Cuisine cuisine) {

        if (name == null) {
            throw new IllegalArgumentException("Illegal supply name.");
        }

        this.name = name;
        this.price = price;
        this.cuisine = cuisine;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    public Optional<Cuisine> getCuisine() {
        return Optional.ofNullable(cuisine);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Supply)) {
            return false;
        }
        Supply other = (Supply) obj;
        return Objects.equals(other.name, name)
                && Objects.equals(other.price, price)
                && Objects.equals(other.cuisine, cuisine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, cuisine);
    }

    @Override
    public String toString() {
        return name + " (" + price + " PLN)";
    }
}
