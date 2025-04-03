package org.example.api.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseAdminSingleton {

    private static FirebaseApp firebaseApp; // Singleton instance

    // Private constructor to prevent instantiation
    private FirebaseAdminSingleton() {}

    public static synchronized FirebaseApp getInstance() {
        if (firebaseApp == null) {
            try {
                FileInputStream serviceAccount = new FileInputStream("./secrets/distress-signal-software-firebase-adminsdk-fbsvc-11f33031b4.json");

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                firebaseApp = FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase initialized successfully!");

            } catch (IOException e) {
                throw new RuntimeException("❌ Failed to initialize Firebase", e);
            }
        }
        return firebaseApp;
    }
}
