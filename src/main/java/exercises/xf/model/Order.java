package exercises.xf.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class representing an order.
 *
 * Created by guisil on 31/01/2017.
 */
public class Order {

    private final List<PricedItem> items;

    private final BigDecimal price;

    /**
     * Constructs an order.
     * @param items list of items that constitute the order
     * @throws IllegalArgumentException if the list of items is null or empty
     */
    public Order(List<PricedItem> items) throws IllegalArgumentException {

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Empty order.");
        }

        this.items = items;

        price = items.stream().map(PricedItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    /**
     * Returns the list of items included in the order.
     * @return list of items included in the order
     */
    List<PricedItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Returns the total price of the order.
     * @return total price of the order
     */
    public BigDecimal getPrice() {
        return price;
    }


    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return items.stream().map(item -> item.getDescription() + ": " + item.toString())
                .collect(Collectors.joining("\n"));
    }
}
