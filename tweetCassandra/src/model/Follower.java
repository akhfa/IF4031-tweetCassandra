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
public class Follower {
    private String username;
    private String follower;
    private long timestamp;
    
    public Follower(String _username, String _follower, long _timestamp)
    {
        this.username = _username;
        this.follower = _follower;
        this.timestamp = _timestamp;
    }
    
    public void save()
    {
        Session session = Connection.getSession();
        
        Insert insert = QueryBuilder.insertInto("pat", "followers")
                                .value("username", username)
                                .value("follower", follower)
                                .value("since", timestamp);
        ResultSet result = session.execute(insert.toString());
        Connection.close();
    }
}
