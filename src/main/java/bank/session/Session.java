package bank.session;

import bank.Bank;

import static bank.session.SessionActions.*;
import static bank.session.SessionUtil.*;

public class Session{
    private final Bank bank;
    private boolean done = false;

    public Session(Bank bank){
        this.bank = bank;
    }

    public void first() {
        printWelcome(this, bank);
    }

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

    public boolean isDone() {
        return done;
    }
}


