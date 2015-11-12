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
public class Tweet {
    private UUID uuid;
    private String username;
    private String body;
    
    public Tweet(UUID _uuid, String _username, String _body)
    {
        this.username = _username;
        this.body = _body;
        this.uuid = _uuid;
    }
    
    public void save()
    {
        Session session = Connection.getSession();
        
        Insert insert = QueryBuilder.insertInto(Connection.getKeySpace(), "tweets")
                                .value("tweet_id", uuid)
                                .value("username", username)
                                .value("body", body);
        ResultSet result = session.execute(insert.toString());
        Connection.close();
    }
}
