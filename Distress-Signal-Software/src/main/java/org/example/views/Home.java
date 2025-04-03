package org.example.views;

import org.example.controllers.HomeController;
import org.example.services.session.UserSession;
import org.example.views.base.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends Layout {

    // Components
    public JButton distressButton;
    public JButton logoutButton;
    private HomeController homeController;

    public Home() {
        super("Home - Distress Signal");
        setLayout(new BorderLayout());

        // Create the navigation bar
        JPanel navBar = new JPanel();
        navBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        navBar.setBackground(Color.DARK_GRAY);

        // Logout button
        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 18));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.RED);
        logoutButton.setFocusPainted(false);
        navBar.add(logoutButton);

        // Add the nav bar to the top
        add(navBar, BorderLayout.NORTH);

        // Center panel for the distress button
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout()); // Center alignment

        // Create the distress button
        distressButton = new JButton("Distress Signal");
        distressButton.setFont(new Font("Arial", Font.BOLD, 30));
        distressButton.setBackground(Color.RED);
        distressButton.setForeground(Color.WHITE);
        distressButton.setFocusPainted(false);
        distressButton.setPreferredSize(new Dimension(200, 100)); // Fixed size

        // Add the distress button to the center panel
        centerPanel.add(distressButton);

        // Add the center panel to the main layout
        add(centerPanel, BorderLayout.CENTER);

        // Initialize HomeController
        homeController = new HomeController();

        // Add action listeners
        addActionListeners();
    }

    private void addActionListeners() {
        // ActionListener for the distress signal button
        distressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to send distress signals
                homeController.sendDistress();
            }
        });

        // ActionListener for the logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Log out the user and navigate back to login view
                UserSession.logOut();
                JOptionPane.showMessageDialog(null, "Logged out successfully!");
                // Add additional code here for navigation to login screen if needed
            }
        });
    }

    // Main method to test the Home view
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Home home = new Home();
            home.setVisible(true);
        });
    }
}
