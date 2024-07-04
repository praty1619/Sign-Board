package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.DigitalBoard;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PreviewPanel extends JPanel {
    private DigitalBoard digitalBoard;
    private Point startPoint;
    private Point endPoint;
    private Color backgroundColor = Color.WHITE;
    private Color textColor = Color.BLACK;;
    private BufferedImage offScreenImage;

    public PreviewPanel(DigitalBoard digitalBoard) {
        this.digitalBoard = digitalBoard;
        setBackground(backgroundColor); // Set default background color
        setLayout(new FlowLayout());  // Use FlowLayout for simplicity
        createOffScreenImage();

        // Create buttons for selecting text color and background color
        JButton textColorButton = new JButton("Text Color");
        JButton bgColorButton = new JButton("Background Color");

        // Create buttons for resetting the board and selecting eraser tool
        JButton resetButton = new JButton("Reset Board");

        // Create a new button for downloading the image
        JButton downloadButton = new JButton("Download Image");

        // Add action listeners to handle color selection
        textColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open color chooser dialog for selecting text color
                Color selectedColor = JColorChooser.showDialog(null, "Choose Text Color", textColor);
                if (selectedColor != null) {
                    textColor = selectedColor; // Update text color
                    digitalBoard.setTextColor(textColor); // Set text color in digital board
                    repaint(); // Repaint panel to reflect changes
                    System.out.println("Selected Text Color: " + textColor);
                }
            }
        });

        bgColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open color chooser dialog for selecting background color
                Color selectedColor = JColorChooser.showDialog(null, "Choose Background Color", backgroundColor);
                if (selectedColor != null) {
                    backgroundColor = selectedColor; // Update background color
                    setBackground(backgroundColor); // Set background color of panel
                    digitalBoard.setBackgroundColor(backgroundColor);
                    System.out.println("Selected Background Color: " + backgroundColor);
                }
            }
        });

        // Add action listener for the reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the digital board
                digitalBoard.setText(null); // Clear text
                repaint(); // Repaint panel to reflect changes
            }
        });

        // Add action listener to the download button
        downloadButton.addActionListener(e -> {
            // Capture the current state of the panel as an image
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();

            // Paint the background color
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());

            // Dispose graphics context to release resources
            g2d.dispose();

            // Define the file name for saving the image
            String fileName = "signature.png";

            // Save the image to a file
            try {
                File outputFile = new File(fileName);
                ImageIO.write(image, "png", outputFile);
                System.out.println("Image saved as: " + outputFile.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        // Add buttons to the panel
        add(textColorButton);
        add(bgColorButton);

        // Add buttons to the panel
        add(resetButton);

        // Add the download button to the panel
        add(downloadButton);

        // Add mouse listeners
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                endPoint = startPoint;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                // Draw the signature on the digital board
                drawSignature();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                // Draw the signature as the mouse moves
                drawSignature();
                startPoint = endPoint;
            }
        });
    }

    public PreviewPanel() {

    }

    // Method to create the off-screen image
    private void createOffScreenImage() {
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) {
            // Set default dimensions if width or height is invalid
            width = 1;
            height = 1;
        }
        offScreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = offScreenImage.createGraphics();
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
    }


    private void drawSignature() {
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2)); // Adjust the stroke thickness as needed
        g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        g2d.dispose();
    }

    // Modify the drawSignature method to accept Graphics2D as a parameter
    private void drawSignature(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2)); // Adjust the stroke thickness as needed
        g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }

    public void setDigitalBoardBackgroundColor(Color bgColor) {
        backgroundColor = bgColor;
        repaint();
    }

    public void setDigitalBoardTextColor(Color textColor) {
        this.textColor = textColor;
        repaint();
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        digitalBoard.draw((Graphics2D) g, getWidth(), getHeight()); // Draw digital board
//    }

    // Modify the paintComponent method to draw the off-screen image and the signature
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the off-screen image
        g2d.drawImage(offScreenImage, 0, 0, null);

        // Draw the digital board
        digitalBoard.draw(g2d, getWidth(), getHeight());

        // Draw the signature
        if (startPoint != null && endPoint != null) {
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2)); // Adjust the stroke thickness as needed
            g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }

    public DigitalBoard getDigitalBoard() {
        return digitalBoard;
    }

    public void setDigitalBoard(DigitalBoard digitalBoard) {
        this.digitalBoard = digitalBoard;
    }
}
