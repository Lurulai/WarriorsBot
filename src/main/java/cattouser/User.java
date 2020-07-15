package cattouser;

import cattos.Catto;

import java.util.ArrayList;

public class User {
    private String userID;
    private ArrayList<Catto> cattos;

    public User(String userID){
        this.userID = userID;
        this.cattos = new ArrayList<>();
    }

    public User(User u){
        this.userID = u.getUserID();
        this.cattos = new ArrayList<>(u.getCattos());
    }

    public ArrayList<Catto> getCattos(){
        return cattos;
    }

    public boolean addCatto(Catto catto){
        if(cattos.size() < 4){
            cattos.add(catto);
            return true;
        }
        return false;
    }

    public String getUserID(){
        return userID;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }
}
