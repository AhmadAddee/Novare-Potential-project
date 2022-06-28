package bank;
import bank.user.*;
import java.util.Optional;

public class Bank {

    private final UserDB userDB = new UserDB();

    /**
     * A method for signing in to the system
     * @param username The username of a user in the system.
     * @param password The password for the user.
     * @return A session object which is used for communicating different operations to the bank. Will return Empty if either username or password was wrong.
     */
    public Optional<Session> signIn(String username, String password) {
        Optional<User> user = userDB.get(username);

        if(user.isPresent() && user.get().checkPassword(password))
            return Optional.of(new Session(user.get()));

        return Optional.empty();
    }

    /**
     * A method for creating new users.
     * @param username Username of the user.
     * @param fullName the full name of the user.
     * @param password the password which the user has chosen.
     * @return True if the user was added to the system. False if username is already in use.
     */
    public boolean signUp(String username, String fullName, String password){
        boolean usernameAlreadyExists = userDB.containsUser(username);
        if(usernameAlreadyExists)
            return false;

        User user = new User(username,fullName,password);
        userDB.addUser(user);

        return true;
    }


}
