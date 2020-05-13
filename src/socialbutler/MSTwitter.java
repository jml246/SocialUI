/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialbutler;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import winterwell.jtwitter.OAuthSignpostClient;
import winterwell.jtwitter.Twitter;
/**
 *
 * @author Jose
 */
public class MSTwitter {

    public OAuthSignpostClient client;
    public String uName = "TweetMe";
    public static Twitter tweet;
    public OAuthSignpostClient oauthClient;
    public String[] accessToken;
    
    
    public Twitter getTwitter() {
        return tweet;
    }
    
    public MSTwitter()
    {

         oauthClient = new OAuthSignpostClient("jm7NbcyMj7NYK1aeSx9eXlTzA", 
         "SL9VykPkAqevFwamxOp6d7cUIC4cYYm9jBK0jRWAW1UvxYm5RY", "oob");

                 // Open the authorisation page in the user's browser. On a desktop, we can do that like this:
         oauthClient.authorizeDesktop();
         // get the pin
         String v = oauthClient.askUser("Please enter the verification PIN from Twitter");
         //System.out.println(v);
         oauthClient.setAuthorizationCode(v);
         //oauthClient.setAuthorizationCode("7983512");
         // Store the authorisation token details for future use
         accessToken = oauthClient.getAccessToken();
         // Next time we can use new OAuthSignpostClient(OAUTH_KEY, OAUTH_SECRET, 
         //      accessToken[0], accessToken[1]) to avoid authenticating again.

         // 2. Make a Twitter object
         tweet = new Twitter("TweetMe", oauthClient);

    }
    public void ReuseAccessToken()
    {
        client = new OAuthSignpostClient("jm7NbcyMj7NYK1aeSx9eXlTzA",
                "SL9VykPkAqevFwamxOp6d7cUIC4cYYm9jBK0jRWAW1UvxYm5RY", accessToken[0]);
        // Open the authorisation page in the user's browser
        // On Android, you'd direct the user to URI url = client.authorizeUrl();
        // On a desktop, we can do that like this:
        // Store the authorisation token details for future use
        accessToken = client.getAccessToken();
        // Next time we can use new OAuthSignpostClient(OAUTH_KEY, OAUTH_SECRET,
        //      accessToken[0], accessToken[1]) to avoid authenticating again.

        //check that internet is ok
        if(isInternetOK())
        {
            tweet = new Twitter(uName, client);
        }
        else
        {
            tweet = null;
        }
    }
    public void sendTweet(String pTweet)
    {
        if(this.isInternetOK())
        this.getTwitter().setStatus(pTweet);
        
    }
    //checks for connection to the internet through dummy request
    private boolean isInternetOK()
    {
        try
        {
            //make a URL to a known source
            URL url = new URL("http://twitter.com");

            //open a connection to that source
            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            Object objData = urlConnect.getContent();

        }
        catch (UnknownHostException e)
        {
            return false;
        }
        catch (IOException e)
        {
            return false;
        }
        return true;
    }
}

