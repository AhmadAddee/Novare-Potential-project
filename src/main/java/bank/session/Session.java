package bank.session;

import bank.user.User;

import static bank.session.SessionActions.*;
import static bank.session.SessionUtil.*;

public class Session{
    private final User user;
    private boolean done = false;

    public Session(User user){
        this.user = user;
    }

    public void first() {
        printWelcome(user);
    }

    public void loop(){
        System.out.println("-----   Main Menu   -----");
        printOptions();
        char userInput = getOption();

        switch (userInput) {
            case '1' -> withdraw(this.user);
            case '2' -> deposit(this.user);
            case '3' -> transfer(this.user);
            case '4' -> viewBalance(this.user);
            case '5' -> updateUsername(this.user);
            case '6' -> viewProfile(this.user);
            case 'h' -> printOptions();
            case 'q' -> done = true;
            default -> invalidOption();
        }
    }

    public boolean isDone() {
        return done;
    }
}


