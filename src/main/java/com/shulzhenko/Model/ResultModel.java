package com.shulzhenko.Model;

import com.shulzhenko.Services.CountService;
import com.shulzhenko.Services.SpellCheckService;

/**
 * Класс ResultModel представляет модель для хранения результатов анализа текста.
 */
public class ResultModel {
    /**
     * Поле, хранящее количество символов в тексте.
     */
    public int charCount;

    /**
     * Поле, хранящее количество пробелов в тексте.
     */
    public int spaceCount;

    /**
     * Поле, хранящее количество знаков пунктуации в тексте.
     */
    public int punctuationCount;

    /**
     * Поле, хранящее количество ошибок в правописании в тексте.
     */
    public int spellingErrorCount;

    /**
     * Поле, хранящее количество предложений в тексте.
     */
    public int countSentences;

    /**
     * Поле, хранящее количество слов в тексте.
     */
    public int countWords;

    /**
     * Поле, хранящее количество повествовательных предложений в тексте.
     */
    public int countNarrativeSentences;

    /**
     * Поле, хранящее количество вопросительных предложений в тексте.
     */
    public int countInterrogativeSentences;

    /**
     * Поле, хранящее количество восклицательных предложений в тексте.
     */
    public int countExclamatorySentences;

    /**
     * Метод для получения результатов анализа текста.
     *
     * @param text Текст, который требуется проанализировать.
     */
    public void getResults(String text) {
        this.charCount = CountService.countCharacters(text);
        this.spaceCount = CountService.countSpaces(text);
        this.punctuationCount = CountService.countPunctuation(text);
        this.spellingErrorCount = SpellCheckService.countSpellingErrors(text);
        this.countSentences = CountService.countSentences(text);
        this.countWords = CountService.countWords(text);
        this.countNarrativeSentences = CountService.countNarrativeSentences(text);
        this.countInterrogativeSentences = CountService.countInterrogativeSentences(text);
        this.countExclamatorySentences = CountService.countExclamatorySentences(text);
    }
}