package com.shulzhenko.Services;

/**
 * Класс CountService предоставляет методы для анализа текста.
 */
public class CountService {

    /**
     * Метод для подсчета количества символов в тексте.
     *
     * @param text Исходный текст.
     * @return Количество символов в тексте.
     */
    public static int countCharacters(String text) {
        return text.length();
    }

    /**
     * Метод для подсчета количества пробелов в тексте.
     *
     * @param text Исходный текст.
     * @return Количество пробелов в тексте.
     */
    public static int countSpaces(String text) {
        return text.replaceAll("[^\\s]", "").length();
    }

    /**
     * Метод для подсчета количества знаков пунктуации в тексте.
     *
     * @param text Исходный текст.
     * @return Количество знаков пунктуации в тексте.
     */
    public static int countPunctuation(String text) {
        int punctuationCount = 0;
        char[] punctuationMarks = {'.', ',', '-', ':', ';', '?', '!'};

        for (char punctuationMark : punctuationMarks) {
            for (char c : text.toCharArray()) {
                if (c == punctuationMark) {
                    punctuationCount++;
                }
            }
        }
        return punctuationCount;
    }

    /**
     * Метод для подсчета количества предложений в тексте.
     *
     * @param text Исходный текст.
     * @return Количество предложений в тексте.
     */
    public static int countSentences(String text) {
        String[] sentences = text.split("[.!?]+");
        if (sentences.length == 0 || (sentences.length == 1 && sentences[0].isEmpty())) {
            return 0;
        }
        return sentences[sentences.length - 1].isEmpty() ? sentences.length - 1 : sentences.length;
    }

    /**
     * Метод для подсчета количества слов в тексте.
     *
     * @param text Исходный текст.
     * @return Количество слов в тексте.
     */
    public static int countWords(String text) {
        text = text.trim();
        if (text.isEmpty()) {
            return 0;
        }

        int wordCount = 0;
        boolean inWord = false;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ' && !inWord) {
                inWord = true; // Начало нового слова
            } else if (text.charAt(i) == ' ' && inWord) {
                inWord = false; // Конец слова
                wordCount++;
            }
        }

        if (inWord) { // Проверка на последнее слово в строке
            wordCount++;
        }

        return wordCount;
    }

    /**
     * Метод для подсчета количества повествовательных предложений в тексте.
     *
     * @param text Исходный текст.
     * @return Количество повествовательных предложений в тексте.
     */
    public static int countNarrativeSentences(String text) {
        return (int) text.chars().filter(ch -> ch == '.').count();
    }

    /**
     * Метод для подсчета количества вопросительных предложений в тексте.
     *
     * @param text Исходный текст.
     * @return Количество вопросительных предложений в тексте.
     */
    public static int countInterrogativeSentences(String text) {
        return (int) text.chars().filter(ch -> ch == '?').count();
    }

    /**
     * Метод для подсчета количества восклицательных предложений в тексте.
     *
     * @param text Исходный текст.
     * @return Количество восклицательных предложений в тексте.
     */
    public static int countExclamatorySentences(String text) {
        return (int) text.chars().filter(ch -> ch == '!').count();
    }
}