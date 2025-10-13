
package as1;

import java.util.regex.Pattern;

public class Login 
{
    public static boolean checkUsername(String input){
        return input.contains("_") && input.length()<=5  ;         
    }
    public static boolean checkPassword(String input){
          String specialChars = "!@#$%^&*()-_=+[]{}|;:,.<>?";  
      for (char c : input.toCharArray()) {  
        if (specialChars.indexOf(c) != -1 ) {  
           return input.length()>=8;  
        }  
      }  
      return false;    
    }
    public static boolean isValidCellPhone(String number) {
        String regex = "^\\+\\d{1,3}\\d{1,10}$";
        return Pattern.matches(regex, number);
    }
    
    public static String registerUser(boolean Name, boolean Password){
        
        if(Name && Password){
            return  "User was created ";
        }
        else{
          return " User Name or Password not meeting requirements, account not created";
        }   
    }
    public static boolean loginUser(String username, String password, String userSecond, String passSecond){
        if(username.compareTo(userSecond)==0 && password.compareTo(passSecond)==0){
            return true;
        }   
        else{
            return false;
        }
    }
    public static String returnLoginStatus(boolean loginStatus){
        if(loginStatus==true){
           return  "Login attempt Successful";
        }
            return "Login attempt Failed";
    }
}
