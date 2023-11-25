package com.shulzhenko.Services;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.Russian;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellCheckService {
    public static int countSpellingErrors(String text) {
        int errorCount = 0;
        try {
            Language russian = new Russian();
            JLanguageTool langTool = new JLanguageTool(russian);

            // Разделение текста на слова с учетом знаков препинания
            String[] words = text.split("[ .,;?!-]+");
            for (String word : words) {
                List<RuleMatch> matches = langTool.check(word);

                // Проверяем, есть ли предложенные исправления
                for (RuleMatch match : matches) {
                    if (!match.getSuggestedReplacements().isEmpty()) {
                        String correctWord = match.getSuggestedReplacements().get(0);
                        errorCount += countLetterMismatches(word, correctWord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // В случае ошибки возвращаем -1
        }
        return errorCount;
    }

    private static int countLetterMismatches(String original, String corrected) {
        int mismatches = 0;
        for (int i = 0; i < original.length(); i++) {
            if (i < corrected.length() && original.charAt(i) != corrected.charAt(i)) {
                mismatches++;
            }
        }
        return mismatches + Math.abs(original.length() - corrected.length());
    }

    public static Map<String, String> findSpellingErrorsWithReplacements(String text) {
        Map<String, String> errorsWithReplacements = new HashMap<>();
        try {
            Language russian = new Russian();
            JLanguageTool langTool = new JLanguageTool(russian);

            String[] words = text.split("[ .,;?!-]+");
            for (String word : words) {
                List<RuleMatch> matches = langTool.check(word);

                for (RuleMatch match : matches) {
                    if (!match.getSuggestedReplacements().isEmpty()) {
                        String correctWord = match.getSuggestedReplacements().get(0);
                        errorsWithReplacements.put(word, correctWord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // В случае ошибки возвращаем пустую карту
        }
        return errorsWithReplacements;
    }
}
