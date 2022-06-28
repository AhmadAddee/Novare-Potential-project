package bank.user;

public class User {
    private String username;
    private final String fullName;
    private Password password;

    int balance;

    protected User(String username, String fullName, String password){
        this.username = username;
        this.fullName = fullName;
        this.password = new Password(password);

        this.balance = 1000; // <-- Starting balance
    }

    /**
     * Method for simulating the user withdrawing money from the ATM.
     * This method should only be called from the Session object.
     * @param amount The amount of money to withdraw
     * @return True if successful, False if the balance < amount
     */
    public boolean withdraw(int amount){
        if(amount > this.balance || amount < 0)
            return false;

        this.balance -= amount;
        return true;
    }

    /**
     * Method for simulating the user depositing money to the ATM.
     * This method should only be called from the Session object.
     * @param amount The amount of money the user have deposited into the ATM.
     * @return True if successful. False if an illegal format of amount was used.
     */
    public boolean deposit(int amount){
        if(amount <= 0)
            return false;

        this.balance += amount;
        return true;
    }

    /**
     * Method for simulating a user transferring money from their own account to another local user.
     * This method should only be called from the Session object.
     * @param amount The amount of money the user is transferring.
     * @param receiver The user that will recive the money.
     * @return True if successful. False if the amount is negative.
     */
    public boolean transfer(int amount, User receiver){
        if(amount < 0 || this.balance < 0)
            return false;

        this.balance -= amount;
        receiver.balance += amount;

        return true;
    }


    public boolean updateUsername(String newUsername){
        //TODO check for name collision
        this.username = newUsername;
        return true;
    }

    public boolean updatePassword(String newPassword){
        this.password = new Password(newPassword);
        return true;
    }

    public String getUsername(){
        return username;
    }

    public String getFullName(){
        return fullName;
    }

    public int getBalance(){
        return this.balance;
    }


    public boolean checkPassword(String password){
        return this.password.compare(password);
    }

}
