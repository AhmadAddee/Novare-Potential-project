package bank.user;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class UserDB {

    private final static UserDB SINGLETON_INSTANCE = new UserDB();

    private final HashMap<Integer, User> idToUser = new HashMap<>();
    private final HashMap<String, User> usernameToUser = new HashMap<>();

    /**
     * A method for getting the UserDB SINGLETON.
     * @return The UserDB SINGLETON
     */
    public static UserDB getInstance(){
        return UserDB.SINGLETON_INSTANCE;
    }

    private UserDB(){
        initMockupUsers();
    }

    /**
     * Method for testing
     */
    protected static UserDB createMockup() {
        return new UserDB();
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

    /**
     * Method for creating a new user
     * @return True if succesfull, false if username is already in use.
     */
    public boolean createUser(String username,String fullName, String password){
        boolean usernameInUse = this.containsUser(username);
        boolean invalidUsername = !User.validUsername(username);

        if(usernameInUse || invalidUsername)
            return false;

        User user = new User(username,fullName,password);
        idToUser.put(idToUser.size(), user);
        usernameToUser.put(user.getUsername(),user);
        return true;
    }


    /**
     * Check whether the username is already in use.
     * @param username The username to compare against the DB
     * @return True if there is a already a user with that username.
     */
    public boolean containsUser(String username){
        return usernameToUser.containsKey(username);
    }

    /**
     * @return number of users in the DB.
     */
    public int getNumberOfUsers(){
        return this.idToUser.size();
    }

    protected boolean updateUsername(String previousUsername, String newUsername){
        User u = usernameToUser.get(previousUsername);
        usernameToUser.remove(previousUsername);

        return Objects.equals(usernameToUser.put(newUsername, u), u);
    }

    /**
     * Method for testing.
     * Used to create users.
     */
    private void initMockupUsers(){
        try {
            User[] users = {
                    new User("adam","Adam Bower","1234"),
                    new User("adamh","Adam Hunter","4321"),
                    new User("adamc","Adam Cruise","1343")
            };

            for (User user: users) {
                idToUser.put(idToUser.size(), user);
                usernameToUser.put(user.getUsername(),user);
            }
        }catch (IllegalArgumentException e) {
            System.out.println("INFO: Could not init mockup users!");
            System.out.println("INFO: Continuing without mockup users");
        }
    }

    /**
     * A method for manipulating the UserDB object.
     * An object is returned with options which can be performed.
     * @param username The username of the user trying to manipulate the DB
     * @return An object with access methods to manipulate the UserDB
     */
    public UserQuery performActionOn(String username){
        return new UserQuery(this, username);
    }

    /**
     * A method for manipulating the UserDB object.
     * An object is returned with options which can be performed.
     * @param userID The user id of the user trying to manipulate the DB
     * @return An object with access methods to manipulate the UserDB
     */
    public UserQuery performActionOn(int userID){
        return new UserQuery(this, userID);
    }
}
