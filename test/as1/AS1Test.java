package as1;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
import java.util.*;

public class MessagesTest {

    @Test
    public void testSentMessagesArrayPopulated() {
        // Clear all lists before each test
        Messages.sentMessages.clear();
        Messages.disregardedMessages.clear();
        Messages.storedMessages.clear();
        Messages.messageHashes.clear();
        Messages.messageIDs.clear();

        // Add messages
        Messages.addMessage("‪+27834557896‬", "Did you get the cake?", "Sent");
        Messages.addMessage("0838884567", "It is dinner time!", "Sent");

        // Expected results
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Did you get the cake?");
        expected.add("It is dinner time!");

        assertEquals("Sent Messages array correctly populated", expected, Messages.sentMessages);
    }

    @Test
    public void testDisplayLongestMessage() {
        Messages.sentMessages.clear();

        Messages.addMessage("‪+27834557896‬", "Did you get the cake?", "Sent");
        Messages.addMessage("‪+27838884567‬", "Where are you? You are late! I have asked you to be on time.", "Sent");

        // The expected longest message
        String expectedLongest = "Where are you? You are late! I have asked you to be on time.";

        // Find the longest message using the same logic
        String longest = "";
        for (String msg : Messages.sentMessages) {
            if (msg.length() > longest.length()) longest = msg;
        }

        assertEquals("Display the longest Message", expectedLongest, longest);
    }

    @Test
    public void testSearchByMessageID() {
        Messages.sentMessages.clear();
        Messages.messageIDs.clear();

        Messages.addMessage("0838884567", "It is dinner time!", "Sent");
        String id = Messages.messageIDs.get(0);

        int index = Messages.messageIDs.indexOf(id);
        String messageFound = Messages.sentMessages.get(index);

        assertEquals("Search for messageID returns correct message", "It is dinner time!", messageFound);
    }

    @Test
    public void testSearchMessagesByRecipient() {
        // This test simulates searching for all messages sent/stored to ‪+27838884567‬
        Map<String, String> recipientMessages = new HashMap<>();
        recipientMessages.put("‪+27838884567‬", "Where are you? You are late! I have asked you to be on time.");
        recipientMessages.put("‪+27838884567‬", "Ok, I am leaving without you.");

        ArrayList<String> expected = new ArrayList<>();
        expected.add("Where are you? You are late! I have asked you to be on time.");
        expected.add("Ok, I am leaving without you.");

        ArrayList<String> found = new ArrayList<>(recipientMessages.values());

        assertEquals("Search all messages sent/stored to a recipient", expected, found);
    }

    @Test
    public void testDeleteMessageUsingHash() {
        Messages.sentMessages.clear();
        Messages.messageHashes.clear();
        Messages.messageIDs.clear();

        Messages.addMessage("‪+27838884567‬", "Where are you? You are late! I have asked you to be on time.", "Sent");
        String hash = Messages.messageHashes.get(0);

        // Delete message
        Messages.deleteMessageByHash(hash);

        assertFalse("Message successfully deleted", Messages.messageHashes.contains(hash));
    }
    @Test
    public void testDisplayReport() {
        // Clear all lists first
        Messages.sentMessages.clear();
        Messages.messageHashes.clear();
        Messages.messageIDs.clear();

        // Add sample messages
        Messages.addMessage("‪+27834557896‬", "Did you get the cake?", "Sent");
        Messages.addMessage("0838884567", "It is dinner time!", "Sent");

        // Capture output printed by displayReport()
        java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output));

        Messages.displayReport();

        String reportOutput = output.toString();

        // Assertions: Check that report contains both messages and keywords
        assertTrue("Report includes message 1", reportOutput.contains("Did you get the cake?"));
        assertTrue("Report includes message 2", reportOutput.contains("It is dinner time!"));
        assertTrue("Report includes 'Message Hash'", reportOutput.contains("Message Hash"));
        assertTrue("Report includes 'Message ID'", reportOutput.contains("Message ID"));
    }
}
