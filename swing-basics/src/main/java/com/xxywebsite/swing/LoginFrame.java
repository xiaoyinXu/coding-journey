package com.xxywebsite.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends JFrame {
    private JLabel titleLabel;
    private JLabel leftImgLabel;

    private JPanel contentPanel;
    private JLabel usernameLabel;
    private JTextField usernameTxt;
    private JLabel pwdLabel;
    private JPasswordField pwdTxt;
    private JButton loginButton;
    private JButton exitButton;

    public LoginFrame() {
       init();
    }

    private void init() {
        // initSelf()
        initSelf();

        // initial every component
        initComponents();

        // layout
        addLayout();
    }

    private void initSelf() {
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(37, 51, 61));
        this.setUndecorated(true);
    }

    /**
     * 首先上左中 用borderLayout
     * 然后 中用SpringLayout
     */
    private void addLayout() {
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(titleLabel, BorderLayout.NORTH);
        container.add(leftImgLabel, BorderLayout.WEST);

        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(37, 51, 61));
        SpringLayout springLayout = new SpringLayout();
        contentPanel.setLayout(springLayout);
        container.add(contentPanel, BorderLayout.CENTER);

        // 弹簧布局
        contentPanel.add(usernameLabel);
        contentPanel.add(usernameTxt);
        contentPanel.add(pwdLabel);
        contentPanel.add(pwdTxt);
        contentPanel.add(loginButton);
        contentPanel.add(exitButton);

        // 用户名标签
//        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 20, SpringLayout.NORTH, contentPanel);

        // 用户名输入框
        springLayout.putConstraint(SpringLayout.WEST, usernameTxt, 20, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameTxt, 0, SpringLayout.NORTH, usernameLabel);

        // 密码标签
        springLayout.putConstraint(SpringLayout.NORTH, pwdLabel, 20, SpringLayout.SOUTH, usernameLabel);
        springLayout.putConstraint(SpringLayout.EAST, pwdLabel, 0, SpringLayout.EAST, usernameLabel);

        // 密码输入框
        springLayout.putConstraint(SpringLayout.WEST, pwdTxt, 20, SpringLayout.EAST, pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pwdTxt, 0, SpringLayout.NORTH,pwdLabel);


        // 登录按钮
        springLayout.putConstraint(SpringLayout.WEST, loginButton, 0, SpringLayout.WEST, pwdTxt);
        springLayout.putConstraint(SpringLayout.NORTH, loginButton, 20, SpringLayout.SOUTH, pwdLabel);


        // 退出按钮
        springLayout.putConstraint(SpringLayout.NORTH, exitButton, 0, SpringLayout.NORTH, loginButton);
        springLayout.putConstraint(SpringLayout.WEST, exitButton, 20, SpringLayout.EAST, loginButton);

        // 水平居中
        int offsetX = Spring.sum(Spring.sum(Spring.width(usernameLabel), Spring.width(usernameTxt)), Spring.constant(20)).getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, usernameLabel, -offsetX, SpringLayout.HORIZONTAL_CENTER, contentPanel);

        // 垂直居中
//        int offsetY = Spring.sum(Spring.scale(Spring.sum(Spring.constant(this.getHeight()), Spring.minus(Spring.height(titleLabel))), 0.5f), Spring.minus(Spring.constant(20))).getValue();
//        int offsetY = Spring.constant((container.getHeight() - titleLabel.getHeight()) / 2 - 20).getValue();
//        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, -offsetY, SpringLayout.VERTICAL_CENTER, contentPanel);

        int offsetY = (Spring.height(usernameLabel).getValue() + 20 + Spring.height(pwdLabel).getValue() + 20 + Spring.height(loginButton).getValue()) / 2;
        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, -offsetY, SpringLayout.VERTICAL_CENTER, contentPanel);
    }


    private void initComponents() {
        titleLabel = new JLabel("欢迎来等登录界面", SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(0, 100));
        leftImgLabel = new JLabel(new ImageIcon(LoginFrame.class.getClassLoader().getResource("a.png")));
        usernameLabel = new JLabel("用户名:");
        usernameTxt = new JTextField();
        usernameTxt.setPreferredSize(new Dimension(200, 20));
        pwdLabel = new JLabel("密码:");
        pwdTxt = new JPasswordField();
        pwdTxt.setPreferredSize(new Dimension(200, 20));
        loginButton = new JButton("登录");
        exitButton = new JButton("退出");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}
