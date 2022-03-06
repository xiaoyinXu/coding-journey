package com.xxywebsite.swing;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPwd;
    private JButton btnLogin;
    private JButton btnCancel;


    public LoginFrame() {
       init();
    }

    private void init() {
        this.setSize(800, 600);
        this.setLayout(new GridLayout(2, 1));

        Container container = this.getContentPane();
        JPanel firstLine = new JPanel();
        JPanel secondLine = new JPanel();
        firstLine.setLayout(new GridLayout(1, 1));
        secondLine.setLayout(new GridLayout(1, 1));
        container.add(firstLine);
        container.add(secondLine);


        JTextArea jTextArea = new JTextArea();
        jTextArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        firstLine.add(new JLabel("用户名"));
        firstLine.add(new JTextArea());


        secondLine.add(new JLabel("密码"));
        secondLine.add(new JPasswordField());

        System.out.println(5);

    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}
