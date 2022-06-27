package bank;

import java.util.HashMap;
import java.util.Optional;

public class UserDB {
    private final HashMap<Integer, User> idToUser = new HashMap<>();
    private final HashMap<String, User> usernameToUser = new HashMap<>();

    public UserDB() {
        initMockupUsers();
    }

    private Optional<User> get(int id){
        return Optional.ofNullable(idToUser.get(id));
    }

    public Optional<User> get(String username){
        return Optional.ofNullable(usernameToUser.get(username));
    }

    public void addUser(User user){
        idToUser.put(idToUser.size(), user);
        usernameToUser.put((user.username),user);
    }

    public boolean containsUser(String username){
        return usernameToUser.containsKey(username);
    }

    private void initMockupUsers(){
        User[] users = {
                new User("adam","Adam Bower","1234"),
                new User("xX_Adam_Xx","Adam Hunter","4321"),
                new User("__ADMA__","Adam Cruise","1343")
        };

        for (User user: users) {
            addUser(user);
        }
    }
}
