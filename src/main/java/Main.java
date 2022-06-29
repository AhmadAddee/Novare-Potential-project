import bank.Bank;
import bank.PasswordX2;
import bank.session.Session;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args){
        TerminalIO.printBanner();

        //noinspection StatementWithEmptyBody
        while(menu());

        System.out.println("Bye.");
    }


    private static boolean menu(){
        boolean quit = false;
        System.out.println("""
                -----   Login Menu  -----
                [1] Login
                [2] Sign up
                [q] Quit
                """);

        System.out.print("Option: ");

        char userInput = TerminalIO.readOption();
        Optional<Session> session = Optional.empty();

        switch (userInput){
            case '1' -> session = onFailedWrapped(login());
            case '2' -> session = onFailedWrapped(signup());
            case 'q' -> quit = true;
            default -> System.out.println("Whoops. Invalid option!");
        }

        while (session.isPresent() && !session.get().isDone()){
            session.get().loop();
        }

        return !quit;
    }

    private static Optional<Session> onFailedWrapped(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<Session> in){
        if(in.isEmpty())
            System.out.println("Login failed. Wrong username or password?");

        return in;
    }

    private static Optional<Session> login(){
        System.out.println("-- Login --");

        String username = TerminalIO.getUsername();
        String password = TerminalIO.getPassword();

        return bank.signIn(username, password);
    }

    private static Optional<Session> signup(){
        System.out.println("-- Sign up --");

        String username = TerminalIO.getUsername();
        String fullName = TerminalIO.getFullName();
        PasswordX2 passwordX2 = new PasswordX2();

        var passwd = passwordX2.getPass();
        if(passwd.isEmpty()){
            System.out.println("Whoops... either the password was invalid or you have entered two different passwords");
            return Optional.empty();
        }

        boolean success = bank.signUp(username,fullName,passwd.get());

        if(!success){
            System.out.println("Whoops... could not create user");
            return Optional.empty();
        }

        System.out.println("Account created. Please login");
        return login();
    }


    private static class TerminalIO {
        public static String getUsername() {
            System.out.print("Username: ");
            return scanner.nextLine();
        }

        public static String getFullName(){
            System.out.print("Full name: ");
            return scanner.nextLine();
        }

        public static String getPassword() {
            System.out.print("Password: ");
            return scanner.nextLine();
        }

        public static char readOption(){
            return scanner.nextLine().charAt(0);
        }

        public static void printBanner() {
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





}
