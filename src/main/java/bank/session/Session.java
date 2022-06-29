package bank.session;

import bank.Bank;

import static bank.session.SessionActions.*;
import static bank.session.SessionUtil.*;

public class Session{
    private final Bank bank;
    private boolean done = false;

    /**
     * @param bank The bank object which this session originates from.
     */
    public Session(Bank bank){
        this.bank = bank;
    }

    /**
     * Prints a small welcome banner for the user
     */
    public void first() {
        printWelcome(this, bank);
    }

    /**
     * A method for the main menu. This is meant to be looped in a while-loop until the user is done.
     */
    public void loop(){
        System.out.println("-----   Main Menu   -----");
        printOptions();
        char userInput = getOption();

        switch (userInput) {
            case '1' -> withdraw(bank,this);
            case '2' -> deposit(bank,this);
            case '3' -> transfer(bank,this);
            case '4' -> viewBalance(bank,this);
            case '5' -> updateUsername(bank,this);
            case '6' -> updatePassword(bank,this);
            case '7' -> viewProfile(bank,this);
            case 'h' -> printOptions();
            case 'q' -> {
                done = true;
                bank.signout(this);
            }
            default -> invalidOption();
        }
    }

    /**
     * Method for checking if the user is done. I.E want to log out.
     * @return true if the user wants to log out. false if the user wants to continue.
     */
    public boolean isDone() {
        return done;
    }
}


