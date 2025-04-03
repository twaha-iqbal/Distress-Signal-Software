package org.example.api.Geomaps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IPLocationService {

    // Static method to get location and return the Google Maps link
    public static String getLocationLink() {
        try {
            // Step 1: Send a GET request to the ip-api service to get the location based on IP
            URL url = new URL("http://ip-api.com/json/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Step 2: Read the response from the API
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // Step 3: Parse the JSON response using GSON
            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
            String lat = json.get("lat").getAsString();  // Extract latitude
            String lon = json.get("lon").getAsString();  // Extract longitude

            // Step 4: Return the raw Google Maps link without encoding
            return "https://www.google.com/maps?q=" + lat + "," + lon;

        } catch (Exception e) {
            e.printStackTrace();  // Print error if something goes wrong
            return null;
        }
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        // Get the Google Maps link and print it
        String locationLink = getLocationLink();
        if (locationLink != null) {
            System.out.println("Your location on Google Maps: " + locationLink);
        } else {
            System.out.println("Unable to retrieve location.");
        }
    }
}
