package BFSN.Beans;


import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorgeAleman
 */
public class Users {
    private List<User> users;//TODO maybe implement hashmap phone -> user
    
    public Users(List<User> users) {
        this.users = users;
    }
    
    public Users(){
        this.users = new ArrayList<User>();
    }
    
    public void addUser(User user){
        users.add(user);
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        String Susers = "[ ";
        for(int i = 0 ; i < users.size() ; ++i){
            Susers += users.get(i).toString();
            if(i != users.size()-1)
                Susers += " , ";
        }
        Susers += " ]";
        return "Users{" + "users=" + Susers + '}';
    }
    
    public User findByPhone(String phonenumber){
        for(User user : users)
            if(user.getPhoneNumber().equals(phonenumber))
                return user;
        return null;
    }
}
