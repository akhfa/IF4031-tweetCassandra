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
import java.util.ArrayList;

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
    
    /**
     * Mendapatkan follower dari username tertentu
     * @param username 
     */
    public static ArrayList<String> getAllFollowerFrom(String _username)
    {
        ArrayList<String> daftarFollower = new ArrayList<>();
        Session session = Connection.getSession();
        
        Statement statement = QueryBuilder.select("follower")
                                            .from("pat", "followers")
                                            .where(eq("username", _username));
        ResultSet results = session.execute(statement);
        
        for (Row row : results) {
            daftarFollower.add(row.getString("follower"));
        }
        return daftarFollower;
    }
}
