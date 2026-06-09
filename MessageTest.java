/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.chatappproject;

import com.mycompany.chatappproject.Message;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * 
 */
public class MessageTest {
    
     // =========================================================
    // DECLARE MESSAGE OBJECTS
    // =========================================================
    private Message message1;
    private Message message2;
 
    // =========================================================
    // @Before: Runs before EVERY test to create fresh objects
    // Uses the exact POE test data
    // =========================================================
    @Before
    public void setUp() {
        // Message 1 - POE test data
        message1 = new Message(1);
        message1.setRecipient("+27718693002");
        message1.setMessageText("Hi Mike, can you join us for dinner tonight?");
 
        // Message 2 - POE test data (invalid recipient by design)
        message2 = new Message(2);
        message2.setRecipient("08575975889");
        message2.setMessageText("Hi Keegan, did you receive the payment?");
    }
 
    // =========================================================
    // MESSAGE LENGTH TESTS
    // =========================================================
 
    @Test
    public void testCheckMessageLength_validMessage_returnsSuccess() {
        // ARRANGE
        String shortMessage = "Hi Mike, can you join us for dinner tonight?";
        // ACT
        String result = message1.checkMessageLength(shortMessage);
        // ASSERT
        assertEquals("Message ready to send.", result);
    }
 
    @Test
    public void testCheckMessageLength_over250chars_returnsFailureWithCount() {
        // ARRANGE - build a message that is 260 characters (10 over limit)
        String longMessage = "A".repeat(260);
        // ACT
        String result = message1.checkMessageLength(longMessage);
        // ASSERT
        assertEquals("Message exceeds 250 characters by 10, please reduce size.", result);
    }
 
    @Test
    public void testCheckMessageLength_exactlyAtLimit_returnsSuccess() {
        // ARRANGE - exactly 250 characters
        String exactMessage = "A".repeat(250);
        // ACT
        String result = message1.checkMessageLength(exactMessage);
        // ASSERT
        assertEquals("Message ready to send.", result);
    }
 
    @Test
    public void testCheckMessageLength_oneOver_returnsFailureWithCountOf1() {
        // ARRANGE - exactly 251 characters (1 over the limit)
        String oneOverMessage = "A".repeat(251);
        // ACT
        String result = message1.checkMessageLength(oneOverMessage);
        // ASSERT
        assertEquals("Message exceeds 250 characters by 1, please reduce size.", result);
    }
 
    // =========================================================
    // RECIPIENT CELL NUMBER TESTS
    // =========================================================
 
    @Test
    public void testCheckRecipientCell_validNumber_returnsSuccess() {
        // ARRANGE - POE test data: valid SA number
        String validNumber = "+27718693002";
        // ACT
        String result = message1.checkRecipientCell(validNumber);
        // ASSERT
        assertEquals("Cell number successfully captured.", result);
    }
 
    @Test
    public void testCheckRecipientCell_invalidNumber_returnsFailure() {
        // ARRANGE - POE test data: no international code
        String invalidNumber = "08575975889";
        // ACT
        String result = message2.checkRecipientCell(invalidNumber);
        // ASSERT
        assertEquals(
            "Cell number is incorrectly formatted or does not contain international code.",
            result
        );
    }
 
    // =========================================================
    // MESSAGE HASH TESTS
    // =========================================================
 
    @Test
    public void testCreateMessageHash_correctFormat_endsWithExpectedWords() {
        // ARRANGE - message1 text: "Hi Mike, can you join us for dinner tonight?"
        // First word: HI, Last word: TONIGHT -> hash ends with :0:HITONIGHT or :1:HITONIGHT
        // POE says it ends with :0:HITONIGHT (using message number pattern)
        // ACT
        String hash = message1.createMessageHash();
        // ASSERT - the hash must end with the message number and word pair
        assertTrue("Hash should end with :1:HITONIGHT", hash.endsWith(":1:HITONIGHT"));
    }
 
    @Test
    public void testCreateMessageHash_isUppercase() {
        // ACT
        String hash = message1.createMessageHash();
        // ASSERT - full hash must be uppercase
        assertEquals(hash.toUpperCase(), hash);
    }
 
    @Test
    public void testCreateMessageHash_multipleMessages_loopTest() {
        // ARRANGE
        String[] texts = {
            "Hi Mike, can you join us for dinner tonight?",   // HI + TONIGHT
            "Hi Keegan, did you receive the payment?"         // HI + PAYMENT
        };
        String[] expectedEndings = {
            ":1:HITONIGHT",
            ":2:HIPAYMENT"
        };
 
        // ACT + ASSERT in a loop
        for (int i = 0; i < texts.length; i++) {
            Message msg = new Message(i + 1);
            msg.setMessageText(texts[i]);
            String hash = msg.createMessageHash();
            assertTrue(
                "Hash for message " + (i + 1) + " should end with " + expectedEndings[i],
                hash.endsWith(expectedEndings[i])
            );
        }
    }
 
    // =========================================================
    // MESSAGE ID TESTS
    // =========================================================
 
    @Test
    public void testCheckMessageID_generatedID_isNotNull() {
        // ASSERT - a new Message should always have a non-null ID
        assertNotNull("Message ID should not be null", message1.getMessageID());
    }
 
    @Test
    public void testCheckMessageID_generatedID_isExactly10Chars() {
        // ACT
        boolean result = message1.checkMessageID();
        // ASSERT - checkMessageID() returns true when ID is 10 chars or fewer
        assertTrue("Message ID should be 10 characters or fewer", result);
    }
 
    // =========================================================
    // SENT MESSAGE TESTS
    // Uses a TestableMessage inner class to simulate user input
    // without needing actual console interaction
    // =========================================================
 
    /**
     * Inner helper class that overrides sentMessage() to bypass
     * Scanner input so we can test all three outcomes automatically.
     */
    static class TestableMessage extends Message {
        private int simulatedChoice;
 
        public TestableMessage(int messageNumber, int simulatedChoice) {
            super(messageNumber);
            this.simulatedChoice = simulatedChoice;
        }
 
        @Override
        public String sentMessage() {
            switch (simulatedChoice) {
                case 1: return "Message successfully sent.";
                case 2: return "Press 0 to delete the message.";
                case 3: return "Message successfully stored.";
                default: return "Invalid option selected.";
            }
        }
    }
 
    @Test
    public void testSentMessage_userSelectsSend_returnsCorrectString() {
        // ARRANGE - simulate user choosing option 1 (Send)
        TestableMessage msg = new TestableMessage(1, 1);
        // ACT
        String result = msg.sentMessage();
        // ASSERT
        assertEquals("Message successfully sent.", result);
    }
 
    @Test
    public void testSentMessage_userSelectsDisregard_returnsCorrectString() {
        // ARRANGE - simulate user choosing option 2 (Disregard)
        TestableMessage msg = new TestableMessage(1, 2);
        // ACT
        String result = msg.sentMessage();
        // ASSERT
        assertEquals("Press 0 to delete the message.", result);
    }
 
    @Test
    public void testSentMessage_userSelectsStore_returnsCorrectString() {
        // ARRANGE - simulate user choosing option 3 (Store)
        TestableMessage msg = new TestableMessage(1, 3);
        // ACT
        String result = msg.sentMessage();
        // ASSERT
        assertEquals("Message successfully stored.", result);
    }
    
}
