package exercises.xf.model;

import java.math.BigDecimal;

/**
 * Class representing a main course.
 *
 * Created by guisil on 31/01/2017.
 */
public class MainCourse extends Supply {

    /**
     * Constructs a main course.
     * @param name name of the main course
     * @param price price of the main course
     * @param cuisine cuisine to which the main course is associated
     * @throws IllegalArgumentException if the cuisine is null
     */
    MainCourse(String name, BigDecimal price, Cuisine cuisine) throws IllegalArgumentException {
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
        return SupplyType.MAIN_COURSE.getDescription();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MainCourse)) {
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
