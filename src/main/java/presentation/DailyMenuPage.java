package presentation;

import businessLayer.CompositeProduct;
import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import presentation.tables.ProductTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DailyMenuPage {
    private final JFrame frame = new JFrame("Log in");
    private final JPanel content = new JPanel();

    private final JLabel lDaily = new JLabel("DAILY MENU NAME");
    private final JLabel lPrice = new JLabel("DAILY MENU PRICE");
    private final JLabel lProd = new JLabel("PRODUCT");
    private final JTextField tDaily = new JTextField();
    private final JTextField tProd = new JTextField();
    private final JTextField tPrice = new JTextField();
    private final JButton addProd = new JButton("ADD PRODUCT");
    private final JButton finish = new JButton("FINISH DAILY MENU");
    private final JButton viewProd = new JButton("VIEW PRODUCTS");

    DeliveryService d;
    List<MenuItem> dailyMenu = new ArrayList<>();

    public DailyMenuPage(DeliveryService d) {

        this.d = d;

        content.setBackground(new Color(255,200,200));
        frame.setBounds(0, 0, 600, 600);
        content.setBounds(0, 0, 600, 600);
        content.setLayout(null);

        lDaily.setBounds(50,0,225,50);
        lDaily.setForeground(new Color(102,0,51));
        tDaily.setBounds(50,50,225,50);
        lPrice.setBounds(325,0,225,50);
        lPrice.setForeground(new Color(102,0,51));
        tPrice.setBounds(325,50,225,50);
        lProd.setBounds(50,100,500,50);
        lProd.setForeground(new Color(102,0,51));
        tProd.setBounds(50,150,500,50);

        addProd.setBounds(50,250,500,50);
        addProd.setBackground(new Color(102,0,51));
        addProd.setForeground(new Color(255,200,200));
        finish.setBounds(50,350,500,50);
        finish.setBackground(new Color(102,0,51));
        finish.setForeground(new Color(255,200,200));
        viewProd.setBounds(50,450,500,50);
        viewProd.setBackground(new Color(102,0,51));
        viewProd.setForeground(new Color(255,200,200));

        viewProd.addActionListener(new ViewActionListener());
        addProd.addActionListener(new AddActionListener());
        finish.addActionListener(new FinishActionListener());

        content.add(lDaily);
        content.add(tDaily);
        content.add(lPrice);
        content.add(tPrice);
        content.add(lProd);
        content.add(tProd);
        content.add(addProd);
        content.add(finish);
        content.add(viewProd);


        frame.setContentPane(content);
        frame.setVisible(true);
    }
    void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    class ViewActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<businessLayer.MenuItem> l = new ArrayList<MenuItem>(d.getH());
            ProductTable p = new ProductTable(l);
        }
    }
    class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> o = new ArrayList<>();
            o = d.searchByName(tProd.getText());
            if(o.size()==0){
                showMessage("Nu exista produsul");
            }else if(o.size() != 1){
                showMessage("Specificati tot numele produsului");
            }else if(o.size()==1){
                dailyMenu.add(o.get(0));
            }
        }
    }
    class FinishActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CompositeProduct product = new CompositeProduct(tDaily.getText(),Double.parseDouble(tPrice.getText()),dailyMenu);
            System.out.println(product);
            d.addProduct(product);
            dailyMenu=new ArrayList<>();
        }
    }
}
