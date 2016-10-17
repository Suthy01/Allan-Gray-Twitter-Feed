

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class User {

    private String name; //name of user
    private Queue follows; //array storing who the user follows
    
    
    public User(String name, Queue follows) //User constructor
    {
        this.name = setUser(name);
        this.follows = follows;
        follows.add(this.name); //make validated user follow him/herself
    }
   
    public String getUser()
    {
        return this.name;
    }
    private String setUser(String name)
    {
        return validateUser(name);
    }
    public Queue getFollows()
    {
        return this.follows;
    }
    
    
    public void addFollow(String line)
    {
       if(!this.follows.contains(this.name))
       {
           this.follows.add(this.name);
       }
         //must test if a single user or multiple users are followed
         if(line.contains(",")) //if the list is comma seperated, there are multiple users in the follow list
         {  
             for(String uf : line.split(", "))
            {
              if(!this.follows.contains(uf)) //while we are dealing with uName, if he doesn't follow uf (the users in his line of follows), do the following
               {
                  this.follows.add(validateUser(uf)); //Add the users which this user follows to his follow queue, validate them
               }//Else do nothing, already follows
            }
         }
         else //if the list is not comma seperated, there is a single user in the follow list
         {
             if(!this.follows.contains(line)) //while we are dealing with uName, if he doesn't follow uf (the users in his line of follows), do the following
               {
                  this.follows.add(validateUser(line)); //Add the users which this user follows to his follow queue, validate them
               }//Else do nothing, already follows
         }
    }
    
    
    public String validateUser(String name) //can be public as we do not call it directly from the constructor
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
    
}
