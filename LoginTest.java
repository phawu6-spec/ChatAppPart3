/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.chatappproject;

import com.mycompany.chatappproject.Login;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
      //==============================================================
        //WE ARE TESTING ALL THE LOGIN DETAILS IF THEY ARE VALID OR NOT.
        //-Validity of the username.
        //-validity of the password.
        //-validity of the cellphone number.
        //--------------------------------------------------------------
    Login login = new Login();
    
       //======================================
      //to check the validity of the username
     //----------------------------------------
    @Test
    public void testValidUsername(){
         //the username is good then proceed to the next step.
        assertTrue(login.checkUserName("kyl_1"));
    }
    
    @Test
    public void testInvalidUsername_WithNoUnderscore(){
        //tells the users that their username does not have the underscore
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    
    @Test
    public void testInvalidUserName_TooLong(){
        //tells the user that their username is too long
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    
        //=====================================
       //to check the validity of the password
      //---------------------------------------
    @Test
    public void testValidPassword(){
        //the password is good then proceed to the next step
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    @Test
    public void testInvalidPassword_WithNoCapital(){
        //tells the user that their password does not have any capital letter
        assertFalse(login.checkPasswordComplexity("ch&&sech@ke99!"));
    }
    
    @Test
    public void testInvalidPassword_WithNoNumber(){
        //tells the user that their password does not have atleast one number
        assertFalse(login.checkPasswordComplexity("Ch&&sec@keee!"));
    }
    
    @Test 
    public void testInvalidPassword_WithNoSpecial(){
        //tells the user that their password does not have atleast one special character
        assertFalse(login.checkPasswordComplexity("Chaasecake99a"));
    }
    
    @Test
    public void testInvalidPassword_WithLessThan8Characters(){
            //te;;s the user that their password does not consist of atleast 8 characters
            assertFalse(login.checkPasswordComplexity("ky_1"));
    }
    
    //===============================================
    //to check the validity of the cell phone number
    //------------------------------------------------
    @Test 
    public void testValidPhoneNumber(){
        //if the cellphone number has the S.A code and has <=12 character it's valid
        assertTrue(login.checkCellphoneNumberValidity("+27838968976"));
    }
    
    @Test
    public void  testInvalidPhoneNumber_WithNoSACode(){
        //tells the user that their cellphone number does not have the S.A code
        assertFalse(login.checkCellphoneNumberValidity("08966553"));
    }
    
    @Test
    public void testInvalidPhoneNumber_WithLongPhoneNumber(){
        //tells the user that their cellphone number has >12 characters 
        assertFalse(login.checkCellphoneNumberValidity("08966553"));
    }
    //if all valid then the user can login on his/her account.
}
