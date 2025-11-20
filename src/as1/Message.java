/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package as1;

/**
 *
 * @author Gamer
 */
public class Message {
        private static int messageCount = 0;  // Tracks how many messages have been created

    private String messageID;
    private String text;
    private String cell;
    private String hash;
    private String status;

    // ✅ Constructor
    public Message(String text,String cell,String hash,String status,String ID, int messageCounter) {
        this.text = text;
        this.status = status;
        this.status = hash;
        this.text = cell;
        this.status = ID;
        this.messageCount++;
        
    }

    // ✅ Getters
    public String getMessageID() { return messageID; }
    public String getText() { return text; }
    public String getHash() { return hash; }
    public String getStatus() { return status; }
    public static int getMessageCount() { return messageCount; }

    // ✅ Setters
    public void setText(String text, String messageID, int messageCount, String hash) { 
        this.text = text; 
        this.messageID = messageID; 
        Message.this.messageCount = messageCount; 
        this.hash=hash; // re-generate hash if text changes
    }

    public void setStatus(String status) { this.status = status; }

  

   
}


