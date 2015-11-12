/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import com.datastax.driver.core.utils.UUIDs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    
    public static ArrayList<TweetContainer> getAllTweetFrom(String _username)
    {
        ArrayList<TweetContainer> tweets = new ArrayList<>();
        Session session = Connection.getSession();
        
        Statement statement = QueryBuilder.select("tweet_id")
                                            .from("pat", "userline")
                                            .where(eq("username", _username));
        ResultSet results = session.execute(statement);
        
        for (Row row : results) {
            Statement statement2 = QueryBuilder.select().all()
                                            .from("pat", "tweets")
                                            .where(eq("tweet_id", row.getUUID("tweet_id")));
            ResultSet results2 = session.execute(statement2);
            
            Row tweet = results2.one();
            if(tweet != null)
            {
                String username = tweet.getString("username");
                String body = tweet.getString("body");
                TweetContainer container = new TweetContainer(username, body);
                tweets.add(container);
            }
        }
        
        return tweets;
    }
}
