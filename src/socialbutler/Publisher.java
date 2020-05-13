/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialbutler;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUploadVideoRequest;
import winterwell.jtwitter.Status;

/**
 *
 * @author jose
 */
public class Publisher {
    Media m;
    File f;
    public Publisher(Media media) throws IOException{
    
        m = media;
        f = new File(m.getFilePath());
        if(m.isTwitter()){
            this.sendToTwitter();
                    System.out.println("Publisher - Sending to twitter method");

        }
        if(m.isInstagram()){
            this.sendToInstagram();
            System.out.println("Publisher - Sending to insta method");
        }
                     
    }
    
    public void sendToTwitter(){
        //String out = null;
               if(m.getFilePath() == "" && new File(m.getFilePath()).exists()){
                //System.out.println("Publisher - Sending to twitter OK");   
                Main.mainGui.updateFeedLabel("Sending tweet...");
                try{
                    if(!m.isIsPubTwitter()){
                        MSTwitter.tweet.setStatus(m.getText());
                    }
                    Main.mainGui.updateFeedLabel("Tweet text OK...");
                    System.out.println("Publisher - Sending text to twitter OK");
                    m.setIsPubTwitter(true);
                }
                catch(winterwell.jtwitter.TwitterException e)
                {
                    Main.mainGui.updateFeedLabel("Tweet text failed... : " + e.getMessage());
                    System.out.println("Publisher - There was a problem" + e);
                }catch(Exception e){e.printStackTrace();}
                }
               else{
                   try{
                    if(!m.isIsPubTwitter()){
                        Main.mainGui.updateFeedLabel("Sending tweet...");
                        MSTwitter.tweet.updateStatusWithMedia(m.getText(), BigInteger.ZERO, new File(m.getFilePath()));
                        Main.mainGui.updateFeedLabel("Tweet text OK...");
                        m.setIsPubTwitter(true);
                    }
                   
                   }catch(winterwell.jtwitter.TwitterException e)
                   {
                       Main.mainGui.updateFeedLabel("Tweet with media failed... : " + e.getMessage());   
                   }catch(Exception e){e.printStackTrace();}
               }
            
        //return out;
    }
    public boolean checkHTTPResponse()
    {
        if(Instagram.instagram.getLastResponse().toString().contains("HTTP/1.1 200 OK"))
        {
            return true;
        }
            return false;
    }
    public void sendToInstagram() throws IOException{
        String out = null;
        
        if(m.getFilePath() != "" && new File(m.getFilePath()).exists())
        {
            try {
                if(m.getFilePath().contains("jpg") ||
                    m.getFilePath().contains("jpeg"))
                {
                    if(!m.isPubInsta()){
                    Main.mainGui.updateFeedLabel("Sending Instagram post with image...");
                    Instagram.instagram.sendRequest(new InstagramUploadPhotoRequest(
                        f, m.getText()));
                    }
                if(checkHTTPResponse()){
                    Main.mainGui.updateFeedLabel("Post sent to Instagram OK...");
                    m.setIsPubInsta(true);
                }
                else{
                        Main.mainGui.updateFeedLabel("Post sent to Instagram Failed...");
                        new Log(Instagram.instagram.getLastResponse().toString() + "/r");
                    }
                }
                else
                {
                    if(m.getFilePath().contains("mp4") && m.isPubInsta()){
                        Main.mainGui.updateFeedLabel("Sending Instagram post with video...");
                        Instagram.instagram.sendRequest(new InstagramUploadVideoRequest(
                            f, m.getText()));
                    Main.mainGui.updateFeedLabel("Sent to Instagram...");
                    if(checkHTTPResponse()){
                        Main.mainGui.updateFeedLabel("Post sent to Instagram OK...");
                        m.setIsPubInsta(true);
                    }
                    else{
                        Main.mainGui.updateFeedLabel("Post sent to Instagram Failed...");
                        new Log(Instagram.instagram.getLastResponse().toString()+"/r");
                        }
                    }
                }
            }catch(IOException err)
            {
                err.printStackTrace();
                Main.mainGui.updateFeedLabel("IOException error: Unable to post to Instagram...");
                
            }catch(NullPointerException e){
                Main.mainGui.updateFeedLabel("NullPointerException error: Unable to post to Instagram...");
                
                e.printStackTrace();}
            
            catch(Exception ex)
            {
                ex.printStackTrace();
                Main.mainGui.updateFeedLabel("Exception encountered: Unable to post to Instagram...");
                
            }
            
        
    
        }
        
    }
}