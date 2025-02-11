package org.example;

public class AuthService {
    public static boolean auth(String username, String password){
        //this is just a placeholder
        if (password.equals("1234")){
            return true;
        }
        else{
            return false;
        }
    }
}
