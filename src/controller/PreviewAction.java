// PreviewAction.java
package controller;

import util.ImageUtils;
import ui.PreviewPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreviewAction implements ActionListener {
    private PreviewPanel previewPanel;
    private String filePath;

    public PreviewAction(PreviewPanel previewPanel, String filePath) {
        this.previewPanel = previewPanel;
        this.filePath = filePath;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ImageUtils.savePanelAsImage(previewPanel, filePath);
    }
}
