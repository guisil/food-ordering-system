package exercises.xf.model;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.util.Lists.*;

/**
 * Created by guisil on 31/01/2017.
 */
public class OrderTest {

    private static final Cuisine cuisine1 = new Cuisine("Polish");
    private static final Cuisine cuisine2 = new Cuisine("Portuguese");

    private static final MainCourse mainCourse1 = new MainCourse("Bigos", 30.0, cuisine1);
    private static final MainCourse mainCourse2 = new MainCourse("Bacalhau", 25.0, cuisine2);

    private static final Dessert dessert1 = new Dessert("Sernik", 10.0, cuisine1);
    private static final Dessert dessert2 = new Dessert("Pastel de Nata", 11.5, cuisine2);

    private static final Meal meal1 = new Meal(mainCourse1, dessert1);
    private static final Meal meal2 = new Meal(mainCourse2, dessert2);

    private static final Drink drink1 = new Drink("Beer", 5.0);
    private static final Drink drink2 = new Drink("Wine", 20.0);

    private static final Order order1 = new Order(newArrayList(meal1, drink1));
    private static final Order order2 = new Order(newArrayList(meal1, drink1));
    private static final Order order3 = new Order(newArrayList(meal1, drink1));
    private static final Order order4 = new Order(newArrayList(meal1, drink2));
    private static final Order order5 = new Order(newArrayList(meal2, drink1));

    @Test
    public void testEquals() throws Exception {
        assertThat(order1).as("Checking if equals is reflexive").isEqualTo(order1);
        assertThat(order1.equals(order2) && order2.equals(order1)).as("Checking if equals is symmetric").isTrue();
        assertThat(order1.equals(order2) && order2.equals(order3) && order1.equals(order3)).as("Checking if equals is transitive").isTrue();
        assertThat(order1.equals(order2) && order1.equals(order2) && order1.equals(order2)).as("Checking if equals is consistent").isTrue();
        assertThat(order1).as("Checking equals with null parameter").isNotEqualTo(null);
        assertThat(order1).as("Checking equals for different objects").isNotEqualTo(order4);
        assertThat(order4).as("Checking equals for different objects").isNotEqualTo(order5);
    }

    @Test
    public void testHashCode() throws Exception {
        assertThat(order1.hashCode()).as("Checking hashCode for the same object").isEqualTo(order1.hashCode());
        assertThat(order1.hashCode()).as("Checking hashCode for equal objects").isEqualTo(order2.hashCode());
    }
}