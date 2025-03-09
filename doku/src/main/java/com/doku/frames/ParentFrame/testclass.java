package com.doku.frames.ParentFrame;

import com.doku.model.Users.PasswordUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class testclass {
    public static void main(String[] args) {
    	String plainPassword = "test123";
    	String hashedPassword = PasswordUtils.hashPassword(plainPassword);
    	boolean isMatch = PasswordUtils.checkPassword(plainPassword, hashedPassword);
    	System.out.println("Şifre eşleşiyor mu? " + isMatch);
    }
}