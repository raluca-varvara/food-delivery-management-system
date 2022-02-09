package presentation;

import businessLayer.DeliveryService;
import dataLayer.OrderSerializator;
import dataLayer.Serializator;
import dataLayer.User;
import dataLayer.UserSerializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LogInPage {
    private final JFrame frame = new JFrame("Log in");
    private final JPanel content = new JPanel();

    private final JLabel lUsername = new JLabel("USERNAME");
    private final JLabel lPassword = new JLabel("PASSWORD");
    private final JTextField tUsername = new JTextField();
    private final JTextField tPassword = new JTextField();
    private final JButton logIn = new JButton("LOG IN");

    public LogInPage() {
        content.setBackground(new Color(255,200,200));
        frame.setBounds(0, 0, 600, 600);
        content.setBounds(0, 0, 600, 600);
        content.setLayout(null);

        lUsername.setBounds(50,0,500,50);
        lUsername.setForeground(new Color(102,0,51));
        tUsername.setBounds(50,50,500,50);
        lPassword.setBounds(50,100,500,50);
        lPassword.setForeground(new Color(102,0,51));
        tPassword.setBounds(50,150,500,50);

        logIn.setBounds(50,250,150,50);
        logIn.setBackground(new Color(102,0,51));
        logIn.setForeground(new Color(255,200,200));
        logIn.addActionListener(new LogInActionListener());

        content.add(lUsername);
        content.add(tUsername);
        content.add(lPassword);
        content.add(tPassword);
        content.add(logIn);


        frame.setContentPane(content);
        frame.setVisible(true);
    }
    void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    class LogInActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Serializator s = new Serializator();
            OrderSerializator ord = new OrderSerializator();
            UserSerializator userSerializator;
            int ok = 0;
            userSerializator = new UserSerializator("client.ser");
            java.util.List<User> l = userSerializator.deserialize();
            DeliveryService d = new DeliveryService();
            d.getH().addAll(s.deserialize());
            d.getOrders().putAll(ord.deserialize());
            for(User user:l){
                if(user.getUsername().contentEquals(tUsername.getText())&&user.getPassword().contentEquals(tPassword.getText())){
                    ok = 1;
                    ClientPage c = new ClientPage(user.getIdUser(),d);
                }
            }
            userSerializator = new UserSerializator("admin.ser");
            l = userSerializator.deserialize();
            for(User user:l){
                if(user.getUsername().contentEquals(tUsername.getText())&&user.getPassword().contentEquals(tPassword.getText())){
                    AdminPage c = new AdminPage(d);
                    ok=1;
                }
            }
            userSerializator = new UserSerializator("employee.ser");
            l = userSerializator.deserialize();
            for(User user:l){
                if(user.getUsername().contentEquals(tUsername.getText())&&user.getPassword().contentEquals(tPassword.getText())){
                    EmployeePage c = new EmployeePage();
                    ok = 1;
                }
            }
            if(ok==0)
                showMessage("Nu exista acest utilizator, va rog sa va inregistrati inainte");
        }
    }
}
