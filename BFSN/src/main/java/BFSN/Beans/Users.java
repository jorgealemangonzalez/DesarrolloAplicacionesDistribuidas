package BFSN.Beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Lista de Usuarios guardados en un HashMap
 * identificados por su único número de telefono
 * @author jorgeAleman
 */
public class Users {
    private HashMap<String,User> users;
    
    public Users(){
        this.users = new HashMap<String,User>();
    }
    
    public Users(List<User> users) {
        this();
        this.setUsers(users);
    }
    
    public void addUser(User user){
        users.put(user.getPhoneNumber(),user);
    }
    
    public List<User> getUsers() {
        return new ArrayList<User>(this.users.values());
    }

    public void setUsers(List<User> users) {
        this.users.clear();
        for(User user :  users){
            this.addUser(user);
        }
    }

    @Override
    public String toString() {
        String Susers = "[ ";
        int i = 0;
        for(User user : users.values()){
            Susers += user.toString();
            if(i != users.size()-1)
                Susers += " , ";
            i++;
        }
        Susers += " ]";
        return "Users{" + "users=" + Susers + '}';
    }
    
    public User findByPhone(String phonenumber){
        return users.get(phonenumber);
    }
}
