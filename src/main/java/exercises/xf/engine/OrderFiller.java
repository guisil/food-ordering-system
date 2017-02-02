package exercises.xf.engine;

import exercises.xf.model.*;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class responsible for filling the order.
 *
 * Created by guisil on 31/01/2017.
 */
public class OrderFiller {

    private final Menu menu;


    /**
     * Constructs an order filler.
     * @param menu menu to be used in the order filling process
     */
    public OrderFiller(Menu menu) {
        this.menu = menu;
    }


    /**
     * Presents the choices to the user and creates an order based on the user's input.
     * @param inputStream stream to read the input from
     * @return optional containing the order
     */
    public Optional<Order> getOrderFromInput(InputStream inputStream) {

        List<PricedItem> choices = new ArrayList<>();

        boolean cliActive = true;

        try (Scanner scanner = new Scanner(inputStream).useDelimiter("\n")) {
            while(cliActive) {

                Cuisine currentCuisine = getCuisineFromInput(scanner).get();

                getMealFromInput(scanner, currentCuisine).ifPresent(choices::add);
                getDrinkFromInput(scanner, currentCuisine).ifPresent(choices::add);

                String answer;
                do {
                    System.out.print("\nWould you like to order something else? (y/n): ");
                    answer = scanner.next();
                } while (!"y".equalsIgnoreCase(answer) && !"n".equalsIgnoreCase(answer));

                if ("n".equalsIgnoreCase(answer)) {
                    cliActive = false;
                }
            }
        }

        try {
            return Optional.of(new Order(choices));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }


    private Optional<Cuisine> getCuisineFromInput(Scanner scanner) {

        List<Cuisine> cuisines = menu.getCuisines();

        System.out.print("Available Cuisines:\n\n");
        IntStream.range(0, cuisines.size()).forEach(i -> System.out.println((i + 1) + " - " + cuisines.get(i)));

        int selection = getValidSelection(scanner, cuisines.size(), 1);
        return Optional.of(cuisines.get(selection - 1));
    }

    private Optional<PricedItem> getMealFromInput(Scanner scanner, Cuisine selectedCuisine) {
        Optional<Supply> chosenMainCourse = getSupplyFromInput(scanner, SupplyType.MAIN_COURSE, menu.getSupplies().get(SupplyType.MAIN_COURSE), selectedCuisine);
        Optional<Supply> chosenDessert = getSupplyFromInput(scanner, SupplyType.DESSERT, menu.getSupplies().get(SupplyType.DESSERT), selectedCuisine);
        try {
            return Optional.of(new Meal(chosenMainCourse.orElse(null), chosenDessert.orElse(null)));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }

    private Optional<Supply> getDrinkFromInput(Scanner scanner, Cuisine selectedCuisine) {
        return getSupplyFromInput(scanner, SupplyType.DRINK, menu.getSupplies().get(SupplyType.DRINK), selectedCuisine);
    }

    private Optional<Supply> getSupplyFromInput(Scanner scanner, SupplyType type, List<Supply> orderItemList, Cuisine selectedCuisine) {

        System.out.print("\nAvailable " + type.getPluralDescription() + " from "+ selectedCuisine + " cuisine:\n\n");

        System.out.println("0 - None");
        List<Supply> cuisineSupply = orderItemList.stream()
                .filter(supply -> !supply.getCuisine().isPresent() || supply.getCuisine().get().equals(selectedCuisine))
                .sorted(SupplyUtil.supplyComparator)
                .collect(Collectors.toList());
        IntStream.range(0, cuisineSupply.size()).forEach(i -> System.out.println((i + 1) + " - " + cuisineSupply.get(i)));

        int selection = getValidSelection(scanner, cuisineSupply.size(), 0);
        if (selection > 0 && selection <= cuisineSupply.size()) {
            return Optional.of(cuisineSupply.get(selection - 1));
        } else {
            return Optional.empty();
        }
    }

    private int getValidSelection(Scanner scanner, int listSize, int minSelection) {
        int selection = -1;
        while(selection < minSelection || selection > listSize) {
            System.out.print("\nPlease make your selection by typing a number between " + minSelection + " and " + listSize + ": ");
            try {
                selection = scanner.nextInt();
            } catch(InputMismatchException ex) {
                System.out.print("\n\nInvalid selection: " + scanner.next() + ". Please select one of the available items.\n\n");
            }
        }
        return selection;
    }
}
