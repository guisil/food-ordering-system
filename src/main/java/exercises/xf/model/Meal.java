package exercises.xf.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by guisil on 31/01/2017.
 */
public class Meal implements PricedItem {

    private final Supply mainCourse;
    private final Supply dessert;

    public Meal(Supply mainCourse, Supply dessert) {

        if (mainCourse == null && dessert == null) {
            throw new IllegalArgumentException("Empty meal.");
        }

        this.mainCourse = mainCourse;
        this.dessert = dessert;
    }


    @Override
    public String getName() {
        return getMainCourse().map(Supply::getName).orElse("No Main Course") +
                " + " + getDessert().map(Supply::getName).orElse("No Dessert");
    }

    @Override
    public String getDescription() {
        return "Meal";
    }

    @Override
    public BigDecimal getPrice() {
        return getMainCourse().map(Supply::getPrice).orElse(BigDecimal.ZERO).add(
                getDessert().map(Supply::getPrice).orElse(BigDecimal.ZERO));
    }

    Optional<Supply> getMainCourse() {
        return Optional.ofNullable(mainCourse);
    }

    Optional<Supply> getDessert() {
        return Optional.ofNullable(dessert);
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
        return SupplyType.MAIN_COURSE.getDescription() + ": " + getMainCourse().map(Supply::toString).orElse("No Main Course") +
                ", " + SupplyType.DESSERT.getDescription() + ": " + getDessert().map(Supply::toString).orElse("No Dessert");
    }
}
