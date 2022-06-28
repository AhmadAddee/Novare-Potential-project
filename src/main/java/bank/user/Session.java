package bank.user;

import java.util.Scanner;

public class Session {
    private final User user;
    private boolean done = false;
    private final Scanner scanner = new Scanner(System.in);


    protected Session(User user){
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
            case '1' -> withdraw();
            case '4' -> viewBalance();
            case 'h' -> printOptions();
            case 'q' -> done = true;
            default -> invalidOption();
        }
    }

    private char getOption() {
        System.out.print("Option: ");
        return scanner.next().charAt(0);
    }

    private void withdraw(){
        System.out.println(
                "You are about to withdraw money from your account \n" +
                "How much would you like to withdraw?"
        );

        int amount = 0;

        do {
            System.out.print("Amount: ");
            if(scanner.hasNextInt())
                amount = scanner.nextInt();
            else
                System.out.println("Please enter a number");
        }while (amount == 0);

        boolean r = this.user.withdraw(amount);

        if(!r){
            System.out.println(
                    "Could not process the transaction: to small balance on account"
            );
        }
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
                "Welcome " + this.user.getFullName() +
                "\n";

        System.out.println(welcome);
    }
}
