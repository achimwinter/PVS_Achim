package assignment8.data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserManager {

    private List<User> users = new LinkedList<>();
    private Integer id = 0;

    private static volatile UserManager instance;
    private static Object mutex = new Object();

    private UserManager() {
    }

    public static UserManager getInstance() {
        UserManager result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new UserManager();
            }
        }
        return result;
    }

    public void createUser(){
        users.add(new User(id));
        ++id;
    }

    public User getUser(Integer id){
        return users.get(id.intValue());
    }

    public void deleteUser(Integer id){
        users.remove(id);
    }

}
