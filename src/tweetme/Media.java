/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetme;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jose
 */
public class Media {
    
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimeToPublish() {
        return timeToPublish;
    }

    public void setTimeToPublish(Date timeToPublish) {
        this.timeToPublish = timeToPublish;
    }

    public boolean isTwitter() {
        return twitter;
    }

    public void setTwitter(boolean twitter) {
        this.twitter = twitter;
    }

    public boolean isInstagram() {
        return instagram;
    }

    public void setInstagram(boolean instagram) {
        this.instagram = instagram;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
    private String text;
    private Date timeToPublish;
    private boolean twitter;
    private boolean instagram;
    private Calendar date = Calendar.getInstance();    
    private final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

    public Media(String filePath, String text, int minutes, boolean twitter, boolean instagram){
        this.filePath = filePath;
        this.text = text;      
        long t = date.getTimeInMillis();
        this.timeToPublish = new Date(t + ( minutes * ONE_MINUTE_IN_MILLIS));
        this.twitter = twitter;
        this.instagram = instagram;
        
    }

    public Media() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String toString(){
       return "Text: "+this.text+
               "File: "+this.filePath+
               "DateToPublish: "+this.getTimeToPublish()+
               "Twitter?: " +this.twitter +
               "Instagram?: " +this.instagram;        
    }
    
}
