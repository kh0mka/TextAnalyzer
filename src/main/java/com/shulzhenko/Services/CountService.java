package com.shulzhenko.Services;

public class CountService {

    public static int countCharacters(String text) {
        return text.length();
    }

    public static int countSpaces(String text) {
        return text.replaceAll("[^\\s]", "").length();
    }

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

    public static int countSentences(String text) {
        String[] sentences = text.split("[.!?]+");
        if (sentences.length == 0 || (sentences.length == 1 && sentences[0].isEmpty())) {
            return 0;
        }
        return sentences[sentences.length - 1].isEmpty() ? sentences.length - 1 : sentences.length;
    }

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

    public static int countNarrativeSentences(String text) {
        return (int) text.chars().filter(ch -> ch == '.').count();
    }

    public static int countInterrogativeSentences(String text) {
        return (int) text.chars().filter(ch -> ch == '?').count();
    }

    public static int countExclamatorySentences(String text) {
        return (int) text.chars().filter(ch -> ch == '!').count();
    }
}