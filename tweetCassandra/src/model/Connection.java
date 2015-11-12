/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 *
 * @author akhfa
 */
public class Connection {
    
    private static Cluster cluster;
    private static Session session;
    public static Session getSession()
    {
        cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        session = cluster.connect("pat");
        return session;
    }
    public static void close()
    {
        cluster.close();
    }
}
