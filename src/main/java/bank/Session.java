package bank;

import bank.user.User;
import bank.user.UserDB;

import java.util.Optional;
import java.util.Scanner;

public class Session {
    private final User user;
    private boolean done = false;

    protected Session(User user){
        this.user = user;
    }

    public void first() {
        BasicMsg.printWelcome(user);
    }

    public void loop(){
        System.out.println("-----   Main Menu   -----");
        BasicMsg.printOptions();
        char userInput = util.getOption();

        switch (userInput) {
            case '1' -> BasicOp.withdraw(this.user);
            case '2' -> BasicOp.deposit(this.user);
            case '3' -> BasicOp.transfer(this.user);
            case '4' -> BasicOp.viewBalance(this.user);
            case '5' -> BasicOp.updateUsername(this.user);
            case '6' -> BasicOp.updatePassword(this.user);
            case '7' -> BasicOp.viewProfile(this.user);
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
                    [5] Edit username\s
                    [6] Edit password\s
                    [7] View profile\s
                    [q] to quit\s
                    [h] for this menu\s
                    """;

            System.out.println(options);
        }

        public static void invalidOption(){
            System.out.println("Invalid option. Use [h] to show what options are available");
        }

        public static void printUserList(){
            final UserDB userDB = UserDB.getInstnace();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < userDB.getNumberOfUsers(); i++) {
                var tmpUser = userDB.get(i);
                assert tmpUser.isPresent();

                String username = tmpUser.get().getUsername();

                sb.append("[")
                    .append(i)
                    .append("] ")
                    .append(username)
                    .append("\n");
            }

            System.out.println(sb.toString());
        }
    }

    private static class BasicOp{
        private static final Scanner scanner = new Scanner(System.in);

        public static void deposit(User user){
            int amount = util.readInt("""
                You are about to deposit money into your account
                How much would you like to deposit?""");

            handle(
                    user.deposit(amount),
                    "Sucess! View your balance to see the change.",
                    "Could not deposit to account. Only positive numbers are allowed"
            );

            waitForClick();
        }

        public static void withdraw(User user){
            int amount = util.readInt("""
                You are about to withdraw money from your account.
                How much would you like to withdraw?
                """);

            handle(
                    user.withdraw(amount),
                    "Sucess! You have now withdrawn money from you account. View your balance to see the changes",
                    "Could not process the transaction. Do you have enough funds on your account?"
            );

            waitForClick();
        }

        public static void viewProfile(User user){
            StringBuilder sb = new StringBuilder();

            sb.append("-- Your profile --\n\n")
                    .append("Username : ")
                        .append(user.getUsername())
                        .append("\n")
                    .append("Full name : ")
                        .append(user.getFullName())
                        .append('\n');

            System.out.println(sb.toString());

            waitForClick();
        }

        public static void viewBalance(User user){
            System.out.println("Your balance is " + user.getBalance());
            waitForClick();
        }

        public static void transfer(User user){
            final UserDB userDB = UserDB.getInstnace();
            BasicMsg.printUserList();

            System.out.print("Enter either a username or the corresponding [id]: ");

            Optional<User> receiver = scanner.hasNextInt()?
                    userDB.get(scanner.nextInt()):
                    userDB.get(scanner.next());

            int amount = util.readInt("""
                    You are about to transfer money to another user.
                    Please enter an amount to transfer: 
                    """);

            handle(
                    receiver.isPresent() &&
                    user.transfer(amount, receiver.get()),
                    "Success! Money have now been transferred. " + receiver.get().getUsername() + " now has " + receiver.get().getBalance(),
                    "Could not transfer the money."
            );

            waitForClick();
        }

        public static void updateUsername(User user){
            System.out.print("New username: ");
            //TODO: add check for valid username
            String name = scanner.nextLine();

            handle(
                    user.updateUsername(name),
                    "Success! Username changed.",
                    "Could not change username. Either the username already exists or the provided username was invalid."
            );

            waitForClick();
        }

        private static void updatePassword(User user){
            System.out.print("New password: ");
            String password = scanner.nextLine();

            handle(
                    user.updatePassword(password),
                    "Success! Password changed.",
                    "Could not change Password."
            );

            waitForClick();
        }

        private static void waitForClick(){
            System.out.println("Operation done. Click enter to continue...");
            scanner.nextLine();
        }

        private static void handle(boolean result, String onSuccess, String onError){
            String msg = result?onSuccess:onError;
            System.out.println(msg);
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
