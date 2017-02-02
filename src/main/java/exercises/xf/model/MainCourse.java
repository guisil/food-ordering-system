package exercises.xf.model;

import java.math.BigDecimal;

/**
 * Created by guisil on 31/01/2017.
 */
public class MainCourse extends Supply {

    MainCourse(String name, BigDecimal price, Cuisine cuisine) {
        super(name, price, cuisine);

        if (cuisine == null) {
            throw new IllegalArgumentException("Invalid cuisine.");
        }
    }


    @Override
    public String getDescription() {
        return SupplyType.MAIN_COURSE.getDescription();
    }


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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
