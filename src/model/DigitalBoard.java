// DigitalBoard.java
package model;

import java.awt.*;

public class DigitalBoard {
    private String text;
    private Font font;
    private Color textColor;
    private Color backgroundColor;
    private boolean eraserMode = false;

    public DigitalBoard() {
        // Default constructor
    }

    public DigitalBoard(String text, Font font, Color textColor, Color backgroundColor) {
        this.text = text;
        this.font = font;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        System.out.println("Text Color Set: " + textColor);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        System.out.println("Background Color Set: " + backgroundColor);
    }

    public void draw(Graphics2D g2d, int width, int height) {

        System.out.println("Drawing with Text Color: " + textColor);
        System.out.println("Drawing with Background Color: " + backgroundColor);

        // Clear the board with the background color
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, width, height);

            // Check if text is null
            if (text != null && !text.isEmpty()) {

                // Set the font and color for the text
                g2d.setFont(font);
                g2d.setColor(textColor);

                // Draw text at appropriate location
                // You may need to adjust the coordinates based on font size
                int x = (width - g2d.getFontMetrics().stringWidth(text)) / 2;
                int y = height / 2;
                g2d.drawString(text, x, y);
        }
    }
}

