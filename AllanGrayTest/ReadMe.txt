NOTE: you will need Java 8 to run this solution

**************************************************************************************************
TO RUN THIS SOLUTION:

1.) Please download the AllanGrayTest folder from the repo
2.) Navigate to the src folder -> then into the allangraytest folder and find the TwitterFeed.bat file -> double click this to run my solution with my inputs
3.) Alternatively type java -jar TwitterFeed.jar into the command line to run the .jar file
4.) If you would like to supply your own inputs to the program (keeping in mind they must be user.txt and tweet.txt) then please add them to the
    src folder after removing the current ones -> type java TwitterFeed into the command line to run the compiled TwitterFeed class file.
**************************************************************************************************

This solution was designed using Test Driven Development. The test classes are included in the submitted folder.All tests pass for the current solution.
I used JUnit as a testing framework which NetBeans has a plugin for - NetBeans uses packages which have to be removed to compile and run the solution from the command line.
In order to run the tests, insert package allangraytest; at the top of User.java, Tweet.java and TwitterFeed.java and then compile and run the test cases in the same manner as below.
In order to run the tests on .validateUser() method, it is required to make the validateUser() method public in the User class so the userTest class can access it.
Essentially, I used testing and I would like you to see this, but I did not include functionality for you, the "examiner", to run these tests. The above description is just in the case that you
would like to run these tests for some reason


Reading in files


1.) A scanner was chosen to read in files, by accessing them by name. I am told I will be supplied with two user files, user.txt and follow.txt so I reference these directly.
    In another case, I would implement file input by using arguments.
2.) All users are read in and instantiated before any tweets, this ensures that no user's tweet is unassigned

Data structures

1.) a TreeMap was the data structure of choice for storing the user objects as we can use their names a keys, which TreeMap automatically sorts in ascending order
    although hashmap provides constant time for put and get methods, treemap provides log(n) AND is sorted, which makes it the obvious choice.
2.) LinkedList used for the tweets so they too appear in order, but do not require the same lookup functionality as a user, so treemap not needed
    

Assumptions:

1.) Users follow themselves by default
2.) If a tweet appears from a name not in the user's text file at all, the tweet will be deleted on the premise of the user being unknown
3.) Lines in user or tweet file that are not in the correct format are deleted
4.) Names cannot contain symbols or numbers, any that do will be formatted to contain only letters
5.) Names cannot contain mixed case. All names incorrectly cased will be formatted to the correct case (first uppercase, rest lowercase)
6.) I thought of implementing a "retweet" functionality, however, the way the text files are set up, if the same tweet occurs twice, this would equate to a user entering the same string twice,
    and not clicking a "retweet" button, so for fear of failing test cases, I will not implement it and as such, I assume duplicate tweets are allowed.
 