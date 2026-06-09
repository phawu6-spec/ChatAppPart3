/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatappproject;

/**
 *
 * @author asave
 */
public class Login {
    
     //===================================================
    //The variables below store the user's login details
    //After the user register, their data is saved here.
    //--------------------------------------------------
    String username;
    String password;
    String phoneNumber;
    
    
    //  ===========================================
    // TO CHECK USERNAME 
    // You must ensure:
    // - Username contains an underscore "_"
    // - Username is not more than 5 characters in length
    // ---------------------------------------------
    public boolean checkUserName(String username) {
       
         //check if the username contains the underscore"_"
        //ensure if the length of the username is <=5
        return username.contains("_") && username.length() <=5;
        
    
    }
    
     // =======================================
    // TO CHECK THE VALIDITY OF THE PASSWORD
    // You must ensure:
    //-the password must be >=8 in length
    //-the password must contain a number
    //-the password must have 
    //----------------------------------
    public boolean checkPasswordComplexity(String password){
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        // Use LOOPS over each characters
        
        for(int i =0; i<password.length(); i++){
            char c = password.charAt(i);
            if (Character.isUpperCase(c)){
                hasCapital = true;
            } else if (Character.isDigit(c)){
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)){
                hasSpecial = true;
            }
        }
        return password.length()>=8 && hasCapital && hasNumber && hasSpecial;
        
    }
    
    // =======================================
    // TO CHECK THE VALIDITY OF THE CELLPHONE NUMBER 
    // You musr ensure:
    //- iut starts with "+27"
    //- it has <=12 characters
    // ---------------------------------------
    public boolean checkCellphoneNumberValidity(String phone){
        return phone.startsWith("+27") && phone.length() <= 12;
    }
    
    // ================================================================
    // METHOD TO REGISTER THE USER
    //You must ensure:
    //-to have a method that checks username
    //-to have a method that checks password
    //-to have a method that checks cellphone number
    //-to have a method that stores the data if everything is correct
    //-to have a method that returns a specific message
    //----------------------------------------------------------------
    
    public String registerUser(String username, String password, String phoneNumber){
       if(!checkUserName(username)){
           return "The Username is incorrect, Ensure that your username contains an underscore and is not more than 5 characters.";
       }
       if(!checkPasswordComplexity(password)){
           return "The password is incorrect, Ensure that the password contains atleast 8 charactewrs , it has a capital letter , it has a number and it has a special character";
       }
       if(!checkCellphoneNumberValidity(phoneNumber)){
           return "The cellphone number is incorrect, Ensure thar the cellphone has international code for S.A and is follwed by 9 digits";
    }
       this.username = username;
       this.password = password;
       this.phoneNumber =phoneNumber;
       
       return "You're successfully registered";
    }
    
    //==============================================================================
    // We are allowing the user to login with the SAME details they registered with.
    //------------------------------------------------------------------------------
    
    public boolean loginUser(String phoneNumber, String password){
        return this.phoneNumber.equals(phoneNumber) && this.password.equals(password);
    }
    public String returnLoginStatus(boolean success){
        if (success){
            return "Welcome " + username + ", It's nice to have you again.";
        }else{
            return "Your phoneNmuber or password is invalid, Try and login again.";
        }
    }

    
}
