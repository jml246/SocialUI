/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;


/**
 */
public class TweetMe {

     
    
public ArrayList<String> arrTweets;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException, Exception {
            new MSTwitter();
            new InstagramLogin().setVisible(true);
            //for testing form purposes
            //new SocialUI().setVisible(true);
    }
    
public void publishTweetsFromFile(String file) 
    {
        int min = 120;
        int max = 240;
	BufferedReader br = null;
	String line = "";
	//String cvsSplitBy = "";
        int tweetInd;
        int lineNo = 0;


while(true){
    
    arrTweets = new ArrayList<String>();

            try {
                arrTweets.clear();
                br = new BufferedReader(new FileReader(file));
                 
                while ((line = br.readLine()) != null) 
                {
                    if(line.contains("--"))
                    {continue;}    
                    arrTweets.add(line);
                }
            } catch (IOException ex) {
                 JOptionPane.showMessageDialog(null,ex);
            }
            finally{
              try {
                br.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
}
        while(arrTweets.size() > 0)
        {
            
            lineNo++;
            Random rnd = new Random();
            Random rndTime = new Random();
            if(arrTweets.size() <= 1){
            tweetInd = 0;
            }else{tweetInd = rnd.nextInt(arrTweets.size() - 1);}
            //String str = new SimpleDateFormat("EEEE").format(new Date())+"'s Tip#"+lineNo+": "+arrTweets.get(tweetInd);   
            //String str = "#"+lineNo + " " + arrTweets.get(tweetInd);   
            String str = arrTweets.get(tweetInd);   
               try
               {
                   if(str.length() > 140) {
                       str = str.substring(0, 140);
                   }
                   //MSTwitter.t.setStatus(str);
                   MSTwitter.tweet.setStatus(str);
                   
                   arrTweets.remove(tweetInd);
                   System.out.println("Tweets size: " + arrTweets.size());
               }
               catch(winterwell.jtwitter.TwitterException e){
                   JOptionPane.showMessageDialog(null, e);
               }
              
               try{
                   TimeUnit.MINUTES.sleep(rndTime.nextInt(max - min + 1) + min);
               }
               catch(InterruptedException io)
               {
               JOptionPane.showMessageDialog(null,io);
               }
               
            }
        
                //JOptionPane.showMessageDialog(null,"Tweeted all quotes");
        }
    }// List<User> followers = tweet.getFollowers();
        // for (User user : followers) {
        //    System.out.println(user.getName());
        //}     
}

