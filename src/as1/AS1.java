
package as1;
import java.util.Scanner;
//JSON File imports from chatgpt, Require more files downloaded to Work
/*import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;*/


public class AS1 {

    public static void main(String[] args) 
    {
       Scanner scan = new Scanner(System.in);
        String username, password, confirmUser, confirmPassword,phone;
        String text = "", ID, choose , cell = "", hash;
        int track=0,messageCounter=0, choice;
        
        System.out.print("Enter Userame: ");
        username = scan.nextLine();
        System.out.print("Enter Password: ");
        password = scan.nextLine();
      
       
        System.out.print("Enter phone number: ");
        phone = scan.nextLine();
        
        
        System.out.println(Login.registerUser(Login.checkUsername(username), Login.checkPassword(password)));
        if(Login.checkUsername(username) && Login.checkPassword(password)){
            System.out.println("Log in ");
 
            System.out.print("Username: ");
            confirmUser = scan.nextLine();
            System.out.print("Password: ");
            confirmPassword = scan.nextLine();
            String isLogged = Login.returnLoginStatus(Login.loginUser(username, password, confirmUser, confirmPassword));
            System.out.println(isLogged);
            if(isLogged.compareTo("Login attempt Successful")==0){
                 System.out.println("Welcome : QuickChatApp");
                 System.out.println("1. Send Message"); System.out.println("2. POE Part 3");System.out.println("3. Exist"); 
                 int quickChat = scan.nextInt();
                 switch(quickChat){
                     case 1:
                         while(quickChat != 111)   
                                       {
                                         System.out.println("How many messages you want? ");
                                         int many = scan.nextInt();
                                         for(int man=0;man<many;){
                                          System.out.println("Recipient Cell Number: ");
                                         cell = scan.next(); 
                                         scan.nextLine();
                                         System.out.println("Message: ");
                                         text = scan.nextLine();   
                                         
                                          
                                         System.out.println("1. Send"); 
                                         System.out.println("2. Save");
                                         System.out.println("3. Delete");  
                                         choice = scan.nextInt();  
                                         
                                         if(choice==1 ||choice ==2){
                                            
                                             ID= Messages.generateRandomId();
                                             
                                             if(Messages.checkMessageID(ID) && Messages.checkRecipientCell(cell) && Messages.checkMessage(text)){
                                                 hash = Messages.createHash(ID, track + 1, text);
                                                 if(choice == 1){
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
                                                 System.out.println("Message or Cell Number is not Valid"); 
                                             }
                                         }else{
                                             System.out.println("Deleted message"); 
                                         }   
                                     }
                                      
                                          System.out.println("1. Send More Messages"); System.out.println("111. Exit"); 
                                          quickChat = scan.nextInt();
                                       }
                    break;
                     case 2:
                         System.out.println("POE Part 3 activity, not Available");
                         break;
                     case 3:
                            System.exit(0);
                     break;
                 }
            }
            
        }
        
    }
}
