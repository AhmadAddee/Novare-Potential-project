package bank.user;

import java.util.HashMap;
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
        SINGLETON_INSTANCE.initMockupUsers();
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
        if(this.containsUser(username))
            return false;

        assert !this.containsUser(username);
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

    /**
     * Method for testing.
     * Used to create users.
     */
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

    /**
     * A method for manipulating the UserDB object.
     * An object is returned with options which can be performed.
     * @param username The username of the user trying to manipulate the DB
     * @return An object with access methods to manipulate the UserDB
     */
    public UserQuery performActionOn(String username){
        return new UserQuery(username);
    }

    /**
     * A method for manipulating the UserDB object.
     * An object is returned with options which can be performed.
     * @param userID The user id of the user trying to manipulate the DB
     * @return An object with access methods to manipulate the UserDB
     */
    public UserQuery performActionOn(int userID){
        return new UserQuery(userID);
    }


    /**
     * A class containing methods for manipulating the UserDB.
     * Most methods are only wrapping methods and functionality of the underlying User class.
     * This is to reduce coupling
     */
    public class UserQuery {

        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        private final Optional<User> user;

        protected UserQuery(String username){
            user = Optional.ofNullable(usernameToUser.get(username));
        }

        protected UserQuery(int userID) {
            user = Optional.ofNullable(idToUser.get(userID));
        }

        public boolean withdraw(int amount){
            return user.isPresent() && user.get().withdraw(amount);
        }

        public boolean deposit(int amount){
            return user.isPresent() && user.get().deposit(amount);
        }

        /**
         * Performs a transfer transaction.
         * @param amount Amount to be transferred
         * @param usernameOrId A string with either the username or id of the recipient.
         * @return True if successful, false otherwise.
         */
        public boolean transferSelfMatch(int amount, String usernameOrId){
            Optional<User> receiver;

            try {
                int id = Integer.parseInt(usernameOrId);
                receiver = Optional.ofNullable(idToUser.get(id));
            }catch (NumberFormatException e) {
                // Couldn't parse input as int, then assume the input to be a username.
                receiver = Optional.ofNullable(usernameToUser.get(usernameOrId));
            }

            return user.isPresent()
                    && receiver.isPresent()
                    && user.get().transfer(amount,receiver.get());
        }

        public boolean updateUsername(String newUsername){
            boolean alreadyExists = usernameToUser.containsKey(newUsername);
            return !alreadyExists
                    && user.isPresent()
                    && user.get().updateUsername(newUsername);
        }

        public boolean updatePassword(String newPassword){
            return user.isPresent() && user.get().updatePassword(newPassword);
        }

        public Optional<String> getUsername(){
            if(user.isEmpty())
                return Optional.empty();
            return Optional.of(user.get().getUsername());
        }

        public Optional<String> getFullName(){
            if(user.isEmpty())
                return Optional.empty();
            return Optional.of(user.get().getFullName());
        }

        public Optional<Integer> getBalance(){
            if(user.isEmpty())
                return Optional.empty();
            return Optional.of(user.get().getBalance());
        }

        public boolean checkPassword(String password){
            return user.isPresent() && user.get().checkPassword(password);
        }

    }
}
