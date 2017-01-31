package exercises.xf.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by guisil on 31/01/2017.
 */
public class Order {

    private final List<PricedItem> items;

    Order(List<PricedItem> items) {
        this.items = items;
    }

    List<PricedItem> getItems() {
        return items;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Order)) {
            return false;
        }
        Order other = (Order) obj;
        return Objects.equals(other.items, items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return items.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }
}
