package dataLayer;

import businessLayer.MenuItem;

import java.io.*;
import java.util.HashSet;

public class Serializator {
    private final String FILENAME = "menuitems.ser";

    public void serialize(HashSet<MenuItem> items) {
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
    public HashSet<MenuItem> deserialize() {
        HashSet<MenuItem> list = new HashSet<MenuItem>();
        try {
            FileInputStream file = new FileInputStream(FILENAME);
            if (file.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(file);
                list = (HashSet<MenuItem>) in.readObject();
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
