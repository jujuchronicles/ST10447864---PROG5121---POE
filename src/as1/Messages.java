/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package as1;

import java.util.regex.Pattern;
import java.util.Random;

/**
 *
 * @author Gamer
 */
public class Messages {
     public static  boolean checkMessageID(String idMessage)
    { return idMessage.length()<= 10;}
     public static boolean checkRecipientCell(String number) {
        String regex = "^\\+\\d{1,3}\\d{1,10}$";
        return Pattern.matches(regex, number)&& number.length() <= 13 && number.length() >= 9;
    }
     public static boolean checkMessage(String text){
         return text.length()<=250 && text.length()> 0;
     }
      public static String generateRandomId() {
        Random generate = new Random();
        long number = 1000000000L + (long)(generate.nextDouble() * 9000000000L);
        return String.valueOf(number);}
    public static String sentMessage(int option)
    {
         return switch (option) {
             case 1 -> "Sent";
             case 2 -> "Saved";
             default -> "Deleted";
         };
    }  
      public static String FirstWord(String text) {
        String[] words = text.trim().split("\\s+");
        return words[0];
    }
    public static String LastWord(String text) {
        String[] words = text.trim().split("\\s+");
        return words[words.length - 1];
    } 
     public static  void print(String text,String cell, String hash, String choose, String ID, int messageCounter){
       System.out.println(text);
       System.out.println(ID);
       System.out.println(hash);
       System.out.println(cell);
       System.out.println(messageCounter);
       System.out.println(choose);
             
               }
     public static void returnTotalMessages(int total){
         System.out.println("Total Number of Messages: "+ total);
     }
   public static String createHash(String ID, int messageCounter, String text)
    {
        String adding = "";
        for(int count=0;count<=1;count+=1){
            adding = adding + ID.charAt(count);
        }
        
        return adding + ":" + messageCounter + ":" + Messages.FirstWord(text) + Messages.LastWord(text);
    }
     
     
}
