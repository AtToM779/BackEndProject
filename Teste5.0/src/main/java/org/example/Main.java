package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new org.example.gui.TelaLogin().setVisible(true);
        });
    }
}
