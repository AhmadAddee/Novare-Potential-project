package bank.session;

import bank.Bank;

import java.util.Optional;
import java.util.Scanner;

/**
 * An interface containing helper methods for basic IO operations in the session object.
 */
interface SessionUtil {
    static Optional<Integer> readInt(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String msg = scanner.nextLine();
        System.out.println(msg);
        try{
            return Optional.of(Integer.parseInt(msg));
        }catch (Exception e) {
            return Optional.empty();
        }
    }

    static char getOption() {
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Operation done. Click enter to continue...");
        scanner.nextLine();
    }

    static void handle(boolean result, String onSuccess, String onError){
        String msg = result?onSuccess:onError;
        System.out.println(msg);
    }
}
