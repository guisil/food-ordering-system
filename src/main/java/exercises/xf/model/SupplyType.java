package exercises.xf.model;

/**
 * Created by guisil on 01/02/2017.
 */
public enum SupplyType {
    MAIN_COURSE(3, 3, "Main Course"),
    DESSERT(3, 3, "Dessert"),
    DRINK(3, 2, "Drink");

    private int numberOfFields;
    private int mandatoryNumberOfFields;
    private String description;

    SupplyType(int numberOfFields, int mandatoryNumberOfFields, String description) {
        this.numberOfFields = numberOfFields;
        this.mandatoryNumberOfFields = mandatoryNumberOfFields;
        this.description = description;
    }


    public int getNumberOfFields() {
        return numberOfFields;
    }

    public int getMandatoryNumberOfFields() {
        return mandatoryNumberOfFields;
    }

    public String getDescription() {
        return description;
    }

    public String getPluralDescription() {
        return description + "s";
    }
}
