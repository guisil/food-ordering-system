package exercises.xf.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * Class representing a supply (food item, drink).
 *
 * Created by guisil on 31/01/2017.
 */
public abstract class Supply implements PricedItem {

    private final String name;
    private final BigDecimal price;
    private final Cuisine cuisine;

    /**
     * Constructs a supply.
     * @param name name of the supply
     * @param price price of the supply
     * @param cuisine cuisine to which the supply is associated
     * @throws IllegalArgumentException if the name of the supply is null
     */
    protected Supply(String name, BigDecimal price, Cuisine cuisine) throws IllegalArgumentException {

        if (name == null) {
            throw new IllegalArgumentException("Illegal supply name.");
        }

        this.name = name;
        this.price = price;
        this.cuisine = cuisine;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Returns an optional containing the cuisine to which the supply is associated.
     * @return optional containing the cuisine to which the supply is associated
     */
    public Optional<Cuisine> getCuisine() {
        return Optional.ofNullable(cuisine);
    }


    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, price, cuisine);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name + " (" + price + " PLN)";
    }
}
