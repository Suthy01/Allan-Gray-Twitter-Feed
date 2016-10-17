




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;


public class TwitterFeed 
{
    


public static void main(String[] args) throws FileNotFoundException
{
    //****************************************BEGIN USER INSTANTIATION***************************************************************************
        //A hashmap would be faster as it has lookup optimization however a treemap will preserve the order of the keys (alphabetical)
        TreeMap<String, User> users; //each user name will be stored as a key, in alphabetical order, while their object will be stored as a value
        users = new TreeMap<>(); //No need for type arguments here; would be redundant
        
        File userFile = new File("user.txt"); //standard file

        Scanner sc = new Scanner(userFile); //simple file scanner
        String line; //To store line
        String uName;//to store username
        
        while (sc.hasNextLine()) //iterate line by line through the text file
        {
            line = sc.nextLine(); //store each line of data seperately for individual processing later
            uName = (line.substring(0,line.indexOf(" "))); //returns the first space - identify the user name -> store the user name
            line = line.substring(line.indexOf("follows ")+8);//set line to list of followers

            
            
            //**********IF USER DOES NOT EXIST*********************************************
            if(!users.containsKey(uName)) 
            {
                 //create a new queue of those users followed - we will be able to access the objects using the key of their name from the treemap created for each unique user
                Queue uFollows = new LinkedList();
                User u = new User(uName,uFollows); //Create new users
                //add a new user to the Treemap
                u.addFollow(line); //follow the user/users in the line of followers
                users.put(u.getUser(),u); //getUser ensures validated user returned
            }
            //******IF USER DOES NOT EXIST***************************************************
            
            //******IF USER DOES EXIST***************************************************
            else //if the user does exist, add any extra followers found in the line
            {
              if(line.contains(",")) //if multiple users in line
              {
                for(String uf : line.split(", ")) //iterate over the users after the follows word
                {
                    users.get(uName).addFollow(uf);  
                }
               }
               else
               {   
                    users.get(uName).addFollow(line); //Add the user in the follow line to the user queue, if allowed
               }    
            }
            //**********IF USER DOES EXIST****************************************************
            
            //*********EXAMINE FOLLOWERS IN FOLLOWS LINE***************
            if(line.contains(",")) //if multiple users in follow line
            {
                for(String uf : line.split(", ")) //iterate over the users after the follows word
                {
                    if(!users.containsKey(uf)) //if user in follower line does not exist
                    {  
                      //Empty queue inputted here, as we know this user does not follow anyone else at this moment
                       Queue emptyQ = new LinkedList();
                       User u = new User(uf,emptyQ);
                       users.put(u.getUser(),u); //using getUser ensures we receieve the user after the validation is done
                    
                    }//else do nothing,the followed user already exists
                }
            }
            else
            {
                if(!users.containsKey(line)) //do same as above
                    {  
                      //Queue containing only this user inputted here, as we know this user does not follow anyone else at this moment
                       Queue lonelyQ = new LinkedList();
                       User u = new User(line,lonelyQ); //must validate this user before making him follow himself
                       users.put(u.getUser(),u); //getUser ensures validated user put in treemap
                    
                    }//else do nothing,the followed user already exists
            }
            //************END EXAMINE FOLLOWERS IN FOLLOW LINE******************
        }
        //****************************************END USER INSTANTIATION****************************************************************************
        
        //****************************************BEGIN TWEETS*************************************************************************
        
        Queue tweets = new LinkedList(); //Queue ensures tweets are printed in the order that they come in to the program
        
        File tweetFile = new File("tweet.txt"); //standard file

            Scanner sc1 = new Scanner(tweetFile); //simple file scanner
            String line1; //Store line
        
            while (sc1.hasNextLine()) //iterate line by line through the text file
            {
                line1 = sc1.nextLine(); //store each line of data seperately for individual processing later
                
                if(!line1.contains(">")) //Don't accept tweet if format is incorrect
                {
                    JOptionPane.showMessageDialog(null, "An incorrectly formatted tweet has been rejected", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String uName1 = line1.substring(0, line1.indexOf(">")); //indexOf always finds FIRST occurence, so this will not find any ">"'s put in the rest of the line
                    String uTweet = line1.substring(line1.indexOf("> ")+2); //jump 2 spaces over the "> "
                    Tweet tweet = new Tweet(uName1,uTweet);
 
                    if(users.containsKey(tweet.getTweeter())) //find the user, if he exists
                    {
                        tweets.add(tweet); //ONLY if the user is validated and found to be in the user map do we add the tweet
                    }
                    else //display error message, do not store tweet: unkown user or 
                    {
                        JOptionPane.showMessageDialog(null, "A tweet from unknown user @"+uName1+" has been deleted", "Warning", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
 
        
        
        
    //****************************************END TWEETS***************************************************************************
    
    //****************************************BEGIN OUTPUT*************************************************************************
     //Below is a method which NetBeans recommends using instead of iterating over the TreeMap - it involves a lamba expression, which I am familiar with due to learning and being examined on Lambda Calculus in computer science
    users.entrySet().stream().map((uEntry) -> {
        return uEntry;
    }).forEachOrdered((uEntry) -> {
       System.out.println(uEntry.getKey());
       //******BEGIN Nested foreach of the tweets**************
        Iterator Iterator = tweets.iterator(); //used iterator as other methods require .poll() but this removes the element and we need it for processing every user in turn
        while (Iterator.hasNext()) 
        {
            Tweet t = (Tweet)Iterator.next();
            
            if(uEntry.getValue().getFollows().contains(t.getTweeter())) //if the user follows the tweeter, display the tweet
            {
                System.out.println(t.outputTweet());
            }
        }
            
       //****END Nested foreach of the tweets***********
   });
    //****************************************END OUTPUT***************************************************************************
 }
}