package example.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {

    // cryptography object
    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    // password hashing
    public String hashPassword(String password){

        String hashedPassword=encoder.encode(password);

        return hashedPassword;
    }

    // compare password
    public boolean comparePassword(String hashedPassword,String newpassword){

        boolean isPasswordSame=encoder.matches(newpassword,hashedPassword);

        return  isPasswordSame;
    }
    
}
