/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetcassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import model.Connection;
import model.User;

/**
 *
 * @author akhfa
 */
public class TweetCassandra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        User user = new User("akhfa10", "akhfa10");
        user.save();
        
//        session.execute("INSERT INTO users (username, password) VALUES ('akhfa1', 'akhfa');");
//        ResultSet results = session.execute("SELECT * FROM users WHERE username='akhfa';");
//        for (Row row : results) {
//            System.out.format("%s %d\n", row.getString("firstname"), row.getInt("age"));
//        }
        
    }
    
}
