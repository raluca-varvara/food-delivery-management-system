package presentation;

import businessLayer.DeliveryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ReportsPage {
    private final JFrame frame = new JFrame("Restaurant");
    private final JPanel content = new JPanel();

    private final JButton timeInterval = new JButton("TIME INTERVAL REPORT");
    private final JButton popularity = new JButton("POPULARITY REPORT");
    private final JButton loyalClients = new JButton("LOYAL CLIENTS REPORT");
    private final JButton todaysProd = new JButton("TODAY'S PRODUCTS REPORT");

    private final JLabel lStartH = new JLabel("Start hour");
    private final JLabel lStopH = new JLabel("Stop hour");
    private final JLabel lNbOfOrders = new JLabel("Nb of orders");
    private final JLabel lNbOfTimes = new JLabel("Nb. of times");
    private final JLabel lValue = new JLabel("Value");
    private final JLabel lDate = new JLabel("Date");

    private final JTextField tStartH = new JTextField();
    private final JTextField tStopH = new JTextField();
    private final JTextField tNbOfOrders = new JTextField();
    private final JTextField tNbOfTimes = new JTextField();
    private final JTextField tValue = new JTextField();
    private final JTextField tDate = new JTextField();

    private final DeliveryService d;

    public ReportsPage(DeliveryService d) {
        this.d = d;
        content.setBackground(new Color(255,200,200));
        frame.setBounds(0, 0, 600, 800);
        content.setBounds(0, 0, 600, 800);
        content.setLayout(null);

        timeInterval.setBounds(50,150,500,50);
        timeInterval.setBackground(new Color(102,0,51));
        timeInterval.setForeground(new Color(255,200,200));
        timeInterval.addActionListener(new TimeActionListener());

        lStartH.setBounds(50,0,225,50);
        lStartH.setForeground(new Color(102,0,51));
        tStartH.setBounds(50,50,225,50);
        lStopH.setBounds(325,0,225,50);
        lStopH.setForeground(new Color(102,0,51));
        tStopH.setBounds(325,50,225,50);

        lNbOfOrders.setBounds(50,200,500,50);
        lNbOfOrders.setForeground(new Color(102,0,51));
        tNbOfOrders.setBounds(50,250,225,50);

        lNbOfTimes.setBounds(50,300,225,50);
        lNbOfTimes.setForeground(new Color(102,0,51));
        tNbOfTimes.setBounds(50,350,225,50);
        lValue.setBounds(325,300,150,50);
        lValue.setForeground(new Color(102,0,51));
        tValue.setBounds(325,350,225,50);

        lDate.setBounds(50,500,500,50);
        lDate.setForeground(new Color(102,0,51));
        tDate.setBounds(50,550,500,50);

        popularity.setBounds(325,250,225,50);
        popularity.setBackground(new Color(102,0,51));
        popularity.setForeground(new Color(255,200,200));
        popularity.addActionListener(new PopularityActionListener());

        loyalClients.setBounds(50,450,500,50);
        loyalClients.setBackground(new Color(102,0,51));
        loyalClients.setForeground(new Color(255,200,200));
        loyalClients.addActionListener(new LoyalClientsActionListener());

        todaysProd.setBounds(50,650,500,50);
        todaysProd.setBackground(new Color(102,0,51));
        todaysProd.setForeground(new Color(255,200,200));
        todaysProd.addActionListener(new DateActionListener());

        content.add(timeInterval);
        content.add(lStartH);
        content.add(tStartH);
        content.add(lNbOfOrders);
        content.add(lNbOfTimes);
        content.add(lStopH);
        content.add(lValue);
        content.add(lDate);
        content.add(tNbOfOrders);
        content.add(tNbOfTimes);
        content.add(tStopH);
        content.add(tValue);
        content.add(tDate);
        content.add(popularity);
        content.add(loyalClients);
        content.add(todaysProd);

        frame.setContentPane(content);
        frame.setVisible(true);
    }
    void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    class TimeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int hStart = Integer.parseInt(tStartH.getText());
                int hStop = Integer.parseInt(tStopH.getText());
                d.timeIntervalReport(hStart,hStop);
            }catch(NumberFormatException err){
                showMessage("orele trebuie sa fie numere intregi int 0 si 24");
            }

        }
    }
    class PopularityActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int nbOfTimes = Integer.parseInt(tNbOfOrders.getText());
                d.popularProductsReport(nbOfTimes);
            }catch(NumberFormatException err){
                showMessage("trebuie sa fie numar intreg");
            }
        }
    }
    class LoyalClientsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int times = Integer.parseInt(tNbOfTimes.getText());
                int value = Integer.parseInt(tValue.getText());
                d.loyalClientsReport(times,value);
            }catch(NumberFormatException err){
                showMessage("trebuie sa fie numere intregi");
            }

        }
    }
    class DateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                LocalDate date = LocalDate.parse(tDate.getText());
                d.todaysProducts(date);
            }catch(NumberFormatException err){
                showMessage("orele trebuie sa fie numere intregi int 0 si 24");
            }

        }
    }
}
