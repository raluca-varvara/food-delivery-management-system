import businessLayer.BaseProduct;
import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import businessLayer.Order;
import dataLayer.OrderSerializator;
import dataLayer.User;
import dataLayer.UserSerializator;
import presentation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        DeliveryService d = new DeliveryService();
        //d.importProducts();

        /*Order o1 = new Order(1,1,LocalDateTime.now());
        Order o2 = new Order(2,2,LocalDateTime.now());

        MenuItem m = new BaseProduct("Fresh Corn Tortillas ",3.75,23,1,2,61,79);
        List<MenuItem > l = new ArrayList<MenuItem>();
        l.add(m);
        d.getOrders().put(o1, l);
        d.getOrders().put(o2, l);

        d.loyalClientsReport(1,1);
        d.timeIntervalReport(8,12);*/

        //AdminPage a = new AdminPage(d);
        //ClientPage c = new ClientPage(1,d);
        /*User u = new User("raluca","parola");
        User u1 = new User("raluca","parola");

        List<User> list = new ArrayList<>();
        list.add(u);
        list.add(u1);
        UserSerializator serializator = new UserSerializator("client.ser");
        serializator.serialize(list);
        List<User> deserialized = serializator.deserialize();
        System.out.println(deserialized);*/
        FrontPage f = new FrontPage();


    }
}
