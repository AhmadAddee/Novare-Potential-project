package bank;

import java.util.Scanner;

public class Session {
    User user;

    boolean done = false;

    public Session(User user){
        this.user = user;
    }

    public void first() {
        printWelcome();
        printOptions();
    }

    public void loop(){
        //TODO:Implement the rest of the user actions

        char userInput = getOption();

        switch (userInput) {
            case '4' -> viewBalance();
            case 'h' -> printOptions();
            case 'q' -> done = true;
            default -> invalidOption();
        }
    }

    private char getOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Option: ");

        return scanner.next().charAt(0);
    }

    private void viewBalance(){
        System.out.println("Your balance is " + user.balance);
    }

    private void invalidOption(){
        System.out.println("Invalid option. Use [h] to show what options are available");
    }

    public boolean isDone() {
        return done;
    }

    private void printOptions() {
        String options =
                """
                [1] Withdraw\s
                [2] Deposit\s
                [3] Transfer\s
                [4] View balance\s
                [q] to quit\s
                [h] for this menu\s
                """;

        System.out.println(options);
    }


    private void printWelcome() {
        String welcome =
                "\n" +
                "Welcome " + this.user.fullName +
                "\n";

        System.out.println(welcome);
    }
}
