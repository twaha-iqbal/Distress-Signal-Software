package org.example.utils.brevo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BrevoAPI {
    private static final String API_KEY = "ENE DIBAA";  // Replace with your Brevo API key
    private static final String API_URL = "https://api.brevo.com/v3/smtp/email";

    public static void sendDistressSignal(String recipientEmail, String messageContent) {
        try {
            // Set up the URL and connection
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set HTTP method to POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("api-key", API_KEY);
            connection.setDoOutput(true);

            // Create the message JSON object
            String jsonMessage = "{"
                    + "\"sender\": {\"email\": \"kmosabbir@gmail.com\"},"
                    + "\"to\": [{\"email\": \"" + recipientEmail + "\"}],"
                    + "\"subject\": \"Distress Signal\","
                    + "\"htmlContent\": \"" + messageContent + "\""
                    + "}";

            // Send the request
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonMessage.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response
            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Check the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Distress signal sent successfully!");
                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("Failed to send distress signal. Response code: " + responseCode);
                System.out.println("Response: " + response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test data
        String recipientEmail = "nabilrafi112@gmail.com";
        String messageContent = "why are u gay";

        // Send distress signal
        sendDistressSignal(recipientEmail, messageContent);
    }
}
