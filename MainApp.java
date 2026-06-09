/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatappproject;

/**
 *
 * @author Student
 */
import java.util.Scanner;


public class MainApp {
    
      public static void main(String[]args){
        
        Scanner input = new Scanner(System.in);
        
        Login login = new Login();
      
        System.out.println("=== USER REGISTRATION===");
        
        System.out.println("Enter a username: ");
        String username = input.nextLine();
        
        System.out.println("Enter a password: ");
        String password = input.nextLine();
        
        System.out.println("Enter your South Africa phone number(+27...): ");
        String phoneNumber = input.nextLine();
        
        String response = login.registerUser(username, password, phoneNumber);
        
        System.out.println(response);
        
        System.out.println("\n=== USER LOGIN ===");
      
        System.out.println("Enter your phonenumber ");
        String loginPhone = input.nextLine();
        
        System.out.println("Enter your password ");
        String loginPassword = input.nextLine();
        
         boolean loggedIn = login.loginUser(loginPhone, loginPassword);
        
         String loginMessage = login.returnLoginStatus(loggedIn );
        System.out.println(loginMessage);
        
         System.out.println("\n=== MESSAGING ===");
 
        boolean keepMessaging = true;
        int messageNumber = 1;
 
        while (keepMessaging) {
            System.out.println("\n--- Message " + messageNumber + " ---");
 
            Message message = new Message(messageNumber);
 
            // Get recipient
            System.out.println("Enter recipient cell number (+27...): ");
            String recipient = input.nextLine();
            String recipientCheck = message.checkRecipientCell(recipient);
            System.out.println(recipientCheck);
 
            if (!recipientCheck.equals("Cell number successfully captured.")) {
                System.out.println("Skipping message due to invalid recipient.");
                messageNumber++;
                continue;
            }
            message.setRecipient(recipient);
 
            // Get message text
            System.out.println("Enter your message (max 250 characters): ");
            String messageText = input.nextLine();
            String lengthCheck = message.checkMessageLength(messageText);
            System.out.println(lengthCheck);
 
            if (!lengthCheck.equals("Message ready to send.")) {
                System.out.println("Skipping message due to length issue.");
                messageNumber++;
                continue;
            }
            message.setMessageText(messageText);
 
            // Generate and display hash
            String hash = message.createMessageHash();
            System.out.println("Message Hash: " + hash);
            System.out.println("Message ID:   " + message.getMessageID());
 
            // Send, disregard, or store
            System.out.println("\nWhat would you like to do?");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message");
            System.out.print("Enter choice: ");
            String choice = input.nextLine();
 
            switch (choice) {
                case "1":
                    System.out.println("Message successfully sent.");
                    break;
                case "2":
                    System.out.println("Press 0 to delete the message.");
                    break;
                case "3":
                    System.out.println("Message successfully stored.");
                    break;
                default:
                    System.out.println("Invalid option selected.");
            }
 
            // Ask if they want to send another message
            System.out.println("\nWould you like to send another message? (yes/no): ");
            String another = input.nextLine();
            if (!another.equalsIgnoreCase("yes")) {
                keepMessaging = false;
            }
 
            messageNumber++;
        }
 
        System.out.println("\nThank you for using ChatApp. Goodbye!");
        
        }
    
}
