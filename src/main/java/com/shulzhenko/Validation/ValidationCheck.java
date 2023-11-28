package com.shulzhenko.Validation;

import javax.swing.*;

public class ValidationCheck {

    public static String validateText(String text, JFrame frame) {
        if (text.isEmpty()) {
            String message = "Поле текста пустое, нечего анализировать. Пожалуйста, введите текст";
            JOptionPane.showMessageDialog(frame, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
            return message;
        } else if (text.length() > 1024) {
            String message = "Длина текста не должна превышать 1024 символов. Слишком большой объем";
            JOptionPane.showMessageDialog(frame, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
            return message;
        }
        return null; // Валидация прошла успешно
    }
}