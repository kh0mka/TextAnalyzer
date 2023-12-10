package com.shulzhenko.Services;

import javax.swing.*;

/**
 * Класс ValidateService предоставляет методы для валидации текста перед анализом.
 */
public class ValidateService {

    /**
     * Метод для валидации текста перед анализом.
     *
     * @param text  Исходный текст.
     * @param frame Окно, на котором будет отображаться сообщение об ошибке.
     * @return Сообщение об ошибке, если валидация не прошла, в противном случае - null.
     */
    public static String validateText(String text, JFrame frame) {
        if (text.isEmpty()) {
            String message = "Поле текста пустое, нечего анализировать. Пожалуйста, введите текст";
            JOptionPane.showMessageDialog(frame, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
            return message;
        } else if (text.length() > 1024) {
            String message = "Длина текста не должна превышать 1024 символа. Слишком большой объем";
            JOptionPane.showMessageDialog(frame, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
            return message;
        }
        return null; // Валидация прошла успешно
    }
}