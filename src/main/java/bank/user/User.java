package bank.user;

import jdk.jshell.spi.ExecutionControl;

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
    protected boolean withdraw(int amount){
        if(amount > this.balance)
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
    protected  boolean deposit(int amount){
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
    protected boolean transfer(int amount, User receiver){
        if(amount < 0 || this.balance < 0)
            return false;

        this.balance -= amount;
        receiver.balance += amount;

        return true;
    }


    protected boolean updateUsername(String newUsername){
        //TODO check for name collision
        this.username = newUsername;
        return true;
    }

    protected boolean updatePassword(Password newPassword){
        this.password = newPassword;
        return true;
    }

    protected String getUsername(){
        return username;
    }

    protected String getFullName(){
        return fullName;
    }

    public boolean checkPassword(String password){
        return this.password.compare(password);
    }

}
