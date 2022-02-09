package dataLayer;

import businessLayer.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserSerializator {

    private final String FILENAME;
    private final static long serialVersionUID = 911136765023976231l;

    public UserSerializator(String filename) {
        FILENAME = filename;
    }

    public void serialize(List<User> items) {
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
    public List<User> deserialize() {
        List<User> list = new ArrayList<User>();
        try {
            FileInputStream file = new FileInputStream(FILENAME);
            if (file.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(file);
                list = (List<User>) in.readObject();
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
