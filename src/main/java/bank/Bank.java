package bank;
import java.util.Optional;

public class Bank {

    private final UserDB userDB = new UserDB();

    public Optional<Session> signIn(String username, String password) {
        Optional<User> user = userDB.get(username);

        if(user.isPresent() && user.get().checkPassword(password))
            return Optional.of(new Session(user.get()));

        return Optional.empty();
    }

    public boolean signUp(String username, String fullName, String password){
        boolean r = userDB.containsUser(username);
        if(r)
            return false;

        User user = new User(username,fullName,password);
        userDB.addUser(user);

        return true;
    }


}
