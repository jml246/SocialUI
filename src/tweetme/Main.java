/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetme;

import java.io.IOException;

/**
 *
 * @author jose
 */
public class Main {
    //public static Main main;
    public static PublishManager publishMan;
    public static SocialUI mainGui;
    public static void main(String args[]) throws IOException
    {
        //start login process
        new MSTwitter();
        new InstagramLogin().setVisible(true);
            
        publishMan = new PublishManager();
        publishMan.start(); 
        mainGui = new SocialUI();
        
       
        new Main();     
    }
    public Main() throws IOException
    {
        
       
    
    }
    
    
}
