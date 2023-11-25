package com.shulzhenko;

import com.shulzhenko.View.StartView;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StartView startView = new StartView();
            }
        });
    }
}
