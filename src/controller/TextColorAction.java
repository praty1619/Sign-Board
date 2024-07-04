// TextColorAction.java
package controller;

import ui.PreviewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextColorAction implements ActionListener {
    private PreviewPanel previewPanel;

    public TextColorAction(PreviewPanel previewPanel) {
        this.previewPanel = previewPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color textColor = JColorChooser.showDialog(null, "Choose Text Color", Color.BLACK);
        previewPanel.setDigitalBoardTextColor(textColor);
        previewPanel.repaint();
    }
}
