package bank;
import bank.session.Session;
import bank.user.*;

import java.util.HashMap;
import java.util.Optional;

public class Bank {
    private final static Bank bank = new Bank();
    private final UserDB userDB = UserDB.getInstance();

    //There will only be one user at the time signed in for this simulation. i.e using a hashmap is totally overkill since there will only be one entry at a time.
    //But this is used to visually-"emulate" the possibility of multiple active sessions at the same time.
    private final HashMap<Session, String> sessionUsernameMapping = new HashMap<>();

    protected Bank(){}

    public static Bank getInstance(){
        return bank;
    }

    /**
     * A method for signing in to the system
     * @param username The username of a user in the system.
     * @param password The password for the user.
     * @return A session object which is used for communicating different operations to the bank. Will return Empty if either username or password was wrong.
     */
    public Optional<Session> login(String username, String password) {
        var user = userDB.get(username);

        boolean passwordCheck = userDB.performActionOn(username).checkPassword(password);
        if(!passwordCheck)
            return Optional.empty();

        Session session = new Session(this);
        sessionUsernameMapping.put(session,username);
        return Optional.of(session);
    }

    /**
     * A method for creating new users.
     * @param username Username of the user.
     * @param fullName the full name of the user.
     * @param password the password which the user has chosen.
     * @return True if the user was added to the system. False if username is already in use.
     */
    public boolean signUp(String username, String fullName, String password){
        return userDB.createUser(username,fullName,password);
    }

    /**
     * Removes the session object from storage.
     * The session object cannot longer be used.
     * @param session Session to be logout
     */
    public void signout(Session session){
        sessionUsernameMapping.remove(session);
    }

    public String getUserIdMapping(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < userDB.getNumberOfUsers(); i++) {
           var username = userDB.performActionOn(i).getUsername();
           assert username.isPresent();

            sb.append("[")
                    .append(i)
                    .append("] ")
                    .append(username.get())
                    .append("\n");
        }

        return sb.toString();
    }

    /**
     * A user can interact with the bank app through this method.
     * @param session The session of which is trying to perform a transaction.
     * @return An object with options and actions which the user can perform.
     */
    public UserQuery performAction(Session session) {
        String username = sessionUsernameMapping.get(session);
        return userDB.performActionOn(username);
    }
}