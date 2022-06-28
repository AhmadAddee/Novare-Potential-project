package bank.user;

import java.util.HashMap;
import java.util.Optional;

public class UserDB {

    private static UserDB SINGLETON_INSTANCE;

    private final HashMap<Integer, User> idToUser = new HashMap<>();
    private final HashMap<String, User> usernameToUser = new HashMap<>();

    public static UserDB getInstance(){
        if(UserDB.SINGLETON_INSTANCE == null)
            SINGLETON_INSTANCE = new UserDB();
        return UserDB.SINGLETON_INSTANCE;
    }

    private UserDB() {
        initMockupUsers();
    }

    /**
     * A method for getting a user based on a given ID.
     * @param id The id of the user
     * @return Either a user object or empty, if there was no
     */
    public Optional<User> get(int id){
        return Optional.ofNullable(idToUser.get(id));
    }

    public Optional<User> get(String username){
        return Optional.ofNullable(usernameToUser.get(username));
    }

    public void createUser(String username,String fullName, String password){
        assert !this.containsUser(username);

        User user = new User(username,fullName,password);
        idToUser.put(idToUser.size(), user);
        usernameToUser.put(user.getUsername(),user);
    }


    public boolean containsUser(String username){
        return usernameToUser.containsKey(username);
    }

    public int getNumberOfUsers(){
        return this.idToUser.size();
    }

    private void initMockupUsers(){
        User[] users = {
                new User("adam","Adam Bower","1234"),
                new User("xX_Adam_Xx","Adam Hunter","4321"),
                new User("__ADMA__","Adam Cruise","1343")
        };

        for (User user: users) {
            idToUser.put(idToUser.size(), user);
            usernameToUser.put(user.getUsername(),user);
        }
    }
}
