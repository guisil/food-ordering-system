package exercises.xf.engine;

import exercises.xf.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guisil on 31/01/2017.
 */
public class DataLoader {

    public Map<SupplyType, List<Supply>> loadSupplies(Map<SupplyType, File> supplyFiles) throws IOException {

        Map<SupplyType, List<Supply>> supplies = new HashMap<>();
        for (SupplyType type: supplyFiles.keySet()) {
            supplies.put(type,
                    loadSupplyType(
                            type,
                            getClass().getClassLoader()
                                    .getResourceAsStream(supplyFiles.get(type).getPath())));
        }

        return supplies;
    }

    private List<Supply> loadSupplyType(SupplyType type, InputStream inputStream) throws IOException {

        List<Supply> supplyList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < type.getMandatoryNumberOfFields() ||
                        fields.length > type.getNumberOfFields()) {
                    continue;
                }

                supplyList.add(SupplyFactory.parseNewSupply(type, fields));
            }
        }

        return supplyList;
    }
}
