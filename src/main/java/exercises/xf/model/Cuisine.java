package exercises.xf.model;

import java.util.Objects;

/**
 * Class representing a cuisine.
 *
 * Created by guisil on 31/01/2017.
 */
public class Cuisine {

    private final String name;

    /**
     * Constructs a cuisine.
     * @param name name of the cuisine
     * @throws IllegalArgumentException if the name is null
     */
    public Cuisine(String name) throws IllegalArgumentException {

        if (name == null) {
            throw new IllegalArgumentException("Empty name.");
        }

        this.name = name;
    }

    /**
     * Returns the name of the cuisine
     * @return name of the cuisine
     */
    public String getName() {
        return name;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Cuisine)) {
            return false;
        }
        Cuisine other = (Cuisine) obj;
        return Objects.equals(other.name, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name;
    }
}
