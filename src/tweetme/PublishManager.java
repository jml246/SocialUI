/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class PublishManager extends Thread{
    protected CopyOnWriteArrayList<Media> media;
    public PublishManager()
    {
        media = new CopyOnWriteArrayList<>();
    }

    public CopyOnWriteArrayList getMediaArr() {
        return media;
    }

    public void setMedia(CopyOnWriteArrayList media) {
        this.media = media;
    }
    
    public void addMedia(Media m){
        media.add(m);
    }
    public void removeMedia(Media m){
        media.remove(m);
    }
    public void scanMediaToPublish() throws IOException
    {
        Date d = new Date();
        try{
        for (Media m : Main.publishMan.media){//Iterator<Media> it = media.iterator(); it.hasNext(); ) {
            //Media m = it.next();
            System.out.println("Manager is going through the list..."+m.toString());
            if(d.after(m.getTimeToPublish()))
            {    
                System.out.println("Publishing..."+m.toString());
                new Publisher(m);
                //System.out.println(m.toString());
                //it.remove();
                media.remove(m);
            }    
            Thread.sleep(1000);
        }
        }catch(ConcurrentModificationException ex){
            ex.printStackTrace();
            System.out.println("Change in array: " + media.size());
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
    private void updateFeedlabel(String msg)
    {
        
        Main.mainGui.updateFeedlabel(Main.mainGui.getFeedLabelText() + new java.util.Date() + ": " + msg + "<br/>");
    }    
    public void run(){
        try {
            while(true){
                this.scanMediaToPublish();
            }
        } catch (Exception ex) {
            Logger.getLogger(PublishManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
