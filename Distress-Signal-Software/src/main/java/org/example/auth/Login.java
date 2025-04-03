package org.example.auth;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Login {
    private static final String FILE_NAME = "users.txt";
    private static final String IMAGE_PATH = "D:/Java/Coding/background.png";
    private static Map<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        loadUsers();
        showRegistrationFrame();
    }

    private static void showRegistrationFrame() {
        JFrame frame = new JFrame("DSS Registration");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon(IMAGE_PATH);
                Image img = background.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new GridBagLayout()); // Center components
        frame.setContentPane(backgroundPanel);
        placeRegistrationComponents(backgroundPanel, frame);
        frame.setVisible(true);
    }

    private static void placeRegistrationComponents(JPanel panel, JFrame frame) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel userLabel = new JLabel("NID (10 digits):");
        userLabel.setForeground(Color.RED);
        panel.add(userLabel, gbc);

        gbc.gridy++;
        JTextField userText = new JTextField(20);
        panel.add(userText, gbc);

        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.RED);
        panel.add(passwordLabel, gbc);

        gbc.gridy++;
        JPasswordField passwordText = new JPasswordField(20);
        panel.add(passwordText, gbc);

        gbc.gridy++;
        JButton registerButton = new JButton("Register");
        panel.add(registerButton, gbc);

        gbc.gridy++;
        JButton loginButton = new JButton("Go to Login");
        panel.add(loginButton, gbc);

        registerButton.addActionListener(e -> {
            String nid = userText.getText().trim();
            String password = new String(passwordText.getPassword()).trim();

            if (nid.matches("\\d{10}") && !password.isEmpty()) {
                if (!users.containsKey(nid)) {
                    users.put(nid, password);
                    saveUsers();
                    JOptionPane.showMessageDialog(frame, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "NID already registered!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid NID or password!");
            }
        });

        loginButton.addActionListener(e -> {
            frame.dispose();
            showLoginFrame();
        });
    }

    private static void showLoginFrame() {
        JFrame frame = new JFrame("DSS Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon(IMAGE_PATH);
                Image img = background.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new GridBagLayout());
        frame.setContentPane(backgroundPanel);
        placeLoginComponents(backgroundPanel, frame);
        frame.setVisible(true);
    }

    private static void placeLoginComponents(JPanel panel, JFrame frame) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel userLabel = new JLabel("NID:");
        userLabel.setForeground(Color.RED);
        panel.add(userLabel, gbc);

        gbc.gridy++;
        JTextField userText = new JTextField(20);
        panel.add(userText, gbc);

        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        panel.add(passwordLabel, gbc);

        gbc.gridy++;
        JPasswordField passwordText = new JPasswordField(20);
        panel.add(passwordText, gbc);

        gbc.gridy++;
        JButton loginButton = new JButton("Login");
        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String nid = userText.getText().trim();
            String password = new String(passwordText.getPassword()).trim();

            if (users.containsKey(nid) && users.get(nid).equals(password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid NID or Password!");
            }
        });
    }

    private static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("No previous user data found.");
        }
    }

    private static void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
