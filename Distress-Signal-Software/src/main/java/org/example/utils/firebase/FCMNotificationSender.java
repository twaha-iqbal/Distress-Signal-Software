package org.example.utils.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.FirebaseMessagingException;

public class FCMNotificationSender {

    public static void sendNotification() {
        String deviceToken = "mock_device_token_12345";
        // Replace with actual device token

        // Create a notification
        Notification notification = Notification.builder()
                .setTitle("Test Notification")
                .setBody("This is a sample push notification from FCM.")
                .build();

        // Create a message with the notification
        Message message = Message.builder()
                .setToken(deviceToken)
                .setNotification(notification)
                .build();

        try {
            // Send the notification via Firebase Messaging
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Notification sent successfully: " + response);
        } catch (FirebaseMessagingException e) {
            System.err.println("Error sending FCM notification: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Ensure Firebase is initialized before sending notifications
        FirebaseAdminSingleton.getInstance(); // Call the Singleton Firebase initializer
        sendNotification();
    }
}

