/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterPublisher.Connexions;

import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Middle object to connect to Twitter API by sending a message.
 * @author jorgeAleman
 */
public class TwitterInterface {
    /**
     * Publish the given message on twitter.
     * @param message
     * @throws TwitterException 
     */
    public static void publish(String message) throws TwitterException{
        
        TwitterFactory factory = new TwitterFactory();
        Twitter twitter = factory.getInstance();
        twitter.setOAuthConsumer("vIZ097dCEeXqnYofk8N6qTw9E", "GdN2pVxNTHJNRBZltaeeCDSTRScQL1bjCzlIgDza2WVRGfZ5SC");
        twitter.setOAuthAccessToken(new AccessToken("795590633918464001-NpyvUY0OylVbLNznVvoG98M8pMIaajv", "z1xR0jyHBbUsDioW3T7LIAC9b3FgTyvaGxgMSiGGn9cjR"));
        twitter.updateStatus(message);
        System.out.println("Twitter message has been published");
        
    }
}
