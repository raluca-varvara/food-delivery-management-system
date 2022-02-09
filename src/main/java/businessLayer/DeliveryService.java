package businessLayer;

import dataLayer.FileWr;
import dataLayer.OrderSerializator;
import dataLayer.Serializator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * -tag tagname:ot:"aceasta este clasa care se ocupa de procesarea serviciilor"
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    private HashSet<MenuItem> h = new HashSet<MenuItem>();
    private Map<Order, List<MenuItem>> orders = new HashMap<Order, List<MenuItem>>();
    private Serializator serializator = new Serializator();
    public Set<MenuItem> getH() {
        return h;
    }
    public Map<Order, List<MenuItem>> getOrders() {
        return orders;
    }

    /**
     * -tag tagname:om:"aceasta este o metoda pentru importarea produselor din fis csv"
     */
    @Override
    public void importProducts()  {
        try {
            Stream<String> rows = Files.lines(Paths.get("products.csv"));
            Set<MenuItem> h1 = rows.skip(1)
                    .map(x->new BaseProduct(x.split(",")))
                    .collect(Collectors.toSet());
            System.out.println(h1);
            h.addAll(h1);
            assert isWellFormed() : "The invariants are not preserved";
            serializator.serialize(h);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * -tag tagname:om:"aceasta este o metoda modificarea unui produs"
     * @param newM not null
     */
    @Override
    public void modifyProduct(MenuItem newM) {
        assert newM != null: "Product is null";
        assert newM.getPrice() > 0 : "Price is negative";
        h.removeIf(l -> l.getTitle().contentEquals(newM.getTitle()));
        h.add(newM);
        assert isWellFormed() : "The invariants are not preserved";
        serializator.serialize(h);
    }
    /**
     * -tag tagname:om:"aceasta este o metoda adaugarea unui produs"
     * @param newM not null
     */
    @Override
    public void addProduct(MenuItem newM) {
        assert newM != null: "Product is null";
        assert newM.getPrice() > 0 : "Price is negative";
        if(newM instanceof CompositeProduct)
            assert ((CompositeProduct) newM).getProducts() != null : "lista e negativa";
        for (MenuItem menuItem : this.h) {
            assert !menuItem.getTitle().contentEquals(newM.getTitle()) : "Item " + newM.getTitle() + " already in the menu";
        }
        h.add(newM);
        assert isWellFormed() : "The invariants are not preserved";
        serializator.serialize(h);
    }

    /**
     * -tag tagname:om:"aceasta este o metoda stergerea unui produs"
     * @param title not null
     */
    @Override
    public void deleteProduct(String title) {
        assert title!= null : "Title is null";
        h.removeIf(l -> l.getTitle().contentEquals(title));
        assert isWellFormed() : "The invariants are not preserved";
        serializator.serialize(h);
    }

    @Override
    public void timeIntervalReport(int hStart, int hStop) {
        Set<Order> keySet = orders.keySet();
        Stream<Order> stream = keySet.stream();
        Set<Order> toWr = stream.map(order->order)
                .filter(hour->(hour.getOrderDate().getHour()>=hStart&&hour.getOrderDate().getHour()<=hStop))
                .collect(Collectors.toSet());
        try {
            FileWr w = new FileWr("timeIntervalReport.txt");
            w.getFr().write(toWr.toString());
            w.getFr().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void popularProductsReport(int nbOfTimes) {
        Set<Map.Entry<Order, List<MenuItem>>> entries = orders.entrySet();
        List<MenuItem> menuList = new ArrayList<>();
        List<List<MenuItem>> l = entries.stream()
                .map(order -> order.getValue())
                .collect(Collectors.toList());
        for(List<MenuItem> itemList:l){
            menuList.addAll(itemList);
        }
        Set<MenuItem> popItem = menuList.stream()
                .filter(values -> (entries.stream().filter(val -> (val.getValue().contains(values))).count()==nbOfTimes))
                .collect(Collectors.toSet());
        try {
            FileWr w = new FileWr("popularProducts.txt");
            w.getFr().write(popItem.toString());
            w.getFr().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loyalClientsReport(int nbOfTimes, int value) {
        System.out.println(orders);
        Set<Map.Entry<Order, List<MenuItem>>> entries = orders.entrySet();
        Stream<Map.Entry<Order, List<MenuItem>>> stream = entries.stream();
        List<Integer> aux = stream.map(order->order)
                .filter(values -> ((values.getKey().getTotalPrice()>=value)))
                .map(order -> order.getKey())
                .map(client->client.getClientId())
                .collect(Collectors.toList());
        //System.out.println(aux);
        List<Integer> toWr =aux.stream().filter(frq ->(Collections.frequency(aux,frq) >= nbOfTimes)).distinct()
                    .collect(Collectors.toList());
        try {
            FileWr w = new FileWr("loyalClients.txt");
            w.getFr().write("loyal clients are :");
            w.getFr().write(toWr.toString());
            w.getFr().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void todaysProducts(LocalDate date) {
        Set<Map.Entry<Order, List<MenuItem>>> entries = orders.entrySet();
        List<MenuItem> list = new ArrayList<>();
        List<List<MenuItem>> l = entries.stream()
                .filter(day -> (day.getKey().getOrderDate().getDayOfMonth()==date.getDayOfMonth()&&day.getKey().getOrderDate().getMonth()==date.getMonth()))
                .map(order -> order.getValue())
                .collect(Collectors.toList());
        for(List<MenuItem> itemList:l){
            list.addAll(itemList);
        }
        list = list.stream().distinct().collect(Collectors.toList());
        try {
            FileWr w = new FileWr("datesProducts.txt");
            w.getFr().write("products ordered on ");
            w.getFr().write(date.toString());
            w.getFr().write(" are: ");
            w.getFr().write(list.toString());
            w.getFr().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MenuItem> searchByName(String seq) {
        List<MenuItem> l = h.stream().filter(name ->(name.getTitle().toLowerCase(Locale.ROOT).contains(seq.toLowerCase(Locale.ROOT)))).collect(Collectors.toList());
        return l;
    }

    @Override
    public List<MenuItem> searchByRating(double rating) {
        List<MenuItem> l = h.stream().filter(name ->(name.getRating() == rating)).collect(Collectors.toList());
        return l;
    }

    @Override
    public List<MenuItem> searchByCalories(double calories) {
        List<MenuItem> l = h.stream().filter(name ->(name.getCalories() == calories)).collect(Collectors.toList());
        return l;
    }

    @Override
    public List<MenuItem> searchByProtein(double protein) {
        List<MenuItem> l = h.stream().filter(name ->(name.getProtein() == protein)).collect(Collectors.toList());
        return l;
    }

    @Override
    public List<MenuItem> searchByFat(double fat) {
        List<MenuItem> l = h.stream().filter(name ->(name.getFat() == fat)).collect(Collectors.toList());
        return l;
    }

    @Override
    public List<MenuItem> searchBySodium(double sodium) {
        List<MenuItem> l = h.stream().filter(name ->(name.getSodium() == sodium)).collect(Collectors.toList());
        return l;
    }

    @Override
    public List<MenuItem> searchByPrice(double price) {
        List<MenuItem> l = h.stream().filter(name ->(name.getPrice() == price)).collect(Collectors.toList());
        return l;
    }
    /**
     * -tag tagname:om:"aceasta este o metoda crearea unei comenzi"
     * @param newOrd not null
     * @param ord not null
     */
    public void makeOrder(Order newOrd, List<MenuItem> ord){
        assert newOrd != null: "the pre condition is not preserved";
        assert ord != null: "the pre condition is not preserved";
        assert newOrd.getOrderId()>0 : "The pre are not preserved";
        OrderSerializator o = new OrderSerializator();
        orders = o.deserialize();
        orders.put(newOrd,ord);
        System.out.println(ord);
        System.out.println(orders);
        assert isWellFormed() : "The invariants are not preserved";
        o.serialize(orders);
        this.setChanged();
        notifyObservers(ord);
    }

    /**
     * @invariant
     * @return if it is well formed
     */
    protected boolean isWellFormed() {
        ArrayList<Integer> tables = new ArrayList<Integer>();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Order order : this.orders.keySet()) {
            if (!ids.contains(order.getOrderId())) {
                ids.add(order.getOrderId());
            } else {
                return false;
            }
        }
        for (MenuItem menuItem : this.h) {
            if (menuItem instanceof CompositeProduct) {
                CompositeProduct compositeProduct = (CompositeProduct) menuItem;
                for (MenuItem item : compositeProduct.getProducts()) {
                    if (!this.h.contains(item)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
