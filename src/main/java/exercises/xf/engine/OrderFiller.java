package exercises.xf.engine;

import exercises.xf.model.*;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by guisil on 31/01/2017.
 */
public class OrderFiller {

    private final Menu menu;


    public OrderFiller(Menu menu) {
        this.menu = menu;
    }


    public Optional<Order> getOrderFromInput(InputStream inputStream) {

        List<PricedItem> choices = new ArrayList<>();
        Cuisine currentCuisine = new Cuisine("None");

        boolean cliActive = true;

        try (Scanner scanner = new Scanner(inputStream).useDelimiter("\n")) {
            while(cliActive) {

                Optional<Cuisine> newCuisine = getCuisineFromInput(scanner);
                if (newCuisine.isPresent()) {
                    currentCuisine = newCuisine.get();
                }

                getMealFromInput(scanner, currentCuisine).ifPresent(choices::add);
                getDrinkFromInput(scanner, currentCuisine).ifPresent(choices::add);

                String answer;
                do {
                    System.out.println("Would you like to order something else? (y/n)");
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

        System.out.println("AVAILABLE CUISINES AND STUFF...");
        IntStream.range(0, cuisines.size()).forEach(i -> System.out.println((i + 1) + " - " + cuisines.get(i)));

        int selection = getValidSelection(scanner, cuisines.size());
        if (selection > 0 && selection <= cuisines.size()) {
            return Optional.of(cuisines.get(selection - 1));
        } else {
            return Optional.empty();
        }
    }

    private Optional<PricedItem> getMealFromInput(Scanner scanner, Cuisine selectedCuisine) {
        Optional<Supply> chosenMainCourse = getSupplyFromInput(scanner, menu.getSupplies().get(SupplyType.MAIN_COURSE), selectedCuisine);
        Optional<Supply> chosenDessert = getSupplyFromInput(scanner, menu.getSupplies().get(SupplyType.DESSERT), selectedCuisine);
        try {
            return Optional.of(new Meal(chosenMainCourse.orElse(null), chosenDessert.orElse(null)));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }

    private Optional<Supply> getDrinkFromInput(Scanner scanner, Cuisine selectedCuisine) {
        return getSupplyFromInput(scanner, menu.getSupplies().get(SupplyType.DRINK), selectedCuisine);
    }

    private Optional<Supply> getSupplyFromInput(Scanner scanner, List<Supply> orderItemList, Cuisine selectedCuisine) {

        System.out.println("0 - None");
        List<Supply> cuisineSupply = orderItemList.stream()
                .filter(supply -> !supply.getCuisine().isPresent() || supply.getCuisine().get().equals(selectedCuisine))
                .sorted(SupplyUtil.supplyComparator)
                .collect(Collectors.toList());
        IntStream.range(0, cuisineSupply.size()).forEach(i -> System.out.println((i + 1) + " - " + cuisineSupply.get(i)));

        int selection = getValidSelection(scanner, cuisineSupply.size());
        if (selection > 0 && selection <= cuisineSupply.size()) {
            return Optional.of(cuisineSupply.get(selection - 1));
        } else {
            return Optional.empty();
        }
    }

    private int getValidSelection(Scanner scanner, int listSize) {
        int selection = -1;
        while(selection < 0 || selection > listSize) {
            System.out.println("Please type a number between 0 and " + listSize);
            try {
                selection = scanner.nextInt();
            } catch(InputMismatchException ex) {
                System.out.println("Illegal selection: " + scanner.next() + ". Please select one of the available items.");
            }
        }
        return selection;
    }
}
