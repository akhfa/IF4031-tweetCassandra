/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;

/**
 *
 * @author akhfa
 */
public class User {
    private String username;
    private String password;
    
    public User(String _username, String _password)
    {
        this.username = _username;
        this.password = _password;
    }
    
    public void save()
    {
        Session session = Connection.getSession();
        
        Insert insert = QueryBuilder.insertInto("pat", "users")
                                .value("username", username)
                                .value("password", password);
        ResultSet result = session.execute(insert.toString());
        Connection.close();
    }
}
