package com.shulzhenko.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Класс StartView представляет стартовое окно приложения для анализа текста.
 * Это окно содержит информацию о курсовой работе, студенте, преподавателе и другие элементы.
 */
public class StartView extends JFrame {
    private JFrame jframe;
    private JLabel jlblBntu;
    private JLabel jlblFaculty;
    private JLabel jlblDepartment;
    private JLabel jlblCourseWork;
    private JLabel jlblSubject;
    private JLabel jlblTopic;
    private JLabel jlblImage;
    private JLabel jlblStudent;
    private JLabel jlblStudentName;
    private JLabel jlblTeacher;
    private JLabel jlblTeacherName;
    private JLabel jlblCity;
    private JButton jbtnNext;
    private JButton jbntExit;
    private ImageIcon iconSort;

    /**
     * Конструктор класса StartView инициализирует и отображает стартовое окно приложения.
     * Окно содержит информацию о курсовой работе, студенте и преподавателе, а также кнопки для перехода к основному окну
     * и выхода из приложения.
     */
    public StartView(){
        jframe = new JFrame("Курсовой проект");

        jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new Timer(60000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jframe.dispose();
                    }
                }).start();
            }
        });

        JPanel panel=new JPanel();
        Font font=new Font("Verdana",Font.PLAIN,14);

        jlblBntu =new JLabel("Белорусский национальный технический университет");
        jlblBntu.setFont(font);
        panel.add(jlblBntu).setBounds(220,10,600,20);

        jlblFaculty =new JLabel("Факультет информацонных технологий и робототехники");
        jlblFaculty.setFont(font);
        panel.add(jlblFaculty).setBounds(200,50,600,20);

        jlblDepartment =new JLabel("Кафедра программного обеспечения информационных систем и технологий");
        jlblDepartment.setFont(font);
        panel.add(jlblDepartment).setBounds(140,70,600,20);

        font=new Font("Verdana",Font.BOLD,16);
        jlblCourseWork =new JLabel("Курсовая работа");
        jlblCourseWork.setFont(font);
        panel.add(jlblCourseWork).setBounds(320,160,400,20);

        font=new Font("Verdana",Font.PLAIN,14);
        jlblSubject =new JLabel("По дисциплине «Программировние на языке Java»");
        jlblSubject.setFont(font);
        panel.add(jlblSubject).setBounds(240,180,400,20);

        font=new Font("Verdana",Font.BOLD,18);
        jlblTopic =new JLabel("Анализ текста");
        jlblTopic.setFont(font);
        panel.add(jlblTopic).setBounds(320,240,700,20);

        font=new Font("Verdana",Font.PLAIN,14);
        jlblStudent =new JLabel("Выполнил: Студент группы 10702221");
        jlblStudent.setFont(font);
        panel.add(jlblStudent).setBounds(450,300,400,20);

        jlblStudentName =new JLabel("Шульженко Максим Викторович");
        jlblStudentName.setFont(font);
        panel.add(jlblStudentName).setBounds(450,320,400,20);

        jlblTeacher =new JLabel("Преподаватель: к.ф.-м.н.доц.");
        jlblTeacher.setFont(font);
        panel.add(jlblTeacher).setBounds(450,420,400,20);

        jlblTeacherName =new JLabel("Сидорик Валерий Владимирович");
        jlblTeacherName.setFont(font);
        panel.add(jlblTeacherName).setBounds(450,440,400,20);

        jlblCity =new JLabel("Минск, 2023");
        jlblCity.setFont(font);
        panel.add(jlblCity).setBounds(330,535,200,20);

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/Icon.png"));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(256, 256, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel jlblImage = new JLabel(resizedIcon);
        panel.add(jlblImage).setBounds(60, 280, 256, 256);

        jbtnNext = new JButton("Далее");

        jbtnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = new JFrame("Text Analyzer");
                MainView.createAndShowGUI();

                jframe.setVisible(false);
            }
        });

        jbtnNext.setFont(font);
        jbtnNext.setSize(200,30);
        panel.add(jbtnNext).setBounds(85,570,200,30);

        jbntExit = new JButton("Выход");

        jbntExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jbntExit.setFont(font);
        jbntExit.setSize(200,30);
        panel.add(jbntExit).setBounds(485,570,200,30);

        panel.setLayout(null);
        jframe.add(panel);

        panel.setBackground(new Color(245, 245, 220));
        jframe.setSize(800,650);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jframe.setVisible(true);
        jframe.setResizable(false);
    }
}