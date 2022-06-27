package bank;

public class User {
    String username;
    String fullName;
    String pin;

    int balance;

    public User(String username, String fullName, String pin){
        this.username = username;
        this.fullName = fullName;
        this.pin = pin;

        this.balance = 1000; // <-- Starting balance
    }

    protected boolean withdraw(int amount){
        //TODO: Check for negative numbers
        this.balance -= amount;
        return true;
    }

    protected  boolean deposit(int deposit){
        //TODO: Check so only positive numbers are applied
        this.balance += deposit;
        return true;
    }

    protected  boolean transfer(int amount, User receiver){
        //TODO:Add check for amount...
        this.balance -= amount;
        receiver.balance += amount;

        return true;
    }

}
