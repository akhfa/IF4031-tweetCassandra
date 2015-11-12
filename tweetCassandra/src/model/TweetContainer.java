/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author akhfa
 */
public class TweetContainer {
    private String username;
    private String body;
    public TweetContainer(String _username, String _body)
    {
        this.username = _username;
        this.body = _body;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getBody()
    {
        return body;
    }
}
