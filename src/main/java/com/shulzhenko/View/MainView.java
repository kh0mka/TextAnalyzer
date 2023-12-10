package com.shulzhenko.View;

import com.shulzhenko.Model.ResultModel;
import com.shulzhenko.Services.*;
import com.shulzhenko.Services.ValidateService;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Главное окно приложения для анализа текста.
 */
public class MainView {
    // Поля и методы класса
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

    /**
     * Метод, создающий и отображающий графический интерфейс приложения.
     */
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

    /**
     * Инициализирует главное окно приложения.
     *
     * @return Готовое главное окно приложения.
     */
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

    /**
     * Инициализирует текстовую область для ввода текста.
     *
     * @return Готовая текстовая область.
     */
    private static JTextArea initializeTextArea() {
        JTextArea inputTextArea = new JTextArea();
        inputTextArea.setFont(MAIN_FONT);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        return inputTextArea;
    }

    /**
     * Настраивает главную панель приложения.
     *
     * @param mainPanel           Главная панель приложения.
     * @param inputTextArea       Текстовая область для ввода текста.
     * @param buttonPanel         Панель с кнопками.
     * @param labelsPanel         Панель с метками.
     */
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

    /**
     * Инициализирует панель с метками и устанавливает начальные значения меткам.
     *
     * @return Готовая панель с метками.
     */
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

    /**
     * Инициализирует метки для отображения результатов анализа.
     */
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

    /**
     * Выполняет анализ введенного текста и обновляет метки с результатами анализа.
     *
     * @param inputTextArea Текстовая область для ввода текста.
     * @param frame         Фрейм приложения.
     */
    private static void performAnalysis(JTextArea inputTextArea, JFrame frame) {
        String text = inputTextArea.getText();
        String validationError = ValidateService.validateText(text, frame);
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

    /**
     * Обновляет метки и текстовую область с результатами анализа текста.
     *
     * @param text Текст для анализа.
     */
    private static void updateLabels(String text) {
        ResultModel resultModel = new ResultModel();
        resultModel.getResults(text);

        updateLabel(charCountLabel, "Количество символов: ", resultModel.charCount);
        updateLabel(spaceCountLabel, "Количество пробелов: ", resultModel.spaceCount);
        updateLabel(punctuationCountLabel, "Количество знаков препинания: ", resultModel.punctuationCount);
        updateLabel(errorCountLabel, "Количество орфографических ошибок: ", resultModel.spellingErrorCount);
        updateLabel(countSentencesLabel, "Количество предложений: ", resultModel.countSentences);
        updateLabel(countWordsLabel, "Количество слов: ", resultModel.countWords);
        updateLabel(countNarrativeSentencesLabel, "Количество повествовательных предложений: ", resultModel.countNarrativeSentences);
        updateLabel(countInterrogativeSentencesLabel, "Количество вопросительных предложений: ", resultModel.countInterrogativeSentences);
        updateLabel(countExclamatorySentencesLabel, "Количество восклицательных предложений: ", resultModel.countExclamatorySentences);
        updateLabel(styleLabel, "Стиль текста: ", TextStyleService.detectStyle(text));

        StringJoiner errorsAndReplacements = new StringJoiner("\n");
        for (Map.Entry<String, String> entry : SpellCheckService.findSpellingErrorsWithReplacements(text).entrySet()) {
            errorsAndReplacements.add(entry.getKey() + " - " + entry.getValue());
        }
        wordsWithErrorsArea.setText("Предложенные замены слов: \n" + errorsAndReplacements.toString());
    }

    /**
     * Логирует результаты анализа текста.
     *
     * @param text Введенный текст.
     */
    public static void logAnalysisResult(String text) {
        ResultModel resultModel = new ResultModel();
        resultModel.getResults(text);

        LogService.logAnalysis(
                text,
                resultModel.charCount,
                resultModel.spaceCount,
                resultModel.punctuationCount,
                resultModel.spellingErrorCount,
                resultModel.countSentences,
                resultModel.countWords,
                resultModel.countNarrativeSentences,
                resultModel.countInterrogativeSentences,
                resultModel.countExclamatorySentences
        );
    }

    /**
     * Обновляет текст метки с заданным префиксом и значением.
     *
     * @param label  Метка для обновления.
     * @param prefix Префикс текста метки.
     * @param value  Значение для отображения.
     */
    private static void updateLabel(JLabel label, String prefix, Object value) {
        label.setText(prefix + value.toString());
    }
}