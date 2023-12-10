package com.shulzhenko;

import com.shulzhenko.View.StartView;

import javax.swing.*;

/**
 * Главный класс приложения, который запускает пользовательский интерфейс.
 */
public class MainApp {
    /**
     * Метод main, который является точкой входа в приложение.
     *
     * @param args Аргументы командной строки (не используются в данном приложении).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StartView startView = new StartView();
            }
        });
    }
}