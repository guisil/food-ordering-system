package exercises.xf.model;

import java.math.BigDecimal;

/**
 * Interface representing items with price.
 *
 * Created by guisil on 31/01/2017.
 */
public interface PricedItem {

    /**
     * Returns the name of the item.
     * @return name of the item
     */
    String getName();

    /**
     * Returns the description of the item.
     * @return description of the item
     */
    String getDescription();

    /**
     * Returns the price of the item.
     * @return price of the item
     */
    BigDecimal getPrice();
}
