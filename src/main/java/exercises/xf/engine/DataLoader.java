package exercises.xf.engine;

import exercises.xf.model.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class responsible for loading the supplies from the provided files.
 *
 * Created by guisil on 31/01/2017.
 */
public class DataLoader {

    /**
     * Loads the menu from the provided files containing the separate items.
     * @param supplyFiles map containing the files from which the items should be loaded
     * @return loaded menu
     * @throws IOException if some problem occurs when reading the files
     */
    public Menu loadMenu(Map<SupplyType, File> supplyFiles) throws IOException {

        Map<SupplyType, List<Supply>> supplies = new HashMap<>();
        for (SupplyType type: supplyFiles.keySet()) {
            supplies.put(type,
                    loadSupplyType(
                            type,
                            getClass().getClassLoader()
                                    .getResourceAsStream(supplyFiles.get(type).getPath())));
        }

        List<Cuisine> cuisines = supplies.values().stream()
                .flatMap(list -> list.stream()
                        .filter(supply -> supply.getCuisine().isPresent())
                        .map(supply -> supply.getCuisine().get()).distinct())
                .distinct().sorted(Comparator.comparing(Cuisine::getName))
                .collect(Collectors.toList());

        return new Menu(cuisines, supplies);
    }


    private List<Supply> loadSupplyType(SupplyType type, InputStream inputStream) throws IOException {

        List<Supply> supplyList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < type.getNumberOfMandatoryFields() ||
                        fields.length > type.getNumberOfFields()) {
                    continue;
                }

                supplyList.add(SupplyFactory.parseNewSupply(type, fields));
            }
        }

        return supplyList
                .stream()
                .sorted(SupplyUtil.supplyComparator)
                .collect(Collectors.toList());
    }
}
