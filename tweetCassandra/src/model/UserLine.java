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
import com.datastax.driver.core.utils.UUIDs;
import java.util.UUID;

/**
 *
 * @author akhfa
 */
public class UserLine {
    private String username;
    private UUID time;
    private UUID tweet_id;
    public UserLine(String _username, UUID _time, UUID _tweet_id)
    {
        this.username = _username;
        this.time = _time;
        this.tweet_id = _tweet_id;
    }
    
    public void save()
    {
        Session session = Connection.getSession();
        
        Insert insert = QueryBuilder.insertInto("pat", "userline")
                                .value("username", username)
                                .value("time", time)
                                .value("tweet_id", tweet_id);
        ResultSet result = session.execute(insert.toString());
        Connection.close();
    }
}
