package exercises.xf.model;

import java.util.Comparator;

/**
 * Class with utilities related with supplies.
 *
 * Created by guisil on 02/02/2017.
 */
public class SupplyUtil {

    /**
     * Comparator to be used when sorting supplies.
     */
    public static final Comparator<Supply> supplyComparator = (s1, s2) -> {
        if (!s1.getCuisine().isPresent() && !s2.getCuisine().isPresent()) {
            return s1.getName().compareTo(s2.getName());
        }
        if (!s1.getCuisine().isPresent()) {
            return 1;
        }
        if (!s2.getCuisine().isPresent()) {
            return -1;
        }

        int comparison = s1.getCuisine().get().getName().compareTo(s2.getCuisine().get().getName());
        if (comparison != 0) {
            return comparison;
        }
        return s1.getName().compareTo(s2.getName());
    };
}
