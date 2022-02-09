package presentation;

import businessLayer.BaseProduct;
import businessLayer.DeliveryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminPage {
    private final JFrame frame = new JFrame("Restaurant");
    private final JPanel content = new JPanel();

    private final JButton importProd = new JButton("IMPORT PRODUCTS");
    private final JButton add = new JButton("ADD");
    private final JButton delete = new JButton("DELETE");
    private final JButton modify = new JButton("MODIFY");
    private final JButton dailyMenu = new JButton("CREATE DAILY MENU");
    private final JButton reports = new JButton("REPORTS");

    private final JLabel lName = new JLabel("Name");
    private final JLabel lRating = new JLabel("Rating");
    private final JLabel lCalories = new JLabel("Calories");
    private final JLabel lProtein = new JLabel("Protein");
    private final JLabel lFat = new JLabel("Fat");
    private final JLabel lSodium = new JLabel("Sodium");
    private final JLabel lPrice = new JLabel("Price");

    private final JTextField tName = new JTextField();
    private final JTextField tRating = new JTextField();
    private final JTextField tCalories = new JTextField();
    private final JTextField tProtein = new JTextField();
    private final JTextField tFat = new JTextField();
    private final JTextField tSodium = new JTextField();
    private final JTextField tPrice = new JTextField();

    private DeliveryService d;

    public AdminPage(DeliveryService d) {

        this.d = d;

        content.setBackground(new Color(255,200,200));
        frame.setBounds(0, 0, 600, 800);
        content.setBounds(0, 0, 600, 800);
        content.setLayout(null);

        importProd.setBounds(50,50,500,50);
        importProd.setBackground(new Color(102,0,51));
        importProd.setForeground(new Color(255,200,200));

        dailyMenu.setBounds(50,550,500,50);
        dailyMenu.setBackground(new Color(102,0,51));
        dailyMenu.setForeground(new Color(255,200,200));
        reports.setBounds(50,650,500,50);
        reports.setBackground(new Color(102,0,51));
        reports.setForeground(new Color(255,200,200));

        lName.setBounds(50,100,500,50);
        lName.setForeground(new Color(102,0,51));
        tName.setBounds(50,150,500,50);

        lRating.setBounds(50,200,150,50);
        lCalories.setBounds(225,200,150,50);
        lProtein.setBounds(400,200,150,50);
        lRating.setForeground(new Color(102,0,51));
        lCalories.setForeground(new Color(102,0,51));
        lProtein.setForeground(new Color(102,0,51));
        tRating.setBounds(50,250,150,50);
        tCalories.setBounds(225,250,150,50);
        tProtein.setBounds(400,250,150,50);

        lFat.setBounds(50,300,150,50);
        lSodium.setBounds(225,300,150,50);
        lPrice.setBounds(400,300,150,50);
        lFat.setForeground(new Color(102,0,51));
        lSodium.setForeground(new Color(102,0,51));
        lPrice.setForeground(new Color(102,0,51));
        tFat.setBounds(50,350,150,50);
        tSodium.setBounds(225,350,150,50);
        tPrice.setBounds(400,350,150,50);

        add.setBounds(50,450,150,50);
        add.setBackground(new Color(102,0,51));
        add.setForeground(new Color(255,200,200));
        delete.setBounds(225,450,150,50);
        delete.setBackground(new Color(102,0,51));
        delete.setForeground(new Color(255,200,200));
        modify.setBounds(400,450,150,50);
        modify.setBackground(new Color(102,0,51));
        modify.setForeground(new Color(255,200,200));

        importProd.addActionListener(new ImportActionListener());
        add.addActionListener(new AddActionListener());
        modify.addActionListener(new ModifyActionListener());
        delete.addActionListener(new DeleteActionListener());
        reports.addActionListener(new ReportActionListener());
        dailyMenu.addActionListener(new DailyActionListener());

        content.add(importProd);
        content.add(dailyMenu);
        content.add(lName);
        content.add(tName);
        content.add(lCalories);
        content.add(lProtein);
        content.add(lRating);
        content.add(lFat);
        content.add(lSodium);
        content.add(lPrice);
        content.add(tCalories);
        content.add(tProtein);
        content.add(tRating);
        content.add(tFat);
        content.add(tSodium);
        content.add(tPrice);
        content.add(add);
        content.add(delete);
        content.add(modify);
        content.add(reports);

        frame.setContentPane(content);
        frame.setVisible(true);
    }
    class ImportActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            d.importProducts();
        }
    }
    class ReportActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ReportsPage p = new ReportsPage(d);
        }
    }
    class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BaseProduct baseProduct = new BaseProduct(tName.getText(),Double.parseDouble(tRating.getText()),Double.parseDouble(tCalories.getText()),Double.parseDouble(tProtein.getText()),Double.parseDouble(tFat.getText()),Double.parseDouble(tSodium.getText()),Double.parseDouble(tPrice.getText()));
            d.addProduct(baseProduct);
        }
    }
    class ModifyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BaseProduct baseProduct = new BaseProduct(tName.getText(),Double.parseDouble(tRating.getText()),Double.parseDouble(tCalories.getText()),Double.parseDouble(tProtein.getText()),Double.parseDouble(tFat.getText()),Double.parseDouble(tSodium.getText()),Double.parseDouble(tPrice.getText()));
            d.modifyProduct(baseProduct);
        }
    }
    class DeleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            d.deleteProduct(tName.getText());
        }
    }
    class DailyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DailyMenuPage p = new DailyMenuPage(d);
        }
    }
}
