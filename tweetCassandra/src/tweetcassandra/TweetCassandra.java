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
        Session session = Connection.getSession();
        
        Insert insert = QueryBuilder.insertInto("pat", "users")
                                .value("username", "akhfa4")
                                .value("password", "akhfa2");
        System.out.println(insert.toString());
        ResultSet result = session.execute(insert.toString());
        System.out.println(result.toString());
        
//        session.execute("INSERT INTO users (username, password) VALUES ('akhfa1', 'akhfa');");
//        ResultSet results = session.execute("SELECT * FROM users WHERE username='akhfa';");
//        for (Row row : results) {
//            System.out.format("%s %d\n", row.getString("firstname"), row.getInt("age"));
//        }
        
        Connection.close();
    }
    
}
