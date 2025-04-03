package org.example.api.brevo;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BrevoAPI {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String API_KEY = dotenv.get("BREVO_API_KEY");
    private static final String sender = "kmosabbir@gmail.com";
    private static final String API_URL = "https://api.brevo.com/v3/smtp/email";




    public static void sendDistressSignal(String recipientEmail, String messageContent) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("api-key", API_KEY);
            connection.setDoOutput(true);

            String jsonMessage = "{"
                    + "\"sender\": {\"email\": \"kmosabbir@gmail.com\"},"
                    + "\"to\": [{\"email\": \"" + recipientEmail + "\"}],"
                    + "\"subject\": \"Distress Signal\","
                    + "\"htmlContent\": \"" + messageContent + "\""
                    + "}";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonMessage.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

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


//    public static void main(String[] args) {
//        String recipientEmail = "kmosabbir@gmail.com";
//        String messageContent = "This is a test distress signal.";
//
//        sendDistressSignal(recipientEmail, messageContent);
//    }
}