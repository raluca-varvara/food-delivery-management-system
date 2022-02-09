package dataLayer;

import businessLayer.MenuItem;
import businessLayer.Order;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class OrderSerializator {
    private final String FILENAME = "orders.ser";

    public void serialize(Map<Order, List<MenuItem>> items) {
        try {
            FileOutputStream file = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(items);
            out.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public Map<Order, List<MenuItem>> deserialize() {
        Map<Order, List<MenuItem>> list = new HashMap<Order, List<MenuItem>>();
        try {
            FileInputStream file = new FileInputStream(FILENAME);
            if (file.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(file);
                list = (Map<Order, List<MenuItem>>) in.readObject();
                in.close();
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
