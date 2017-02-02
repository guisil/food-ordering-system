package exercises.xf.model;

/**
 * Enumeration representing the different types of supply.
 *
 * Created by guisil on 01/02/2017.
 */
public enum SupplyType {
    MAIN_COURSE(3, 3, "Main Course"),
    DESSERT(3, 3, "Dessert"),
    DRINK(3, 2, "Drink");

    private int numberOfFields;
    private int numberOfMandatoryFields;
    private String description;

    /**
     * Constructs a new supply type.
     * @param numberOfFields number of fields for the supply type
     * @param numberOfMandatoryFields number of mandatory fields for the supply type
     * @param description description of the supply type
     */
    SupplyType(int numberOfFields, int numberOfMandatoryFields, String description) {
        this.numberOfFields = numberOfFields;
        this.numberOfMandatoryFields = numberOfMandatoryFields;
        this.description = description;
    }


    /**
     * Returns the number of fields for the supply type.
     * @return number of fields for the supply type
     */
    public int getNumberOfFields() {
        return numberOfFields;
    }

    /**
     * Returns the number of mandatory fields for the supply type.
     * @return number of mandatory fields for the supply type
     */
    public int getNumberOfMandatoryFields() {
        return numberOfMandatoryFields;
    }

    /**
     * Returns the description of the supply type.
     * @return description of the supply type
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the description (in the plural) of the supply type.
     * @return description (in the plural) of the supply type
     */
    public String getPluralDescription() {
        return description + "s";
    }
}
