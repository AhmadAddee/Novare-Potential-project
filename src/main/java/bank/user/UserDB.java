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

    public UserQuery performActionOn(String username){
        return new UserQuery(username);
    }
    public UserQuery performActionOn(int userID){
        return new UserQuery(userID);
    }


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

        private boolean transfer(int amount, String receiverUsername){
            var receiver = Optional.ofNullable(usernameToUser.get(receiverUsername));
            return transfer(amount, receiver);
        }

        public boolean transferSelfMatch(int amount, String usernameOrId){
            try {
                int id = Integer.parseInt(usernameOrId);
                return transfer(amount,id);
            }catch (NumberFormatException e) {
                return transfer(amount,usernameOrId);
            }
        }

        private boolean transfer(int amount, int receiverUserID){
            var receiver = Optional.ofNullable(idToUser.get(receiverUserID));
            return transfer(amount,receiver);
        }

        private boolean transfer(int amount, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<User> receiver){
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
