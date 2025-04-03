package org.example.controllers;

import org.example.models.User;
import org.example.services.session.UserSession;
import org.example.api.brevo.BrevoAPI;
import org.example.api.Geomaps.IPLocationService;

import javax.swing.*;
import java.net.URLEncoder;
import java.util.List;

public class HomeController {

    public void sendDistress() {
        User user = UserSession.getLoggedInUser();

        if (user == null) {
            JOptionPane.showMessageDialog(null, "No user logged in!");
            return;
        }

        List<String> emergencyNumbers = user.getEmergencyNumbers();
        List<String> emergencyMails = user.getEmergencyMails();

        // Building the message content with emergency numbers included
        StringBuilder messageContent = new StringBuilder();
        messageContent.append("<strong>🚨 Distress Signal from ").append(user.getName()).append(" 🚨</strong><br>")
                .append("<strong>Phone:</strong> ").append(user.getPhoneNumber()).append("<br>")
                .append("<strong>Blood Group:</strong> ").append(user.getBloodGroup()).append("<br>")
                .append("<strong>Age:</strong> ").append(user.getAge()).append("<br>")
                .append("<strong>Sex:</strong> ").append(user.getSex()).append("<br>")
                .append("<br><strong>📞 Emergency Contacts:</strong>");

        for (String number : emergencyNumbers) {
            messageContent.append("<br> - ").append(number);
        }

        // Get the location link using IPLocationService
        String locationLink = IPLocationService.getLocationLink();

        if (locationLink != null && !locationLink.isEmpty()) {
            try {
                


                // Append the location link properly formatted as HTML
                messageContent.append("<br><strong>📍 Location: </strong><p>").append(locationLink).append("</p>");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Sending distress emails
        for (String email : emergencyMails) {
            try {
                BrevoAPI.sendDistressSignal(email, messageContent.toString());
                System.out.println("Distress signal sent to email: " + email);
                Thread.sleep(3000);  // Delay to avoid API overload
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        JOptionPane.showMessageDialog(null, "Distress signals sent to all contacts!");
    }
}
