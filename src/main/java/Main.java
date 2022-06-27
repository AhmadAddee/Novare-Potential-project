import bank.Bank;
import bank.Session;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        printBanner();
        Bank b = new Bank();

        System.out.println("Please login... \n");

        String username = getUsername();
        String pin = getPassword();

        Session s = b.signIn(username, pin).get();

        s.first();

        while (s.isDone()) {
            s.loop();
        }
    }

    private static String getUsername() {
        System.out.print("Username: ");
        return scanner.nextLine();
    }

    private static String getPassword() {
        System.out.print("Password: ");
        return scanner.nextLine();
    }

    private static void printBanner() {
        String banner = """
                 ________  _________  _____ ______           ________  ________  ________      \s
                |\\   __  \\|\\___   ___\\\\   _ \\  _   \\        |\\   __  \\|\\   __  \\|\\   __  \\     \s
                \\ \\  \\|\\  \\|___ \\  \\_\\ \\  \\\\\\__\\ \\  \\       \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\    \s
                 \\ \\   __  \\   \\ \\  \\ \\ \\  \\\\|__| \\  \\       \\ \\   __  \\ \\   ____\\ \\   ____\\   \s
                  \\ \\  \\ \\  \\   \\ \\  \\ \\ \\  \\    \\ \\  \\       \\ \\  \\ \\  \\ \\  \\___|\\ \\  \\___|   \s
                   \\ \\__\\ \\__\\   \\ \\__\\ \\ \\__\\    \\ \\__\\       \\ \\__\\ \\__\\ \\__\\    \\ \\__\\      \s
                    \\|__|\\|__|    \\|__|  \\|__|     \\|__|        \\|__|\\|__|\\|__|     \\|__|      \s
                                                                                               \s
                """;

        System.out.println(banner);
    }
}
