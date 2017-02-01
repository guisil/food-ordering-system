package exercises.xf;

import exercises.xf.engine.DataLoader;
import exercises.xf.engine.OrderFiller;
import exercises.xf.model.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by guisil on 31/01/2017.
 */
public class App {

    public static void main(String[] args) {

        DataLoader loader = new DataLoader();

        System.out.println("Welcome to the Supply Ordering System!");
        System.out.println("");
        System.out.println("Loading data...");

        Map<SupplyType, List<Supply>> supplies = new HashMap<>();

        try {
            Map<SupplyType, File> supplyFiles = new HashMap<>();
            supplyFiles.put(SupplyType.MAIN_COURSE, new File("exercises/xf/main_courses.txt"));
            supplyFiles.put(SupplyType.DESSERT, new File("exercises/xf/desserts.txt"));
            supplyFiles.put(SupplyType.DRINK, new File("exercises/xf/drinks.txt"));

            supplies = loader.loadSupplies(supplyFiles);

        } catch (IOException e) {
            System.err.println("Error loading data.");
            e.printStackTrace();
            return;
        }

        OrderFiller orderFiller = new OrderFiller(supplies);
        Optional<Order> order = orderFiller.getOrderFromInput(System.in);


        order.ifPresent(o -> {
            System.out.println("Thank you for your visit. Your order:");
            o.getItems().forEach(System.out::println);
            System.out.println("Total price: " + o.getItems().stream().mapToDouble(PricedItem::getPrice).sum());
            System.out.print("\n\nSmacznego!\n\n");
        });

        if (!order.isPresent()) {
            System.out.println("Nothing to your liking? Try again tomorrow!");
        }
    }
}
