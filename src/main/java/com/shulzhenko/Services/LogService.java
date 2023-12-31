package com.shulzhenko.Services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс LogService предоставляет методы для ведения лога анализа текста.
 */
public class LogService {
    private static final String LOG_FILE = "LogService.txt";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    /**
     * Метод для записи результатов анализа текста в лог-файл.
     *
     * @param text                     Исходный текст.
     * @param charCount                Количество символов в тексте.
     * @param spaceCount               Количество пробелов в тексте.
     * @param punctuationCount         Количество знаков препинания в тексте.
     * @param spellingErrorCount       Количество орфографических ошибок в тексте.
     * @param countSentences           Количество предложений в тексте.
     * @param countWords               Количество слов в тексте.
     * @param countNarrativeSentences  Количество повествовательных предложений в тексте.
     * @param countInterrogativeSentences Количество вопросительных предложений в тексте.
     * @param countExclamatorySentences Количество восклицательных предложений в тексте.
     */
    public static void logAnalysis(String text, int charCount, int spaceCount, int punctuationCount, int spellingErrorCount, int countSentences, int countWords, int countNarrativeSentences, int countInterrogativeSentences, int countExclamatorySentences) {
        String timestamp = dateFormat.format(new Date());
        String logEntry;

        if (text.isEmpty()) {
            logEntry = String.format("[%s] Ошибка при анализе текста: Поле текста пустое, нечего анализировать.\n\n", timestamp);
        } else if (text.length() > 1024) {
            logEntry = String.format("[%s] Ошибка при анализе текста: Длина текста не должна превышать 1024 символов.\n\n", timestamp);
        } else {
            String results = String.format("Количество символов: %d;\nКоличество пробелов: %d;\nКоличество знаков препинания: %d;\nКоличество орфографических ошибок: %d;\nКоличество предложений: %d;\nКоличество слов: %d;\nКоличество повествовательных предложений: %d;\nКоличество вопросительных предложений: %d;\nКоличество восклицательных предложений: %d.\n",
                    charCount, spaceCount, punctuationCount, spellingErrorCount, countSentences, countWords, countNarrativeSentences, countInterrogativeSentences, countExclamatorySentences);
            logEntry = String.format("[%s] Был проанализирован текст: \"%s\".\nРезультаты:\n%s\n", timestamp, text, results);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(logEntry);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл лога: " + e.getMessage());
        }
    }
}