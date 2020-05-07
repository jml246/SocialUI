/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetme;

import java.io.IOException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;




/**
 *
 * @author jose
 */
public class Instagram {
    public static Instagram4j instagram;
   
    public Instagram(String userName, String passWord) throws IOException, Exception{
        if(userName != "" || passWord != "")
        {
            
            instagram = Instagram4j.builder().username(userName).password(passWord).build();
            instagram.setup();
            InstagramLoginResult login = instagram.login();

        
        }
        else
        {
            //do nothing
            
        }
    }

   
}
