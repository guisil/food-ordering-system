package exercises.xf.model;

import java.util.Objects;

/**
 * Created by guisil on 31/01/2017.
 */
public class Meal implements PricedItem {

    private final MainCourse mainCourse;
    private final Dessert dessert;

    Meal(MainCourse mainCourse, Dessert dessert) {
        this.mainCourse = mainCourse;
        this.dessert = dessert;
    }

    @Override
    public String getName() {
        return mainCourse.getName() + " + " + dessert.getName();
    }

    @Override
    public double getPrice() {
        return mainCourse.getPrice() + dessert.getPrice();
    }

    MainCourse getMainCourse() {
        return mainCourse;
    }

    Dessert getDessert() {
        return dessert;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Meal)) {
            return false;
        }
        Meal other = (Meal) obj;
        return Objects.equals(other.mainCourse, mainCourse)
                && Objects.equals(other.dessert, dessert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainCourse, dessert);
    }

    @Override
    public String toString() {
        return "Meal(" + mainCourse + ", " + dessert + ")";
    }
}
