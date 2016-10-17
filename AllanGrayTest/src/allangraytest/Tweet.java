



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JOptionPane;


public class Tweet
{
    private String tweeter;
    private String tweet;
    
    public Tweet(String tweeter, String tweet)
    {
        this.tweeter = validateTweeter(tweeter);
        this.tweet = validateTweet(tweet);
    }
    
    //Assuming the only thing a tweet cannot be, is greater than 140 characters. otherwise any string is tweetable.
    //twitter has a functioanlity that blocks users from tweeting more than a certain number of times per day, however, this is out of scoop for this implementation
    private String validateTweet(String tweet) //Constructors must invoke only methods that are final or private, thus this method is private
    {
        if(tweet.length()>140) //if the tweet is more than 140 characters, cut it down to 140 characters
        {
            JOptionPane.showMessageDialog(null, "A tweet by @"+this.tweeter+" containing "+tweet.length()+" characters has been truncated to 140 characters", "Warning", JOptionPane.ERROR_MESSAGE);
            tweet = tweet.substring(0,140);
            
        }
        return tweet;
    }
    
    //Method is private as I call it in the constructor
    //no need to test this method, as it is a direct copy of the validate method in the user class, which has been thoroughly tested already
    private String validateTweeter(String name) //This class must have its own validate method, as accessing the user class' methods could cause major bugs
    {   
        //String wrongName = name; //copy string for later comparison - we can do this as strings are immutable in java
        name = name.replaceAll("[^A-Za-z]",""); //only allow upper or lower case letters in names
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase(); //Rather than checking case, this forces names to start with capital and be followed by all lowercase
           
//       if(!wrongName.equals(name)) //this is done a lot in big data sets, taken out to avoid death by boredom
//           {
//                JOptionPane.showMessageDialog(null, "Incorrectly formatted user name '"+wrongName+"' has been formatted to: "+ name, "Warning", JOptionPane.ERROR_MESSAGE);
//           }
        return name;
    }
    
    public String getTweeter()
    {
     return this.tweeter;   
    }
    
    public String getTweet()
    {
        return this.tweet;
    }
    
    public String outputTweet() //sounds better than .toString()
    {
        return "\t@"+this.tweeter+": "+this.tweet;
    }
}
