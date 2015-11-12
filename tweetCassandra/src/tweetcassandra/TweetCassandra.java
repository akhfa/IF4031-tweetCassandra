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
import com.datastax.driver.core.utils.UUIDs;
import controller.Logic;
import java.util.Date;
import java.util.Scanner;
//import java.util.UUID;
import model.Connection;
import model.Follower;
import model.Friend;
import model.TimeLine;
import model.Tweet;
import model.User;
import model.UserLine;

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
        String username = "";
        boolean guest = true;
        boolean first = true;
        Logic logic = new Logic();
        
        while (true)
        {
            if(guest)
            {
                if(first)
                {
                    System.out.println("Selamat Datang");
                    first = false;
                }
                    
                System.out.println("To register,    ketik: reg [username] [password]");
                System.out.println("To login,       ketik: login [username] [password]");
                System.out.println("To exit,        ketik: exit");
                System.out.print("guest:~$ ");
                Scanner in = new Scanner(System.in);
                String command = in.nextLine();

                String [] com = command.split(" ");
                switch(com[0])
                {
                    case "reg":
                        if(logic.register(com[1], com[2]))
                        {
                            username = com[1];
                            first = true;
                            guest = false;
                        }
                        break;
                    case "login":
                        if(logic.login(com[1], com[2]))
                        {
                            username = com[1];
                            first = true;
                            guest = false;
                        }
                        else 
                            System.out.println("Password salah. Coba lagi.");
                        break;
                    default:
                        System.exit(0);
                }
            }
            else
            {
                if(first)
                    System.out.println("Selamat datang " + username);
                System.out.print(username + ":~$ ");
                
                Scanner in = new Scanner(System.in);
                String command = in.nextLine();

                String [] com = command.split(" ");
                switch(com[0])
                {
                    
                }
            }
        }
    }
}
