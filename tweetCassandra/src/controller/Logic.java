/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.datastax.driver.core.utils.UUIDs;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import model.Follower;
import model.Friend;
import model.TimeLine;
import model.Tweet;
import model.TweetContainer;
import model.User;
import model.UserLine;

/**
 *
 * @author akhfa
 */
public class Logic {
    
    /**
     * Mengembalikan String username jika username ada di database.
     * Jika tidak ada, mengembalikan string false
     * @param username
     * @param password
     * @return 
     */
    public boolean register(String username, String password)
    {
        User user = new User(username, password);
        
        if(!user.exist())
        {
            user.save();
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean login(String _username, String _password)
    {
        User user = new User(_username, _password);
        return user.login();
    }
    
    public boolean follow(String from, String destination)
    {
        User user1 = new User(from);
        User user2 = new User(destination);
        if(user2.exist())
        {
            Follower followerManajer = new Follower(destination, from, new Date().getTime());
            followerManajer.save();
            Friend friendManajer = new Friend(from, destination, new Date().getTime());
            friendManajer.save();
            return true;
        }
        else
            return false;
    }
    
    public boolean postTweet(String _username, String _body)
    {
        UUID tweetuuid = UUIDs.random();
        UUID timeuuid = UUIDs.timeBased();
        Tweet tweet = new Tweet(tweetuuid, _username, _body);
        tweet.save();
        UserLine userline = new UserLine(_username, timeuuid, tweetuuid);
        userline.save();
        TimeLine timeline = new TimeLine(_username, timeuuid, tweetuuid);
        timeline.save();
        
        ArrayList<String> daftarFollower = Follower.getAllFollowerFrom(_username);
        for(String follower : daftarFollower)
        {
            System.out.println("follower = " + follower);
            TimeLine timelineTemp = new TimeLine(follower, timeuuid, tweetuuid);
            timelineTemp.save();
        }
        
        return true;
    }
    
    public void printUserline(String _username)
    {
        ArrayList<TweetContainer> tweets = UserLine.getAllTweetFrom(_username);
        for(TweetContainer tweet : tweets)
        {
            System.out.println("@ " + tweet.getUsername());
            System.out.println("" + tweet.getBody());
            System.out.println("===================================");
        }
    }
    
    public void printTimeline(String _username)
    {
        ArrayList<TweetContainer> tweets = TimeLine.getAllTweetFrom(_username);
        for(TweetContainer tweet : tweets)
        {
            System.out.println("@ " + tweet.getUsername());
            System.out.println(tweet.getBody());
            System.out.println("===================================");
        }
    }
}
