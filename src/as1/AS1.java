
package as1;
import javax.swing.JOptionPane;

//JSON File imports from chatgpt, Require more files downloaded to Work
/*import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;*/


public class AS1 {

    public static void main(String[] args) 
    {
       
        String username, password, confirmUser, confirmPassword,phone;
        String text = "", ID, choose , cell = "", hash;
        int track=0,messageCounter=0, choice;
        
        
        username = JOptionPane.showInputDialog("Enter Userame:");
        password = JOptionPane.showInputDialog("Enter Password:");
      
       
       
        phone = JOptionPane.showInputDialog("Enter phone number:");
        
        
        System.out.println(Login.registerUser(Login.checkUsername(username), Login.checkPassword(password)));
        if(Login.checkUsername(username) && Login.checkPassword(password)){
            System.out.println("Log in ");
 
            confirmUser = JOptionPane.showInputDialog("Userame:");
            confirmPassword = JOptionPane.showInputDialog("Password:");
            
            String isLogged = Login.returnLoginStatus(Login.loginUser(username, password, confirmUser, confirmPassword));
            JOptionPane.showMessageDialog(null, isLogged);

            if(isLogged.compareTo("Login attempt Successful")==0){
                JOptionPane.showMessageDialog(null, "Welcome : QuickChatApp");
                String[] options = {"Send Message", "POE Part 3", " Exit"};
                
                 int quickChat = JOptionPane.showOptionDialog( null,"Choose an option:", "Custom Dialog", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);

                 switch(quickChat){
                     case 0:
                         while(quickChat != 1)   
                                       {  
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
                                                /*List<Map<String, Object>> messages = new ArrayList<>();

                                                          Map<String, Object> msg1 = new LinkedHashMap<>();
                                                          msg1.put("ID",ID);
                                                          msg1.put("message", text);
                                                          msg1.put("recipient", cell);
                                                          msg1.put("hash", hash);
                                                          msg1.put("number", messageCounter);
                                                          msg1.put("status", choose);
                                                          messages.add(msg1);
                                                          
                                                          
                                                          ObjectMapper mapper = new ObjectMapper();
                                                           try 
                                                           {
                                                            mapper.writeValue(new File("messages.json"), messages);
                                                            System.out.println("✅ Messages saved to messages.json");
                                                            } 
                                                           catch (IOException e) {e.printStackTrace();} */    
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
                          JOptionPane.showMessageDialog(null, "POE Part 3 activity, not Available"); 
                         break;
                     case 2:
                            JOptionPane.showMessageDialog(null, "Thank you"); 
                            System.exit(0);
                     break;
                 }
            }
            
        }
        
    }
}
