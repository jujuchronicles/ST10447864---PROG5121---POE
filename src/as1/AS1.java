
package as1;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;



public class AS1 {

    public static void main(String[] args) 
    {
        
        String username, password, confirmUser, confirmPassword,phone;
        String text = "", ID, choose , cell = "", hash;
        int track=0,messageCounter=0, choice;
          String filepath = "messages.json";
          ArrayList<String> textList = new ArrayList<>();
          ArrayList<String> idList = new ArrayList<>();
          ArrayList<String> chooseList = new ArrayList<>();
          ArrayList<String> cellList = new ArrayList<>();
          ArrayList<String> hashList = new ArrayList<>();
          ArrayList<Integer> counterList = new ArrayList<>();
          
        
        username = JOptionPane.showInputDialog("Enter Userame:");
        password = JOptionPane.showInputDialog("Enter Password:");
      
       
       
        phone = JOptionPane.showInputDialog("Enter phone number:");
        if(!Login.isValidCellPhone(phone)){
          JOptionPane.showMessageDialog(null, "Cell Number is not Format Correct");
          System.exit(0);
          }
        
        // Login If register success
        if(Login.checkUsername(username) && Login.checkPassword(password)){
            JOptionPane.showMessageDialog(null, "Log in ");
 
            confirmUser = JOptionPane.showInputDialog("Userame:");
            confirmPassword = JOptionPane.showInputDialog("Password:");
            
            String isLogged = Login.returnLoginStatus(Login.loginUser(username, password, confirmUser, confirmPassword));
            JOptionPane.showMessageDialog(null, isLogged);

            if(isLogged.compareTo("Login attempt Successful")==0){
                JOptionPane.showMessageDialog(null, "Welcome : QuickChatApp");
                String[] options = {"Send Message", "Report", " Exit"};
                
                 int quickChat = JOptionPane.showOptionDialog( null,"Choose an option:", "Custom Dialog", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
                 while(quickChat!=2){
                 switch(quickChat){
                     case 0:
                         while(quickChat != 1)   
                                       {  // Create messages
                                         int many = Integer.parseInt(JOptionPane.showInputDialog(null, "How many messages you want?"));
                                         for(int man=0;man<many;){
                                                 cell = JOptionPane.showInputDialog("Recipient Cell Number:"); 
                                                text = JOptionPane.showInputDialog("Enter Message:");  
                                         
                                          String[] options2 = {"Send", "Save", " Delete"};
                                         choice = JOptionPane.showOptionDialog( null,"Choose an option:", "Custom Dialog", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options2,options2[0]);  
                                         
                                         if(choice==0 ||choice ==1){
                                            
                                             ID= Messages.generateRandomId();
                                             
                                             if(Messages.checkMessageID(ID) && Messages.checkRecipientCell(cell) && Messages.checkMessage(text)){
                                                 hash = Messages.createHash(ID, track + 1, text);
                                                 if(choice == 0){
                                                     choose = "Sent Message";
                                                 }
                                                 else{
                                                     choose = "Saved Message";
                                                 }
                                                 messageCounter = messageCounter +1;
                                                 Messages.returnTotalMessages(messageCounter);
                                                Messages.print(text, cell, hash, choose, ID, messageCounter);
                                                man = man + 1;
                                              //JSON File COde, Gives Error (due to missing files) so Saved as a Comment
                                             ArrayList<Message> messages = new ArrayList<>();

                                          // Create several Message objects
                                            Message msg1 = new Message(text, cell, hash, choose, ID, messageCounter);
                                           
                                            // Save messages to JSON
                                            AS1.writeMessagesToJSON(filepath, messages);

                                            // Read messages back from JSON
                                             readStoredMessagesFromJSON(filepath);

                                             //Array
                                             textList.add(text);
                                             idList.add(ID);
                                             chooseList.add(choose);
                                             cellList.add(cell);
                                             hashList.add(hash);
                                             counterList.add(messageCounter);
                                             }
                                             else{
                                                 JOptionPane.showMessageDialog(null, "Message or Cell Number is not Valid");
                                             }
                                         }else{
                                             JOptionPane.showMessageDialog(null, "Deleted message");
                                              
                                         }   
                                     }
                                      
                                          String[] options3 = {"Send More Messages", " Exit"};
                
                                         quickChat = JOptionPane.showOptionDialog( null,"Choose an option:", "Custom Dialog", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options3,options3[0]);


                                       }
                    break;
                     case 1:
                         //Part 3
                          String[] optionsP3 = {"Display Recipients", "Longest Message", " Search ID", "Search Messages", "Delete Message (Via HAsh)", "Report", "Exit"};
                          
                          int report = JOptionPane.showOptionDialog( null,"Choose an option:", "Custom Dialog", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,optionsP3,optionsP3[0]);
                          while(report!=6){
                            switch(report){
                                // Showing all Recipient and Senders
                              case 0:
                               JOptionPane.showMessageDialog(null, "Showing all Recipient"); 
                               for(int count=0;count< cellList.size();count+=1){
                                   JOptionPane.showMessageDialog(null, "Sender : "+ null/*username*/+"\n Cell : " + cellList.get(count)); 
                               }  
                              break;
                              // Longest Message
                               case 1:
                                   int longest =0;int temporary=0;
                                 for(int count=0;count< cellList.size();count+=1){
                                   if(textList.get(count).length()>longest){
                                       temporary = count;
                                       longest=textList.get(count).length();
                                   } 
                               }    
                                  JOptionPane.showMessageDialog(null, "Longest Message : "+ textList.get(temporary)); 
                              break;
                              // Search ID
                               case 2:
                                  int find = idList.indexOf(JOptionPane.showInputDialog("Search ID: "));
                                  if(find>=0){
                                    JOptionPane.showMessageDialog(null, "Recipient: "+ cellList.get(find) + "\n Message: "+ textList.get(find));   
                                  }
                                  else{
                                      JOptionPane.showMessageDialog(null, "None Found"); 
                                  }
                                  JOptionPane.showMessageDialog(null, "Search Finished");
                              break;
                             // Search Cell Number
                               case 3:
                                   String findR = JOptionPane.showInputDialog("Search Recipient Cell Number: ") ;
                                    for(int counter=0;counter< cellList.size();counter+=1){
                                   if(cellList.get(counter).compareTo(findR)==0){
                                       JOptionPane.showMessageDialog(null, "Message : "+ textList.get(counter)); 
                                   } 
                               } 
                                     JOptionPane.showMessageDialog(null, "Search Finished");
                              break; // Search HASH to Delete
                               case 4:
                                    int findHash = hashList.indexOf(JOptionPane.showInputDialog("Search Hash: "));
                                     if(findHash>=0){
                                               textList.remove(findHash);
                                             idList.remove(findHash);
                                             chooseList.remove(findHash);
                                             cellList.remove(findHash);
                                             hashList.remove(findHash);
                                             counterList.remove(findHash);
                                             JOptionPane.showMessageDialog(null, "Item Deleted");
                                     }
                                  else{
                                      JOptionPane.showMessageDialog(null, "No Result Found for Hash"); 
                                  }
                                  JOptionPane.showMessageDialog(null, "Search Finished");
                              break;// SHow all message detais
                               case 5:
                                   JOptionPane.showMessageDialog(null, "Message Report");
                                    for(int counter=0;counter< cellList.size();counter+=1){
                                   Messages.print(textList.get(counter), cellList.get(counter), hashList.get(counter), chooseList.get(counter), idList.get(counter), counterList.get(counter).intValue());
                               } JOptionPane.showMessageDialog(null, "Report Finished");
                              break;
                              // Exit Report Center
                               case 6:
                                   JOptionPane.showMessageDialog(null, "Exiting"); 
                              break;
                              
                          }
                           report = JOptionPane.showOptionDialog( null,"Choose an option:", "Custom Dialog", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,optionsP3,optionsP3[0]);
                           }
                         break;
                         // Exit Program
                     case 2:
                            JOptionPane.showMessageDialog(null, "Bye"); 
                            System.exit(0);
                     break;
                     
                 }
                 quickChat = JOptionPane.showOptionDialog( null,"Choose an option:", "Custom Dialog", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
            }
    }}}
           // ✅ WRITE messages to JSON file
    public static void writeMessagesToJSON(String filepath, ArrayList<Message> messages) {
        JSONArray jsonArray = new JSONArray();

        for (Message m : messages) {
            JSONObject obj = new JSONObject();
            obj.put("messageID", m.getMessageID());
            obj.put("text", m.getText());
            obj.put("status", m.getStatus());
            obj.put("hash", m.getHash());
            jsonArray.add(obj);
        }

        try (FileWriter file = new FileWriter(filepath)) {
            file.write(jsonArray.toJSONString());
            file.flush();
            JOptionPane.showMessageDialog(null,"Messages successfully saved to " + filepath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error writing to JSON file: " + e.getMessage());
        }
    }

    // ✅ READ messages from JSON file
    public static void readStoredMessagesFromJSON(String filepath) {
        try {
            FileReader reader = new FileReader(filepath);
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            System.out.println("\n=== Messages Read from JSON ===");
            for (Object obj : jsonArray) {
                JSONObject messageObj = (JSONObject) obj;
                JOptionPane.showMessageDialog(null, "---------------------------" );
               JOptionPane.showMessageDialog(null, "Message ID: " + messageObj.get("messageID"));
               JOptionPane.showMessageDialog(null,"Text: " + messageObj.get("text"));
               JOptionPane.showMessageDialog(null, "Status: " + messageObj.get("status"));
               JOptionPane.showMessageDialog(null, "Hash: " + messageObj.get("hash"));
               
            }

            reader.close();
        } catch (Exception e) {
            System.out.println(" Error reading JSON file: " + e.getMessage());
        }
    }

//}
 
        }
        
    //}

