package com.xxywebsite.swing;

import javax.swing.*;
import java.awt.*;

public class MyBorderLayoutFrame extends JFrame {

    public MyBorderLayoutFrame() {
        init();
    }

    private void init() {
        int width = 600;
        int height = 400;
        this.setSize(width, height);
        this.setTitle("我的窗口");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.RED);
        leftPanel.setPreferredSize(new Dimension(width / 5, 0));
        container.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
//        rightPanel.setBackground(Color.GREEN);
        container.add(rightPanel, BorderLayout.CENTER);

        rightPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.GREEN);
        topPanel.setPreferredSize(new Dimension(0, height / 5));
        rightPanel.add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE);
        rightPanel.add(mainPanel, BorderLayout.CENTER);


    }

    public static void main(String[] args) {
        new MyBorderLayoutFrame().setVisible(true);
    }
}
