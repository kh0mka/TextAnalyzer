package com.shulzhenko.View;

import javax.swing.*;
import java.awt.*;

/**
 * Класс AboutAuthorView представляет окно "Об авторе".
 */
public class AboutAuthorView extends JFrame {
    private JLabel jlblAuthor, jlblGroup, jlblName, jlblEmail, jlblImage;
    private JButton jbtnBack;
    private ImageIcon iconAuthor;

    /**
     * Конструктор класса AboutAuthorView, инициализирующий окно "Об авторе".
     */
    public AboutAuthorView() {
        JFrame frame = new JFrame("Об авторе");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 220));

        iconAuthor = new ImageIcon(new ImageIcon(getClass().getResource("/images/Author.jpg"))
                .getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH));
        jlblImage = new JLabel(iconAuthor);
        jlblImage.setBounds(15, 50, 300, 400);
        panel.add(jlblImage);

        setupLabel(jlblAuthor = new JLabel("Об авторе"), new Font("Verdana", Font.BOLD, 16), 110, 10, 100, 20, panel);
        setupLabel(jlblGroup = new JLabel("Студент группы 10702221"), new Font("Verdana", Font.BOLD, 14), 30, 460, 500, 20, panel);
        setupLabel(jlblName = new JLabel("Шульженко Максим Викторович"), new Font("Verdana", Font.BOLD, 14), 30, 490, 500, 20, panel);
        setupLabel(jlblEmail = new JLabel("shulzhenko-m@inbox.ru"), new Font("Verdana", Font.BOLD, 14), 30, 520, 500, 20, panel);

        jbtnBack = new JButton("Назад");
        jbtnBack.setFont(new Font("Verdana", Font.BOLD, 14));
        jbtnBack.setBounds(30, 560, 270, 30);
        jbtnBack.addActionListener(e -> {
            new MainView();
            frame.setVisible(false);
        });
        panel.add(jbtnBack);

        frame.add(panel);
        frame.setSize(345, 650);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void setupLabel(JLabel label, Font font, int x, int y, int width, int height, JPanel panel) {
        label.setFont(font);
        label.setBounds(x, y, width, height);
        panel.add(label);
    }
}