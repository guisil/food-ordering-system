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

        System.out.print("\nWelcome to the Food Ordering System!\n\n");
        System.out.print("Loading data...\n\n");

        Menu menu;

        try {
            Map<SupplyType, File> supplyFiles = new HashMap<>();
            supplyFiles.put(SupplyType.MAIN_COURSE, new File("exercises/xf/main_courses.txt"));
            supplyFiles.put(SupplyType.DESSERT, new File("exercises/xf/desserts.txt"));
            supplyFiles.put(SupplyType.DRINK, new File("exercises/xf/drinks.txt"));

            menu = loader.loadMenu(supplyFiles);

        } catch (IOException e) {
            System.err.print("Error loading data.\n\n");
            e.printStackTrace();
            return;
        }

        OrderFiller orderFiller = new OrderFiller(menu);
        Optional<Order> order = orderFiller.getOrderFromInput(System.in);

        order.ifPresent(o -> {
            System.out.print("\n\nThank you for your visit. Your order:\n\n");
            System.out.print(o);
            System.out.print("\n\nTotal price: " + o.getPrice() + " PLN\n");
            System.out.print("\n\nSmacznego!\n\n");
        });

        if (!order.isPresent()) {
            System.out.print("\n\nNothing to your liking? Try again tomorrow!\n\n");
        }
    }
}
