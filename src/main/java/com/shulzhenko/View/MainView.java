package com.shulzhenko.View;

import com.shulzhenko.Services.SpellCheckService;
import com.shulzhenko.Services.CountService;
import com.shulzhenko.Services.TextStyleService;
import com.shulzhenko.Validation.ValidationCheck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.StringJoiner;

public class MainView {
    private static JLabel charCountLabel;
    private static JLabel spaceCountLabel;
    private static JLabel punctuationCountLabel;
    private static JLabel errorCountLabel;
    private static JLabel styleLabel;

    private static JLabel countSentencesLabel;
    private static JLabel countWordsLabel;
    private static JLabel countNarrativeSentencesLabel;
    private static JLabel countInterrogativeSentencesLabel;
    private static JLabel countExclamatorySentencesLabel;
    private static JTextArea wordsWithErrorsArea;
    private static final Font MAIN_FONT = new Font("Arial", Font.PLAIN, 18); // Увеличенный размер шрифта

    public static void createAndShowGUI() {

        Color bgColor = new Color(245, 245, 220);

        JFrame frame = new JFrame("Анализ текста");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650); // Установка размера окна
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu infoMenu = new JMenu("Информация");
        JMenuItem authorItem = new JMenuItem("Сведения об авторе");
        JMenuItem appItem = new JMenuItem("Сведения о программе");
        authorItem.setFont(MAIN_FONT);
        appItem.setFont(MAIN_FONT);

        authorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutAuthorView().setVisible(true);
            }
        });

        appItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutAppView().setVisible(true);
            }
        });

        infoMenu.add(authorItem);
        infoMenu.add(appItem);
        menuBar.add(infoMenu);
        frame.setJMenuBar(menuBar);

        JTextArea inputTextArea = new JTextArea();
        inputTextArea.setFont(MAIN_FONT);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        inputScrollPane.setPreferredSize(new Dimension(400, 150));

        JPanel buttonPanel = new JPanel();
        JButton analyzeButton = new JButton("Анализ текста");
        analyzeButton.setFont(MAIN_FONT);
        buttonPanel.add(analyzeButton);

        initializeLabels();

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.add(charCountLabel);
        labelsPanel.add(spaceCountLabel);
        labelsPanel.add(punctuationCountLabel);
        labelsPanel.add(errorCountLabel);

        labelsPanel.add(countSentencesLabel);
        labelsPanel.add(countWordsLabel);
        labelsPanel.add(countNarrativeSentencesLabel);
        labelsPanel.add(countInterrogativeSentencesLabel);
        labelsPanel.add(countExclamatorySentencesLabel);

        labelsPanel.add(styleLabel);

        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputTextArea.getText();
                ValidationCheck.validateText(text, frame);
            }
        });

        wordsWithErrorsArea = new JTextArea(6, 40);
        wordsWithErrorsArea.setFont(MAIN_FONT);
        wordsWithErrorsArea.setEditable(false);
        JScrollPane wordsScrollPane = new JScrollPane(wordsWithErrorsArea);
        wordsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        mainPanel.add(inputScrollPane, gbc);
        mainPanel.add(buttonPanel, gbc);
        mainPanel.add(labelsPanel, gbc);
        mainPanel.add(wordsScrollPane, gbc);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        mainPanel.setBackground(bgColor);
        buttonPanel.setBackground(bgColor);
        labelsPanel.setBackground(bgColor);
    }

    private static void initializeLabels() {
        charCountLabel = new JLabel("Количество символов: ");
        spaceCountLabel = new JLabel("Количество пробелов: ");
        punctuationCountLabel = new JLabel("Количество знаков препинания: ");
        errorCountLabel = new JLabel("Количество орфографических ошибок: ");

        countSentencesLabel = new JLabel("Количество предложений: ");
        countWordsLabel = new JLabel("Количество слов: ");
        countNarrativeSentencesLabel = new JLabel("Количество повествовательных предложений: ");
        countInterrogativeSentencesLabel = new JLabel("Количество вопросительных предложений: ");
        countExclamatorySentencesLabel = new JLabel("Количество восклицательных предложений: ");

        styleLabel = new JLabel("Стиль текста: ");

        charCountLabel.setFont(MAIN_FONT);
        spaceCountLabel.setFont(MAIN_FONT);
        punctuationCountLabel.setFont(MAIN_FONT);
        errorCountLabel.setFont(MAIN_FONT);

        countSentencesLabel.setFont(MAIN_FONT);
        countWordsLabel.setFont(MAIN_FONT);
        countNarrativeSentencesLabel.setFont(MAIN_FONT);
        countInterrogativeSentencesLabel.setFont(MAIN_FONT);
        countExclamatorySentencesLabel.setFont(MAIN_FONT);

        styleLabel.setFont(MAIN_FONT);
    }

    public static void updateLabels(String text) {
        int charCount = CountService.countCharacters(text);
        int spaceCount = CountService.countSpaces(text);
        int punctuationCount = CountService.countPunctuation(text);

        int countSentences = CountService.countSentences(text);
        int countWords = CountService.countWords(text);
        int countNarrativeSentences = CountService.countNarrativeSentences(text);
        int countInterrogativeSentences = CountService.countInterrogativeSentences(text);
        int countExclamatorySentences = CountService.countExclamatorySentences(text);

        int spellingErrorCount = SpellCheckService.countSpellingErrors(text);

        charCountLabel.setText("Количество символов: " + charCount);
        spaceCountLabel.setText("Количество пробелов: " + spaceCount);
        punctuationCountLabel.setText("Количество знаков препинания: " + punctuationCount);
        errorCountLabel.setText("Количество орфографических ошибок: " + spellingErrorCount);

        countSentencesLabel.setText("Количество предложений: " + countSentences);
        countWordsLabel.setText("Количество слов: " + countWords);
        countNarrativeSentencesLabel.setText("Количество повествовательных предложений: " + countNarrativeSentences);
        countInterrogativeSentencesLabel.setText("Количество вопросительных предложений: " + countInterrogativeSentences);
        countExclamatorySentencesLabel.setText("Количество восклицательных предложений: " + countExclamatorySentences);

        styleLabel.setText("Стиль текста: " + TextStyleService.detectStyle(text));

        StringJoiner errorsAndReplacements = new StringJoiner("\n");
        for (Map.Entry<String, String> entry : SpellCheckService.findSpellingErrorsWithReplacements(text).entrySet()) {
            errorsAndReplacements.add(entry.getKey() + " - " + entry.getValue());
        }
        wordsWithErrorsArea.setText("Предложенные замены слов: \n" + errorsAndReplacements.toString());
    }
}