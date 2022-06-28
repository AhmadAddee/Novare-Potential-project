package bank;

import bank.user.User;

import java.util.Scanner;

public class Session {
    private final User user;
    private boolean done = false;

    protected Session(User user){
        this.user = user;
    }

    public void first() {
        BasicMsg.printWelcome(user);
        BasicMsg.printOptions();
    }

    public void loop(){
        //TODO:Implement the rest of the user actions

        char userInput = util.getOption();

        switch (userInput) {
            case '1' -> BasicOp.withdraw(this.user);
            case '2' -> BasicOp.deposit(this.user);
            case '4' -> BasicOp.viewBalance(this.user);
            case 'h' -> BasicMsg.printOptions();
            case 'q' -> done = true;
            default -> BasicMsg.invalidOption();
        }
    }

    public boolean isDone() {
        return done;
    }

    private static class BasicMsg{
        public static void printWelcome(User user) {
            String welcome =
                    "\n" +
                    "Welcome " + user.getFullName() +
                    "\n";
            System.out.println(welcome);
        }

        public static void printOptions() {
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

        public static void invalidOption(){
            System.out.println("Invalid option. Use [h] to show what options are available");
        }
    }

    private static class BasicOp{
        public static void deposit(User user){
            int amount = util.readInt("""
                You are about to deposit money into your account
                How much would you like to deposit?""");

            boolean r = user.withdraw(amount);

            if(!r){
                System.out.println(
                        "Could not process the transaction: to small balance on account"
                );
            }
        }

        public static void withdraw(User user){
            int amount = util.readInt("""
                You are about to withdraw money from your account.
                How much would you like to withdraw?
                """);

            boolean r = user.withdraw(amount);

            if(!r){
                System.out.println(
                        "Could not process the transaction: to small balance on account"
                );
            }
        }

        public static void viewBalance(User user){
            System.out.println("Your balance is " + user.getBalance());
        }
    }

    private static class util{
        private static final Scanner scanner = new Scanner(System.in);

        public static int readInt(String message){
            System.out.println(message);

            int amount = 0;

            do {
                System.out.print("Amount: ");
                if(scanner.hasNextInt())
                    amount = scanner.nextInt();
                else
                    System.out.println("Please enter a number");
            }while (amount == 0);

            return amount;
        }

        public static char getOption() {
            System.out.print("Option: ");
            return scanner.next().charAt(0);
        }
    }
}
