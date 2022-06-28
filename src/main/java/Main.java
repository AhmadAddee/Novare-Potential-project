import bank.Bank;
import bank.PasswordX2;
import bank.session.Session;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        printBanner();
        Bank b = new Bank();

        System.out.println("""
                [1] Login
                [2] Sign up
                """);

        System.out.print("Option: ");

        //TODO Add check for out of bound option selection
        int option = scanner.nextInt();
        scanner.nextLine(); // <--- Dummy scan to reset the cursor after reading an int.

        Session s = null;

        if(option == 1){
            s = login(b);
        } else if (option == 2){
            s = signup(b);
        }


        //TODO fix handling of invalid session
        assert s != null;
        s.first();

        while (!s.isDone()) {
            s.loop();
        }
    }

    private static Session login(Bank bank){
        System.out.println("-- Login");

        String username = getUsername();
        String password = getPassword();

        var session = bank.signIn(username, password);

        if(session.isEmpty())
            return login(bank);

        return session.get();
    }

    private static Session signup(Bank bank){
        System.out.println("-- Sign up");

        String username = getUsername();
        String fullName = getFullName();
        PasswordX2 passwordX2;

        boolean r;
        do {
            passwordX2 = new PasswordX2(scanner);
            r = passwordX2.isDifferent();
            if(r)
                System.out.println("Whoops... you have entered two different passwords");
        } while(r);

        boolean success =
                passwordX2.getPass().isPresent() &&
                bank.signUp(username,fullName,passwordX2.getPass().get());  // <--- Won't run if getPass returns empty
        if(!success)
            return signup(bank);
        else
            return login(bank);
    }

    private static String getUsername() {
        System.out.print("Username: ");
        return scanner.nextLine();
    }

    private static String getFullName(){
        System.out.print("Full name: ");
        return scanner.nextLine();
    }

    private static String getPassword() {
        //TODO Hide the password so it is not printed to the CLI.

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
