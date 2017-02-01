package exercises.xf.model;

/**
 * Created by guisil on 01/02/2017.
 */
public enum SupplyType {
    MAIN_COURSE(3, 3),
    DESSERT(3, 3),
    DRINK(3, 2);

    private int numberOfFields;
    private int mandatoryNumberOfFields;

    SupplyType(int numberOfFields, int mandatoryNumberOfFields) {
        this.numberOfFields = numberOfFields;
        this.mandatoryNumberOfFields = mandatoryNumberOfFields;
    }

    public int getNumberOfFields() {
        return numberOfFields;
    }

    public int getMandatoryNumberOfFields() {
        return mandatoryNumberOfFields;
    }
}
