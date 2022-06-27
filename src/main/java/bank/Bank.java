package bank;

import java.util.HashMap;
import java.util.Optional;

public class Bank {
    HashMap<Integer, User> idToUser = new HashMap<>();
    HashMap<String, User> usernameToUser = new HashMap<>();

    public Bank(){
        initMockupUsers();
    }

    public Optional<Session> signIn(String username, String pin) {
        boolean containsUser = usernameToUser.containsKey(username);

        if(!containsUser)
            return Optional.empty();

        //TODO: Add check for pin

        return Optional.of(new Session(usernameToUser.get(username)));
    }

    public boolean signUp(String username, String fullName, String pin){
        //TODO: Check for collision in username
        User user = new User(username,fullName,pin);
        int id = idToUser.size();
        idToUser.put(id,user);

        return true;
    }


    private void initMockupUsers(){
        User u1 = new User("adam","Adam Bower","1234");
        User u2 = new User("xX_Adam_Xx","Adam Hunter","4321");
        User u3 = new User("__ADMA__","Adam Cruise","1343");

        idToUser.put(0,u1);
        usernameToUser.put((u1.username),u1);

        idToUser.put(1,u2);
        usernameToUser.put((u2.username),u2);

        idToUser.put(2,u3);
        usernameToUser.put((u3.username),u3);
    }
}
