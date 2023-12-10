package com.shulzhenko.View;

import javax.swing.*;
import java.awt.*;

/**
 * Класс AboutAppView представляет окно "О программе".
 */
public class AboutAppView extends JFrame {
    private JTextArea textArea;
    private JLabel titleLabel;
    private JButton backBtn;
    private Font mainFont;
    private Font titleFont;

    /**
     * Конструктор класса AboutAppView, инициализирующий окно "О программе".
     */
    public AboutAppView() {
        setTitle("О программе");
        setSize(600, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220));
        add(panel);

        // Настройка заголовка
        titleLabel = new JLabel("Анализ текста", SwingConstants.CENTER);
        titleFont = new Font("Verdana", Font.BOLD, 18);
        titleLabel.setFont(titleFont);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Настройка текстовой области
        mainFont = new Font("Verdana", Font.PLAIN, 14);
        textArea = new JTextArea("Данная программа анализирует текст, введенный пользователем, рассчитывая количество предложений, слов, " +
                "а также различных типов предложений (повествовательных, вопросительных, восклицательных). Она также определяет количество " +
                "орфографических ошибок с предложениями по их исправлению, подсчитывает общее количество символов и знаков препинания, а " +
                "также определяет стиль текста. Программа включает в себя четыре окна: стартовое, основное, сведения об авторе и о программе.");

        textArea.setFont(mainFont);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setBackground(new Color(245, 245, 220));
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Настройка кнопки "Назад"
        backBtn = new JButton("Назад");
        backBtn.setFont(mainFont);
        backBtn.addActionListener(e -> dispose());
        panel.add(backBtn, BorderLayout.SOUTH);

        setVisible(true);
    }
}