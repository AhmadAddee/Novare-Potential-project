package bank.session;

import bank.Bank;

import java.util.Scanner;

interface SessionUtil {
    Scanner scanner = new Scanner(System.in);

    static int readInt(String message){
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

    static char getOption() {
        System.out.print("Option: ");
        return scanner.next().charAt(0);
    }

    static void printWelcome(Session session, Bank bank) {
        var fullName = bank.performAction(session).getFullName();
        assert fullName.isPresent();

        String welcome =
                "\n" +
                "Welcome " + fullName.get() +
                "\n";
        System.out.println(welcome);
    }

    static void printOptions() {
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

    static void invalidOption(){
        System.out.println("Invalid option. Use [h] to show what options are available");
    }

    static void printUserList(){
        String userIdMapping = Bank.getInstance().getUserIdMapping();
        System.out.println(userIdMapping);
    }

    static void waitForClick(){
        System.out.print("Operation done. Click enter to continue...");
        scanner.nextLine(); // Clear buffer first.
        scanner.nextLine();
    }

    static void handle(boolean result, String onSuccess, String onError){
        String msg = result?onSuccess:onError;
        System.out.println(msg);
    }
}
