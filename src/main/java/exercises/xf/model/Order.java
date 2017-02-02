package exercises.xf.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by guisil on 31/01/2017.
 */
public class Order {

    private final List<PricedItem> items;

    private final BigDecimal price;

    public Order(List<PricedItem> items) {

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Empty order.");
        }

        this.items = items;

        price = items.stream().map(PricedItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public List<PricedItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal getPrice() {
        return price;
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
        return items.stream().map(item -> item.getDescription() + ": " + item.toString())
                .collect(Collectors.joining("\n"));
    }
}
