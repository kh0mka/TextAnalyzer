package com.shulzhenko.Validation;

import javax.swing.*;
import static com.shulzhenko.View.MainView.updateLabels;

public class ValidationCheck {

    public static void validateText(String text, JFrame frame) {
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Поле текста пустое, нечего анализировать", "Пожалуйста, введите текст", JOptionPane.ERROR_MESSAGE);
        } else {
            if (text.length() > 1024) {
                JOptionPane.showMessageDialog(frame, "Длина текста не должна превышать 1024 символов", "Слишком большой объем", JOptionPane.ERROR_MESSAGE);
            } else {
                updateLabels(text);
            }
        }
    }
}