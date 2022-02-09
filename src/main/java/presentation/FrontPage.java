package presentation;

import presentation.LogInPage;
import presentation.RegisterPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontPage {
    private final JFrame frame = new JFrame("Restaurant");
    private final JPanel content = new JPanel();

    private final JLabel title = new JLabel("Restaurant",SwingConstants.CENTER);;
    private final JButton login = new JButton("LOG IN");
    private final JButton register = new JButton("REGISTER");

    public FrontPage() {
        content.setBackground(new Color(255,200,200));
        frame.setBounds(0, 0, 600, 600);
        content.setBounds(0, 0, 600, 600);
        content.setLayout(null);

        title.setBounds(100, 50, 400, 50);
        title.setFont(new Font("Serif",Font.BOLD, 35));
        title.setForeground(new Color(102,0,51));

        login.setBounds(100, 150, 400, 50);
        login.setBackground(new Color(102,0,51));
        login.setForeground(new Color(255,200,200));
        register.setBounds(100, 250, 400, 50);
        register.setBackground(new Color(102,0,51));
        register.setForeground(new Color(255,200,200));

        login.addActionListener(new LogInActionListener());
        register.addActionListener(new RegisterActionListener());

        content.add(title);
        content.add(login);
        content.add(register);

        frame.setContentPane(content);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class LogInActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LogInPage l = new LogInPage();
        }
    }
    class RegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            RegisterPage l = new RegisterPage();
        }
    }
}
