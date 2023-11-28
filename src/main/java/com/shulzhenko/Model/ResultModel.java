package com.shulzhenko.Model;

import com.shulzhenko.Services.CountService;
import com.shulzhenko.Services.SpellCheckService;

public class ResultModel {
    public int charCount;
    public int spaceCount;
    public int punctuationCount;
    public int spellingErrorCount;
    public int countSentences;
    public int countWords;
    public int countNarrativeSentences;
    public int countInterrogativeSentences;
    public int countExclamatorySentences;
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
