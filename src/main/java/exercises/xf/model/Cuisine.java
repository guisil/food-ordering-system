package exercises.xf.model;

import java.util.Objects;

/**
 * Created by guisil on 31/01/2017.
 */
public class Cuisine {

    private final String name;

    public Cuisine(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Empty name.");
        }

        this.name = name;
    }


    String getName() {
        return name;
    }


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

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Cuisine(Name: " + name + ")";
    }
}
