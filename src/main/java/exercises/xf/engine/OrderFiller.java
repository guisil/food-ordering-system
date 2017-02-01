package exercises.xf.engine;

import exercises.xf.model.*;

import java.io.InputStream;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by guisil on 31/01/2017.
 */
public class OrderFiller {

    private final Map<SupplyType, List<Supply>> supplies;

    public OrderFiller(Map<SupplyType, List<Supply>> supplies) {
        this.supplies = supplies;
    }

    public Optional<Order> getOrderFromInput(InputStream inputStream) {

        List<PricedItem> choices = new ArrayList<>();

        boolean cliActive = true;

        try (Scanner scanner = new Scanner(inputStream).useDelimiter("\n")) {
            while(cliActive) {
                getMealFromInput(scanner).ifPresent(choices::add);
                getDrinkFromInput(scanner).ifPresent(choices::add);

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


    private Optional<PricedItem> getMealFromInput(Scanner scanner) {
        Optional<Supply> chosenMainCourse = getSupplyFromInput(scanner, supplies.get(SupplyType.MAIN_COURSE));
        Optional<Supply> chosenDessert = getSupplyFromInput(scanner, supplies.get(SupplyType.DESSERT));
        try {
            return Optional.of(new Meal(chosenMainCourse.orElse(null), chosenDessert.orElse(null)));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }

    private Optional<Supply> getDrinkFromInput(Scanner scanner) {
        return getSupplyFromInput(scanner, supplies.get(SupplyType.DRINK));
    }

    private Optional<Supply> getSupplyFromInput(Scanner scanner, List<Supply> orderItemList) {

        System.out.println("0 - None");
        IntStream.range(0, orderItemList.size()).forEach(i -> System.out.println((i + 1) + " - " + orderItemList.get(i)));
        int selection = -1;
        while(selection < 0 || selection > orderItemList.size()) {
            System.out.println("Please type a number between 0 and " + orderItemList.size());
            try {
                selection = scanner.nextInt();
            } catch(InputMismatchException ex) {
                System.out.println("Illegal selection: " + scanner.next() + ". Please select one of the available items.");
            }
        }
        if (selection > 0 && selection <= orderItemList.size()) {
            return Optional.of(orderItemList.get(selection - 1));
        } else {
            return Optional.empty();
        }
    }
}
