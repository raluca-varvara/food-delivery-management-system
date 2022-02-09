package presentation;

import dataLayer.User;
import dataLayer.UserSerializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RegisterPage {
    private final JFrame frame = new JFrame("Register");
    private final JPanel content = new JPanel();

    private final JLabel lUsername = new JLabel("USERNAME");
    private final JLabel lPassword = new JLabel("PASSWORD");
    private final JTextField tUsername = new JTextField();
    private final JTextField tPassword = new JTextField();
    private final JButton register = new JButton("Register");
    private final JComboBox comboBox;

    public RegisterPage() {
        content.setBackground(new Color(255,200,200));
        frame.setBounds(0, 0, 600, 600);
        content.setBounds(0, 0, 600, 600);
        content.setLayout(null);

        String[] cb = {"client", "administrator", "employee"};
        comboBox = new JComboBox(cb);

        lUsername.setBounds(50,0,500,50);
        lUsername.setForeground(new Color(102,0,51));
        tUsername.setBounds(50,50,500,50);
        lPassword.setBounds(50,100,500,50);
        lPassword.setForeground(new Color(102,0,51));
        tPassword.setBounds(50,150,500,50);

        comboBox.setBounds(50,250,200,50);
        comboBox.setBackground(new Color(102,0,51));
        comboBox.setForeground(new Color(255,200,200));

        register.setBounds(350,250,200,50);
        register.setBackground(new Color(102,0,51));
        register.setForeground(new Color(255,200,200));
        register.addActionListener(new RegisterActionListener());

        content.add(lUsername);
        content.add(tUsername);
        content.add(lPassword);
        content.add(tPassword);
        content.add(register);
        content.add(comboBox);


        frame.setContentPane(content);
        frame.setVisible(true);
    }
    void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    class RegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User u = new User(tUsername.getText(),tPassword.getText());
            int selectedIndex = comboBox.getSelectedIndex();
            int ok=1;
            UserSerializator userSerializator;
            if(selectedIndex==0){
                userSerializator = new UserSerializator("client.ser");
            }
            else if(selectedIndex==1){
                userSerializator = new UserSerializator("admin.ser");
            }
            else {
                userSerializator = new UserSerializator("employee.ser");
            }
            List<User> l = userSerializator.deserialize();
            for(User user:l){
                if(u.getUsername().contentEquals(user.getUsername())){
                    ok=0;
                    showMessage("Acest user deja exista");
                }
            }
            if(ok==1){
                u.setIdUser(l.size());
                l.add(u);
                userSerializator.serialize(l);
            }
        }
    }
}
