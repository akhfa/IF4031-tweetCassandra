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

/**
 *
 * @author akhfa
 */
public class User {
    private String username;
    private String password;
    
    public User(String _username)
    {
        this.username = _username;
    }
    
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
    
    public boolean exist()
    {
        Session session = Connection.getSession();
        
        Statement statement = QueryBuilder.select("username")
                                            .from("pat", "users")
                                            .where(eq("username", username));
        ResultSet results = session.execute(statement);
        Row row = results.one();
        return row != null;
    }
    
    /**
     * Fungsi untuk mengecek username dan password
     * @return 
     */
    public boolean login()
    {
        Session session = Connection.getSession();
        
        Statement statement = QueryBuilder.select().all()
                                            .from("pat", "users")
                                            .where(eq("username", username));
        ResultSet results = session.execute(statement);
        
        Row row = results.one();
        
        if(row != null)
        {
            String dbpassword = row.getString("password");
            return password.equals(dbpassword);
        }
        else
        {
            return false;
        }
    }
}
