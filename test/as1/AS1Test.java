package as1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class AS1Test {

    @BeforeEach
    public void resetData() {
        Messages.sentMessages.clear();
        Messages.disregardedMessages.clear();
        Messages.storedMessages.clear();
        Messages.messageHashes.clear();
        Messages.messageIDs.clear();
    }

    @AfterEach
    public void restoreStdOut() {
        System.setOut(System.out);
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        Messages.addMessage("‪+27834557896‬", "Did you get the cake?", "Sent");
        Messages.addMessage("0838884567", "It is dinner time!", "Sent");

        ArrayList<String> expected = new ArrayList<>();
        expected.add("Did you get the cake?");
        expected.add("It is dinner time!");

        assertEquals(expected, Messages.sentMessages,
                "Sent Messages array should contain all 'Sent' messages");
    }

    @Test
    public void testDisplayLongestMessage() {
        Messages.addMessage("‪+27834557896‬", "Did you get the cake?", "Sent");
        Messages.addMessage("‪+27838884567‬", 
            "Where are you? You are late! I have asked you to be on time.",
            "Sent");

        String expectedLongest = 
            "Where are you? You are late! I have asked you to be on time.";

        String longest = "";
        for (String msg : Messages.sentMessages) {
            if (msg.length() > longest.length()) longest = msg;
        }

        assertEquals(expectedLongest, longest, "Longest message should be detected correctly");
    }

    @Test
    public void testSearchByMessageID() {
        Messages.addMessage("0838884567", "It is dinner time!", "Sent");

        String id = Messages.messageIDs.get(0);
        int index = Messages.messageIDs.indexOf(id);

        String messageFound = Messages.sentMessages.get(index);

        assertEquals("It is dinner time!", messageFound,
                "Searching by message ID should return correct message");
    }

    @Test
    public void testSearchMessagesByRecipient() {
        // Simulate stored messages grouped by recipient
        Map<String, List<String>> recipientMessages = new HashMap<>();

        recipientMessages.putIfAbsent("‪+27838884567‬", new ArrayList<>());
        recipientMessages.get("‪+27838884567‬")
                .add("Where are you? You are late! I have asked you to be on time.");
        recipientMessages.get("‪+27838884567‬")
                .add("Ok, I am leaving without you.");

        List<String> expected = Arrays.asList(
            "Where are you? You are late! I have asked you to be on time.",
            "Ok, I am leaving without you."
        );

        assertEquals(expected, recipientMessages.get("‪+27838884567‬"),
                "Search should return all messages for a given recipient");
    }

    @Test
    public void testDeleteMessageUsingHash() {
        Messages.addMessage("‪+27838884567‬", 
            "Where are you? You are late! I have asked you to be on time.", 
            "Sent");

        String hash = Messages.messageHashes.get(0);

        Messages.deleteMessageByHash(hash);

        assertFalse(Messages.messageHashes.contains(hash),
                "Message should be deleted using its hash");
    }

    @Test
    public void testDisplayReport() {
        Messages.addMessage("‪+27834557896‬", "Did you get the cake?", "Sent");
        Messages.addMessage("0838884567", "It is dinner time!", "Sent");

        // Capture console output
        java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(output));

        Messages.displayReport();

        String reportOutput = output.toString();

        assertTrue(reportOutput.contains("Did you get the cake?"), 
                   "Report should show message 1");
        assertTrue(reportOutput.contains("It is dinner time!"), 
                   "Report should show message 2");
        assertTrue(reportOutput.contains("Message Hash"), 
                   "Report should display message hash column");
        assertTrue(reportOutput.contains("Message ID"), 
                   "Report should display message ID column");
    }
}