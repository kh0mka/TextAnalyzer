package com.shulzhenko.View;

import com.shulzhenko.Services.*;
import com.shulzhenko.Validation.ValidationCheck;

import javax.swing.*;
import java.awt.*;
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
    private static final Font MAIN_FONT = new Font("Arial", Font.PLAIN, 18);

    public static void createAndShowGUI() {
        JFrame frame = initializeFrame();
        JTextArea inputTextArea = initializeTextArea();

        JPanel buttonPanel = new JPanel();
        JButton analyzeButton = new JButton("Анализ текста");
        analyzeButton.setFont(MAIN_FONT);
        analyzeButton.addActionListener(e -> performAnalysis(inputTextArea, frame));
        buttonPanel.add(analyzeButton);

        JPanel labelsPanel = initializeLabelsPanel();

        JPanel mainPanel = new JPanel(new GridBagLayout());
        setupMainPanel(mainPanel, inputTextArea, buttonPanel, labelsPanel);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static JFrame initializeFrame() {
        JFrame frame = new JFrame("Анализ текста");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650);
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenuItem authorItem = new JMenuItem("Сведения об авторе");
        JMenuItem appItem = new JMenuItem("Сведения о программе");
        authorItem.setFont(MAIN_FONT);
        appItem.setFont(MAIN_FONT);
        authorItem.addActionListener(e -> new AboutAuthorView().setVisible(true));
        appItem.addActionListener(e -> new AboutAppView().setVisible(true));

        JMenu infoMenu = new JMenu("Информация");
        infoMenu.add(authorItem);
        infoMenu.add(appItem);
        menuBar.add(infoMenu);

        frame.setJMenuBar(menuBar);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    private static JTextArea initializeTextArea() {
        JTextArea inputTextArea = new JTextArea();
        inputTextArea.setFont(MAIN_FONT);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        return inputTextArea;
    }

    private static void setupMainPanel(JPanel mainPanel, JTextArea inputTextArea, JPanel buttonPanel, JPanel labelsPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        inputScrollPane.setPreferredSize(new Dimension(400, 150));

        wordsWithErrorsArea = new JTextArea(6, 40);
        wordsWithErrorsArea.setFont(MAIN_FONT);
        wordsWithErrorsArea.setEditable(false);
        JScrollPane wordsScrollPane = new JScrollPane(wordsWithErrorsArea);
        wordsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(inputScrollPane, gbc);
        mainPanel.add(buttonPanel, gbc);
        mainPanel.add(labelsPanel, gbc);
        mainPanel.add(wordsScrollPane, gbc);

        Color bgColor = new Color(245, 245, 220);
        mainPanel.setBackground(bgColor);
        buttonPanel.setBackground(bgColor);
        labelsPanel.setBackground(bgColor);
    }

    private static JPanel initializeLabelsPanel() {
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

        return labelsPanel;
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

    private static void performAnalysis(JTextArea inputTextArea, JFrame frame) {
        String text = inputTextArea.getText();
        String validationError = ValidationCheck.validateText(text, frame);
        if (validationError != null)
        {
            logAnalysisResult(text);
            return;
        }
        else {
            updateLabels(text);
            logAnalysisResult(text);
        }
    }

    private static void updateLabels(String text) {
        ResultService resultService = new ResultService();
        resultService.getResult(text);

        updateLabel(charCountLabel, "Количество символов: ", resultService.charCount);
        updateLabel(spaceCountLabel, "Количество пробелов: ", resultService.spaceCount);
        updateLabel(punctuationCountLabel, "Количество знаков препинания: ", resultService.punctuationCount);
        updateLabel(errorCountLabel, "Количество орфографических ошибок: ", resultService.spellingErrorCount);
        updateLabel(countSentencesLabel, "Количество предложений: ", resultService.countSentences);
        updateLabel(countWordsLabel, "Количество слов: ", resultService.countWords);
        updateLabel(countNarrativeSentencesLabel, "Количество повествовательных предложений: ", resultService.countNarrativeSentences);
        updateLabel(countInterrogativeSentencesLabel, "Количество вопросительных предложений: ", resultService.countInterrogativeSentences);
        updateLabel(countExclamatorySentencesLabel, "Количество восклицательных предложений: ", resultService.countExclamatorySentences);
        updateLabel(styleLabel, "Стиль текста: ", TextStyleService.detectStyle(text));

        StringJoiner errorsAndReplacements = new StringJoiner("\n");
        for (Map.Entry<String, String> entry : SpellCheckService.findSpellingErrorsWithReplacements(text).entrySet()) {
            errorsAndReplacements.add(entry.getKey() + " - " + entry.getValue());
        }
        wordsWithErrorsArea.setText("Предложенные замены слов: \n" + errorsAndReplacements.toString());
    }

    public static void logAnalysisResult(String text) {
        ResultService resultService = new ResultService();
        resultService.getResult(text);

        LogService.logAnalysis(
                text,
                resultService.charCount,
                resultService.spaceCount,
                resultService.punctuationCount,
                resultService.spellingErrorCount,
                resultService.countSentences,
                resultService.countWords,
                resultService.countNarrativeSentences,
                resultService.countInterrogativeSentences,
                resultService.countExclamatorySentences
        );
    }

    private static void updateLabel(JLabel label, String prefix, Object value) {
        label.setText(prefix + value.toString());
    }
}