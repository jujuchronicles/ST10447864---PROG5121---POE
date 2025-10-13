/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package as1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Gamer
 */
public class MessagesTest {
    
    public MessagesTest() {
    }
    /**
     * Test of checkMessageID method, of class Messages.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        String idMessage = Messages.generateRandomId();
        boolean expResult = true;
        boolean result = Messages.checkMessageID(idMessage);
        assertEquals(expResult, result);
     
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRecipientCell method, of class Messages.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String number = "+2718693002";
        boolean expResult = true;
        boolean result = Messages.checkRecipientCell(number);
        assertEquals(expResult, result);
      
    }
    public void testCheckRecipientPhone() {
        System.out.println("checkRecipientCell");
        String number = "0857595889";
        boolean expResult = false;
        boolean result = Messages.checkRecipientCell(number);
        assertEquals(expResult, result);
    
    }

    /**
     * Test of checkMessage method, of class Messages.
     */
    @Test
    public void testCheckMessage() {
        System.out.println("checkMessage");
        String text = "Hi Mike, can you join us for Dinner Tonight";
        boolean expResult = true;
        boolean result = Messages.checkMessage(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateRandomId method, of class Messages.
     */
    @Test
    public void testGenerateRandomId() {
        System.out.println("generateRandomId");
        String expResult = "";
        String result = Messages.generateRandomId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sentMessage method, of class Messages.
     */
    @Test
    public void testSentMessage() {
        System.out.println("sentMessage");
        int option = 1;
        String expResult = "Sent";
        String result = Messages.sentMessage(option);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    public void testDeleteMessage() {
        System.out.println("sentMessage");
        int option = 0;
        String expResult = "Deleted";
        String result = Messages.sentMessage(option);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    public void testSaveMessage() {
        System.out.println("sentMessage");
        int option = 2;
        String expResult = "Saved";
        String result = Messages.sentMessage(option);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of FirstWord method, of class Messages.
     */
    @Test
    public void testFirstWord() {
        System.out.println("FirstWord");
        String text = "Hi Mike, can you join us for Dinner Tonight";
        String expResult = "HiTonight";
        String result = Messages.FirstLast(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of LastWord method, of class Messages.
     */
    @Test
    public void testLastWord() {
        System.out.println("LastWord");
        String text = "Hi Keagun, Did you get the Payment";
        String expResult = "HiPayment";
        String result = Messages.FirstLast(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class Messages.
     */
    @Test
    public void testPrint() {
        System.out.println("print");
        String text = "Hi Mike, can you join us for Dinner Tonight";
        String cell = "+2718693002";
        String ID = Messages.generateRandomId();
        String hash = "00:1:HITONIGHT";
        String choose = "Sent";
        
        int messageCounter = 1;
        Messages.print(text, cell, hash, choose, ID, messageCounter);
  
    }

    /**
     * Test of createHash method, of class Messages.
     */
    @Test
    public void testCreateHash() {
        System.out.println("createHash");
        String ID = Messages.generateRandomId();
        int messageCounter = 1;
        String text = "Hi Mike, can you join us for Dinner Tonight";
        String expResult = "00:1:HITONIGHT";
        String result = Messages.createHash(ID, messageCounter, text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
