package com.shulzhenko.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutAuthorView extends JFrame {
    private JLabel jlblAuthor;
    private JLabel jlblGroup;
    private JLabel jlblName;
    private JLabel jlblEmail;
    private JButton jbtnBack;
    private JLabel jlblImage;
    private ImageIcon iconAuthor;

    /**
     * Constructor for creating an object
     */
    public AboutAuthorView(){
        JFrame frame=new JFrame("Об авторе");
        JPanel panel=new JPanel();


        iconAuthor = new ImageIcon(getClass().getResource("/images/author.jpg"));
        jlblImage = new JLabel(iconAuthor);
        panel.add(jlblImage).setBounds(15,50,300,400);

        Font font=new Font("Verdana",Font.BOLD,16);
        jlblAuthor =new JLabel("Об авторе");
        jlblAuthor.setFont(font);
        panel.add(jlblAuthor).setBounds(110,10,100,20);

        font=new Font("Verdana",Font.BOLD,14);
        jlblGroup =new JLabel("Студент группы 10702221");
        jlblGroup.setFont(font);
        panel.add(jlblGroup).setBounds(30,460,500,20);

        jlblName =new JLabel("Шульженко Максим Александрович");
        jlblName.setFont(font);
        panel.add(jlblName).setBounds(30,490,500,20);

        jlblEmail =new JLabel("shulzhenko-m@inbox.ru");
        jlblEmail.setFont(font);
        panel.add(jlblEmail).setBounds(30,520,500,20);

        jbtnBack =new JButton("Назад");
        jbtnBack.setFont(font);
        panel.add(jbtnBack).setBounds(30,560,270,30);

        jbtnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainView();
                frame.setVisible(false);
            }
        });

        panel.setLayout(null);
        frame.add(panel);



        panel.setBackground(new Color(245, 245, 220));
        frame.setSize(345, 650);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
