package com.xxywebsite.swing;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class MyFlowLayoutFrame extends JFrame {
    private JButton jButton1;

    private JButton jButton2;

    private void init() {
        jButton1 = new JButton("按钮1");
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton2 = new JButton("按钮2");

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());
        container.add(jButton1);
        container.add(jButton2);

        this.setSize(600, 400);
        this.setTitle("我的窗口");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public MyFlowLayoutFrame() {
        init();
    }

}
