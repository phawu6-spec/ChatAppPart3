/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatappproject;

import java.util.Random;
import java.util.Scanner;
 
/**
 * Message class for ChatApp Part 2.
 * Handles message creation, validation, hashing, and sending.
 */
public class Message {
 
    // =========================================================
    // FIELDS
    // =========================================================
    private String messageID;       // 10-digit randomly generated ID
    private int messageNumber;      // position of the message in the session
    private String recipient;       // recipient's cell number
    private String messageText;     // the actual message content
    private String messageHash;     // generated hash for this message
 
    // Reuse the Login validator for phone number checking
    private Login loginValidator = new Login();
 
    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public Message(int messageNumber) {
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
    }
 
    // =========================================================
    // GENERATE A RANDOM 10-CHARACTER MESSAGE ID
    // =========================================================
    private String generateMessageID() {
        Random random = new Random();
        long id = (long) (random.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }
 
    // =========================================================
    // CHECK MESSAGE LENGTH
    // - Under or equal to 250 characters: return success message
    // - Over 250 characters: return failure message with count
    // =========================================================
    public String checkMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = message.length() - 250;
            return "Message exceeds 250 characters by " + over
                    + ", please reduce size.";
        }
    }
 
    // =========================================================
    // CHECK RECIPIENT CELL NUMBER
    // Reuses Login.checkCellPhoneNumber() logic
    // =========================================================
    public String checkRecipientCell(String cellNumber) {
        if (loginValidator.checkCellphoneNumberValidity(cellNumber)) {
            return "Cell number successfully captured.";
        } else {
            return "Cell number is incorrectly formatted or does not contain international code.";
        }
    }
 
    // =========================================================
    // CREATE MESSAGE HASH
    // Format: <first 2 chars of ID>:<messageNumber>:<FIRSTWORD><LASTWORD>
    // All uppercase
    // =========================================================
    public String createMessageHash() {
        // Get the first and last words of the message text
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
 
        // Remove any punctuation from first/last word
        firstWord = firstWord.replaceAll("[^a-zA-Z0-9]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");
 
        // Build hash: first 2 chars of ID + : + messageNumber + : + FIRSTWORD+LASTWORD
        String hash = messageID.substring(0, 2)
                + ":" + messageNumber
                + ":" + firstWord + lastWord;
 
        this.messageHash = hash.toUpperCase();
        return this.messageHash;
    }
 
    // =========================================================
    // CHECK MESSAGE ID
    // Returns true if the ID is 10 characters or fewer
    // =========================================================
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }
 
    // =========================================================
    // SENT MESSAGE
    // Asks the user what to do with the message
    // Returns a String based on the user's choice
    // =========================================================
    public String sentMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to do with the message?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message");
        System.out.print("Enter choice: ");
 
        int choice = scanner.nextInt();
 
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid option selected.";
        }
    }
 
    // =========================================================
    // GETTERS AND SETTERS
    // =========================================================
    public String getMessageID() {
        return messageID;
    }
 
    public int getMessageNumber() {
        return messageNumber;
    }
 
    public String getRecipient() {
        return recipient;
    }
 
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
 
    public String getMessageText() {
        return messageText;
    }
 
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
 
    public String getMessageHash() {
        return messageHash;
    }
}
 
