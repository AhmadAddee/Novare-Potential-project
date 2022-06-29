package bank.user;

import java.util.Optional;

/**
 * A class containing methods for manipulating the UserDB.
 * Most methods are only wrapping methods and functionality of the underlying User class.
 * This is to reduce coupling
 */
public class UserQuery {

    private final UserDB userDB;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<User> user;

    protected UserQuery(UserDB userDB, String username) {
        this.userDB = userDB;
        user = userDB.get(username);
    }

    protected UserQuery(UserDB userDB, int userID) {
        this.userDB = userDB;
        user = userDB.get(userID);
    }

    public boolean withdraw(int amount) {
        return user.isPresent() && user.get().withdraw(amount);
    }

    public boolean deposit(int amount) {
        return user.isPresent() && user.get().deposit(amount);
    }

    /**
     * Performs a transfer transaction.
     *
     * @param amount       Amount to be transferred
     * @param usernameOrId A string with either the username or id of the recipient.
     * @return True if successful, false otherwise.
     */
    public boolean transferSelfMatch(int amount, String usernameOrId) {
        Optional<User> receiver;

        try {
            int id = Integer.parseInt(usernameOrId);
            receiver = userDB.get(id);
        } catch (NumberFormatException e) {
            // Couldn't parse input as int, then assume the input to be a username.
            receiver = userDB.get(usernameOrId);
        }

        return user.isPresent()
                && receiver.isPresent()
                && user.get().transfer(amount, receiver.get());
    }

    public boolean updateUsername(String newUsername) {
        boolean alreadyExists = userDB.containsUser(newUsername);
        return !alreadyExists
                && user.isPresent()
                && user.get().updateUsername(newUsername);
    }

    public boolean updatePassword(String newPassword) {
        return user.isPresent() && user.get().updatePassword(newPassword);
    }

    public Optional<String> getUsername() {
        if (user.isEmpty())
            return Optional.empty();
        return Optional.of(user.get().getUsername());
    }

    public Optional<String> getFullName() {
        if (user.isEmpty())
            return Optional.empty();
        return Optional.of(user.get().getFullName());
    }

    public Optional<Integer> getBalance() {
        if (user.isEmpty())
            return Optional.empty();
        return Optional.of(user.get().getBalance());
    }

    public boolean checkPassword(String password) {
        return user.isPresent() && user.get().checkPassword(password);
    }

}
