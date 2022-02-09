package presentation;

import businessLayer.CompositeProduct;
import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import businessLayer.Order;
import dataLayer.FileWr;
import presentation.tables.ProductTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClientPage {
    private final JFrame frame = new JFrame("Restaurant");
    private final JPanel content = new JPanel();

    private final JButton viewProd = new JButton("VIEW PRODUCTS");
    private final JButton search = new JButton("SEARCH");
    private final JButton addToCart = new JButton("ADD TO CART");
    private final JButton finish = new JButton("FINISH");
    private final JButton dailyMenu = new JButton("SHOW DAILY MENU");
    private final JLabel lProduct = new JLabel("Product");
    private final JTextField tSearch = new JTextField();
    private final JTextField tProduct = new JTextField();
    private final JComboBox comboBox;
    private final JComboBox comboBox1;

    private int idClient;
    DeliveryService d;
    private List<MenuItem> ord = new ArrayList<>();

    public ClientPage(int idClient, DeliveryService d) {
        this.d = d;
        d.addObserver(new EmployeePage());
        this.idClient=idClient;

        content.setBackground(new Color(255,200,200));
        frame.setBounds(0, 0, 600, 700);
        content.setBounds(0, 0, 600, 700);
        content.setLayout(null);

        String[] cb = {"name", "rating", "calories", "protein", "fat","sodium","price"};
        comboBox = new JComboBox(cb);

        int nr = 0;
        for(MenuItem m:d.getH())
            if(m instanceof CompositeProduct)
                nr++;
        String dailyMenus[] = new String[nr];
        nr = 0;
        for(MenuItem m:d.getH())
            if(m instanceof CompositeProduct) {
                dailyMenus[nr]=m.getTitle();
                nr++;
            }
        comboBox1 = new JComboBox(dailyMenus);

        viewProd.setBounds(50,50,500,50);
        viewProd.setBackground(new Color(102,0,51));
        viewProd.setForeground(new Color(255,200,200));

        tSearch.setBounds(50,150,150,50);
        comboBox.setBounds(225,150,150,50);
        comboBox.setForeground(new Color(102,0,51));
        comboBox1.setBounds(50,450,225,50);
        comboBox1.setForeground(new Color(102,0,51));
        search.setBounds(400,150,150,50);
        search.setBackground(new Color(102,0,51));
        search.setForeground(new Color(255,200,200));

        lProduct.setBounds(50,200,250,50);
        lProduct.setForeground(new Color(102,0,51));

        tProduct.setBounds(50,250,200,50);

        addToCart.setBounds(350,250,200,50);
        addToCart.setBackground(new Color(102,0,51));
        addToCart.setForeground(new Color(255,200,200));

        finish.setBounds(50,350,500,50);
        finish.setBackground(new Color(102,0,51));
        finish.setForeground(new Color(255,200,200));

        dailyMenu.setBounds(325,450,225,50);
        dailyMenu.setBackground(new Color(102,0,51));
        dailyMenu.setForeground(new Color(255,200,200));

        viewProd.addActionListener(new ViewActionListener());
        search.addActionListener(new SearchActionListener());
        addToCart.addActionListener(new AddActionListener());
        finish.addActionListener(new FinishActionListener());
        dailyMenu.addActionListener(new DailyMenuActionListener());

        content.add(viewProd);
        content.add(tSearch);
        content.add(comboBox);
        content.add(search);
        content.add(tProduct);
        content.add(addToCart);
        content.add(lProduct);
        content.add(finish);
        content.add(comboBox1);
        content.add(dailyMenu);

        frame.setContentPane(content);
        frame.setVisible(true);
    }
    void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    class ViewActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> l = new ArrayList<MenuItem>(d.getH());
            ProductTable p = new ProductTable(l);
        }
    }
    class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = comboBox.getSelectedIndex();
            List<MenuItem> list = new ArrayList<>();
            if(selectedIndex==0){
                list = d.searchByName(tSearch.getText());
                ProductTable p = new ProductTable(list);
            }
            else if(selectedIndex==1){
                list = d.searchByRating(Double.parseDouble(tSearch.getText()));
                ProductTable p = new ProductTable(list);
            }
            else if(selectedIndex==2){
                list = d.searchByCalories(Double.parseDouble(tSearch.getText()));
                ProductTable p = new ProductTable(list);
            }
            else if(selectedIndex==3){
                list = d.searchByProtein(Double.parseDouble(tSearch.getText()));
                ProductTable p = new ProductTable(list);
            }
            else if(selectedIndex==4){
                list = d.searchByFat(Double.parseDouble(tSearch.getText()));
                ProductTable p = new ProductTable(list);
            }
            else if(selectedIndex==5){
                list = d.searchBySodium(Double.parseDouble(tSearch.getText()));
                ProductTable p = new ProductTable(list);
            }
            else if(selectedIndex==6){
                list = d.searchByPrice(Double.parseDouble(tSearch.getText()));
                ProductTable p = new ProductTable(list);
            }
        }
    }
    class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> o = new ArrayList<>();
            o = d.searchByName(tProduct.getText());
            if(o.size()==0){
                showMessage("Nu exista produsul");
            }else if(o.size() != 1){
                showMessage("Specificati tot numele produsului");
            }else if(o.size()==1){
                ord.add(o.get(0));
            }
        }
    }
    class FinishActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double totalPr = 0;
            for(MenuItem l:ord)
                totalPr+=l.getPrice();
            Order newOrd= new Order(idClient,d.getOrders().size(), LocalDateTime.now(),totalPr);
            d.makeOrder(newOrd ,ord);
            try {
                FileWr w = new FileWr("bill.txt");
                w.getFr().write(newOrd.toString());
                w.getFr().write(" \n");
                w.getFr().write(ord.toString());
                w.getFr().write(" \n total");
                w.getFr().write(String.valueOf(totalPr));
                w.getFr().close();
                ord =new ArrayList<>();
            } catch (IOException er) {
                er.printStackTrace();
            }
        }
    }
    class DailyMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           String name = (String)comboBox1.getSelectedItem();
           List<MenuItem> list = new ArrayList<>();
           list=d.searchByName(name);
           CompositeProduct c = (CompositeProduct) list.get(0);
           ProductTable p = new ProductTable(((CompositeProduct) list.get(0)).getProducts());
        }
    }
}
