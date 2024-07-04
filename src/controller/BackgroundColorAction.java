// BackgroundColorAction.java
package controller;

import ui.PreviewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackgroundColorAction implements ActionListener {
    private PreviewPanel previewPanel;

    public BackgroundColorAction(PreviewPanel previewPanel) {
        this.previewPanel = previewPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color bgColor = JColorChooser.showDialog(null, "Choose Background Color", Color.WHITE);
        previewPanel.setDigitalBoardBackgroundColor(bgColor);
        previewPanel.repaint();
    }
}
