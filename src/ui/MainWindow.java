// MainWindow.java
package ui;

import javax.swing.*;
import model.DigitalBoard;

import java.awt.*;

public class MainWindow extends JFrame {
    private PreviewPanel previewPanel;

    public MainWindow() {
        setTitle("Digital Board Creator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the frame on the screen

        DigitalBoard digitalBoard = new DigitalBoard(); // Create a DigitalBoard object

        PreviewPanel previewPanel = new PreviewPanel(digitalBoard); // Pass the DigitalBoard object to the PreviewPanel constructor

        // Create a font for the digital board
        Font font = new Font("Arial", Font.PLAIN, 12); // Example font with size 12

        // Set the font for the digital board
        previewPanel.getDigitalBoard().setFont(font);

        // Add components to the frame
        add(previewPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}
