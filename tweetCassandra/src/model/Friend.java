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
public class Friend {
    private String username;
    private String friend;
    private long timestamp;
    
    public Friend(String _username, String _friend, long _timestamp)
    {
        username = _username;
        friend = _friend;
        timestamp = _timestamp;
    }
    public void save()
    {
        Session session = Connection.getSession();
        
        Insert insert = QueryBuilder.insertInto(Connection.getKeySpace(), "friends")
                                .value("username", username)
                                .value("friend", friend)
                                .value("since", timestamp);
        ResultSet result = session.execute(insert.toString());
        Connection.close();
    }
}
