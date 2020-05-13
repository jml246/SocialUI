/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialbutler;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;
import static socialbutler.Main.mainGui;




/**
 *
 * @author jose
 */
public class Instagram {
    public static Instagram4j instagram;
    public static InstagramLoginResult login;
    public Instagram(String userName, String passWord) throws IOException, Exception{
        if(userName != "" || passWord != "")
        {
            
            instagram = Instagram4j.builder().username(userName).password(passWord).build();
            instagram.setup();
            login = instagram.login();
            if((!login.getStatus().equals("ok")))
            {
             JOptionPane.showMessageDialog(null, "User name or password is incorrect - could not log in. Please try again."); 
            
            }
            else{
                mainGui.setVisible(true);
                }
        }
        else
        {
            //do nothing
            
        }
        System.out.println("Printing last response"+Instagram.instagram.getLastResponse());
    }
    
    
    
    public InstagramLoginResult getLogin()
    {
        return login;
    }

   
}
