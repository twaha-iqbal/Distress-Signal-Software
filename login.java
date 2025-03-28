import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class login {
    private static final String FILE_NAME = "users.txt";
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
                
                g.setColor(new Color(0,100,0));
                g.fillRect(0, 0, getWidth(), getHeight()); // Green background
                
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 60));
                g.drawString("DSS", 120, 180); // Draw DSS text
            }
        };

        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        placeRegistrationComponents(backgroundPanel, frame);
        frame.setVisible(true);
    }

    private static void placeRegistrationComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("NID (10 digits):");
        userLabel.setBounds(10, 20, 150, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(160, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(160, 50, 165, 25);
        panel.add(passwordText);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(10, 90, 150, 25);
        panel.add(registerButton);

        JButton loginButton = new JButton("Go to Login");
        loginButton.setBounds(170, 90, 150, 25);
        panel.add(loginButton);

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
                g.setColor(new Color(0,100,0));
                g.fillRect(0, 0, getWidth(), getHeight()); // Black background
                
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 60));
                g.drawString("DSS", 120, 180); // Draw DSS text
            }
        };

        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        placeLoginComponents(backgroundPanel, frame);
        frame.setVisible(true);
    }

    private static void placeLoginComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("NID:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(160, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(160, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 90, 150, 25);
        panel.add(loginButton);

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
