/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import model.Follower;
import model.Friend;
import model.User;

/**
 *
 * @author akhfa
 */
public class Logic {
    
    /**
     * Mengembalikan String username jika username ada di database.
     * Jika tidak ada, mengembalikan string false
     * @param username
     * @param password
     * @return 
     */
    public boolean register(String username, String password)
    {
        User user = new User(username, password);
        
        if(user.exist())
        {
            user.save();
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean login(String _username, String _password)
    {
        User user = new User(_username, _password);
        return user.login();
    }
    
    public boolean follow(String from, String destination)
    {
        User user1 = new User(from);
        User user2 = new User(destination);
        if(user2.exist())
        {
            Follower followerManajer = new Follower(destination, from, new Date().getTime());
            followerManajer.save();
            Friend friendManajer = new Friend(from, destination, new Date().getTime());
            friendManajer.save();
            return true;
        }
        else
            return false;
    }
}
