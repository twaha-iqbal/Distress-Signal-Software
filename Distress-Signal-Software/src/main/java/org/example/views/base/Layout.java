package org.example.views.base;

import javax.swing.*;
import java.awt.*;

public abstract class Layout extends JFrame {

    public Layout(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen window by default

        // Set minimum size to avoid the window becoming too small
        setMinimumSize(new Dimension(800, 600)); // Example: Minimum size of 800x600
    }

    // Method to switch between scenes (views)
    public void switchScene(JPanel newScene) {
        setContentPane(newScene);
        revalidate();
        repaint();
    }

    // Method to set window size
    public void setWindowSize(int width, int height) {
        setSize(width, height);
    }

    // Method to set the title of the window
    public void setWindowTitle(String title) {
        setTitle(title);
    }
}
