package org.example.styleUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MapGUI {
    private static Map<String, JPanel> areaPanels = new HashMap<>();
    private static Map<String, String> crimeDetails = new HashMap<>();
    private static JLayeredPane layeredPane;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mapping System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layeredPane = new JLayeredPane();
        frame.setContentPane(layeredPane);
        frame.setLayout(null);

        ImageIcon icon = new ImageIcon("Dhaka.jpg");
        JLabel background = new JLabel(icon);
        background.setBounds(0, 0, 800, 600);
        layeredPane.add(background, Integer.valueOf(0));

        JLabel searchLabel = new JLabel("Search Location");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 16));
        searchLabel.setBounds(50, 10, 200, 20);
        layeredPane.add(searchLabel, Integer.valueOf(1));

        JTextField searchField = new JTextField();
        searchField.setBounds(50, 40, 200, 30);
        layeredPane.add(searchField, Integer.valueOf(1));

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(260, 40, 100, 30);
        layeredPane.add(searchButton, Integer.valueOf(1));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText().trim();
                displayArea(searchQuery);
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText().trim();
                displayArea(searchQuery);
            }
        });

        initializeCrimeDetails();

        addProgressBar("Badda", "Badda Crime Risk", 50, 100, 70, Color.ORANGE);
        addProgressBar("Shahbagh", "Shahbagh Crime Risk", 50, 200, 60, Color.YELLOW);
        addProgressBar("Bashundhara", "Bashundhara Crime Risk", 50, 300, 40, Color.GREEN);
        addProgressBar("Cantonment", "Cantonment Areas Crime Risk", 50, 400, 30, Color.BLUE);
        addProgressBar("Mohammadpur", "Mohammadpur Crime Risk", 50, 500, 80, Color.RED);

        frame.setVisible(true);
    }

    private static void initializeCrimeDetails() {

        crimeDetails.put("Badda", "• Mugging & Snatching – Frequent incidents targeting pedestrians and rickshaw passengers, especially at night.\n" +
                "• Drug Trafficking – Reports indicate that illegal drug trade is a growing concern.\n" +
                "• Extortion – Local businesses and small vendors often face threats from extortionists.");
        crimeDetails.put("Shahbagh", "• Political Violence – Frequent protests and clashes occur near the university area.\n" +
                "• Theft & Pickpocketing – Due to the high number of pedestrians and students, pickpocketing is a major issue.\n" +
                "• Drug Abuse – Certain areas near educational institutions have reported an increase in substance abuse.");
        crimeDetails.put("Bashundhara", "• Robbery & Home Invasions – Targeting upscale residential properties and luxury apartments.\n" +
                "• Cybercrimes & Scams – Digital fraud and online financial scams are increasing in this area.\n" +
                "• Vehicle Theft – Cars and motorcycles parked in less-secure areas are often stolen.");
        crimeDetails.put("Cantonment", "• Restricted Area Violations – Cases of unauthorized access and security breaches in military zones.\n" +
                "• Smuggling & Illegal Trade – Some reports indicate involvement in illegal goods movement.\n" +
                "• Fraud & Financial Crimes – Cases of counterfeit money and banking fraud have been reported.");
        crimeDetails.put("Mohammadpur", "• Mugging & Street Crimes – Incidents of armed robbery and murders are common.\n" +
                "• Gang Activities – Certain local gangs operate in specific parts of Mohammadpur, causing violence.\n" +
                "• Illegal Land Grabbing – Encroachment and disputes over land ownership have led to several conflicts.");
    }

    private static void addProgressBar(String areaKey, String label, int x, int y, int percentage, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, 700, 120);

        JLabel progressLabel = new JLabel(label);
        progressLabel.setBounds(0, 0, 200, 20);
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(progressLabel);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(percentage);
        progressBar.setString(percentage + "%");
        progressBar.setStringPainted(true);
        progressBar.setForeground(color);
        progressBar.setBounds(0, 25, 300, 25);
        panel.add(progressBar);

        JTextArea detailsArea = new JTextArea();
        detailsArea.setText(crimeDetails.get(areaKey));
        detailsArea.setEditable(false);
        detailsArea.setOpaque(false);
        detailsArea.setFont(new Font("Arial", Font.PLAIN, 12));
        detailsArea.setBounds(0, 60, 700, 60);
        panel.add(detailsArea);

        areaPanels.put(areaKey.toLowerCase(), panel);
    }

    private static void displayArea(String area) {
        String areaKey = area.toLowerCase();
        for (JPanel panel : areaPanels.values()) {
            layeredPane.remove(panel);
        }
        JPanel panel = areaPanels.get(areaKey);
        if (panel != null) {
            panel.setBounds(50, 100, 700, 150);
            layeredPane.add(panel, Integer.valueOf(1));
            layeredPane.revalidate();
            layeredPane.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "Area not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
