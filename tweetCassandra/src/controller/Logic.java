/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import model.Connection;
import static model.Connection.getSession;
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
}
