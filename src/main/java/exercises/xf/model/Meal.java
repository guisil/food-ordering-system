package exercises.xf.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * Class representing a meal.
 *
 * Created by guisil on 31/01/2017.
 */
public class Meal implements PricedItem {

    private final Supply mainCourse;
    private final Supply dessert;

    /**
     * Constructs a meal.
     * @param mainCourse main course of the meal (if any)
     * @param dessert dessert of the meal (if any)
     * @throws IllegalArgumentException if both the main course and the dessert are null
     */
    public Meal(Supply mainCourse, Supply dessert) throws IllegalArgumentException {

        if (mainCourse == null && dessert == null) {
            throw new IllegalArgumentException("Empty meal.");
        }

        this.mainCourse = mainCourse;
        this.dessert = dessert;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return getMainCourse().map(Supply::getName).orElse("No Main Course") +
                " + " + getDessert().map(Supply::getName).orElse("No Dessert");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Meal";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getPrice() {
        return getMainCourse().map(Supply::getPrice).orElse(BigDecimal.ZERO).add(
                getDessert().map(Supply::getPrice).orElse(BigDecimal.ZERO));
    }

    /**
     * Returns an optional containing the main course.
     * @return optional containing the main course
     */
    Optional<Supply> getMainCourse() {
        return Optional.ofNullable(mainCourse);
    }

    /**
     * Returns a optional containing the dessert.
     * @return optional containing the dessert
     */
    Optional<Supply> getDessert() {
        return Optional.ofNullable(dessert);
    }


    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(mainCourse, dessert);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return SupplyType.MAIN_COURSE.getDescription() + ": " + getMainCourse().map(Supply::toString).orElse("No Main Course") +
                ", " + SupplyType.DESSERT.getDescription() + ": " + getDessert().map(Supply::toString).orElse("No Dessert");
    }
}
