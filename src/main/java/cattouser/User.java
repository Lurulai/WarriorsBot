package cattouser;

import cattos.Catto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;

/**
 * A class to create a user based on their user id and the cats they have
 */
public class User {
    // User id to keep track of unique users
    private String userID;
    // List of cattos they have
    private ArrayList<Catto> cattos;

    /**
     * Default constructor
     */
    public User(){
        super();
        this.cattos = new ArrayList<>();
    }

    /**
     * Creates a new user given their userid in string
     * @param userID discord user id as a string
     */
    public User(String userID){
        this.userID = userID;
        this.cattos = new ArrayList<>();
    }

    /**
     * Creates a new deep copy of a user given their old user
     * @param u User class to deep copy
     */
    public User(User u){
        this.userID = u.getUserID();
        this.cattos = new ArrayList<>(u.getCattos());
    }

    /**
     * Gets the list of cats a user has.
     * @return ArrayList\<Catto\>
     */
    public ArrayList<Catto> getCattos(){
        return cattos;
    }

    /**
     * Adds a cat to the list of cats a user has.
     * @param catto adds a given cat to the list of cats
     * @return boolean
     */
    public boolean addCatto(Catto catto){
        if(cattos.size() < 4){
            cattos.add(catto);
            return true;
        }
        return false;
    }

    /**
     * Gets the user id of the user
     * @return String
     */
    public String getUserID(){
        return userID;
    }

    /**
     * Sets the user id of the user
     * @param userID discord user id as a string
     */
    public void setUserID(String userID){
        this.userID = userID;
    }

    /**
     * Given a new arraylist of cattos, it updates the arraylist with new
     * @param newCattos new arraylist to replace the old one with
     */
    public void setCatto(ArrayList<Catto> newCattos) {
        if(newCattos != null || newCattos.size() != 0){
            this.cattos = newCattos;
        }
    }
}
