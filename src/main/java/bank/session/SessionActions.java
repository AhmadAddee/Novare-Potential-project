package bank.session;

import bank.user.User;
import bank.user.UserDB;

import java.util.Optional;
import java.util.Scanner;

import static bank.session.SessionUtil.*;

interface SessionActions {

    Scanner scanner = new Scanner(System.in);

    static void deposit(User user){
        int amount = readInt("""
                You are about to deposit money into your account
                How much would you like to deposit?""");

        handle(
                user.deposit(amount),
                "Sucess! View your balance to see the change.",
                "Could not deposit to account. Only positive numbers are allowed"
        );

        waitForClick();
    }

    static void withdraw(User user){
        int amount = readInt("""
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

    static void viewProfile(User user){

        String sb = "-- Your profile --\n\n" +
                "Username : " +
                user.getUsername() +
                "\n" +
                "Full name : " +
                user.getFullName() +
                '\n';

        System.out.println(sb);

        waitForClick();
    }

    static void viewBalance(User user){
        System.out.println("Your balance is " + user.getBalance());
        waitForClick();
    }

    static void transfer(User user){
        final UserDB userDB = UserDB.getInstance();
        printUserList();

        System.out.print("Enter either a username or the corresponding [id]: ");

        Optional<User> receiver = scanner.hasNextInt()?
                userDB.get(scanner.nextInt()):
                userDB.get(scanner.next());

        int amount = readInt("""
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

    static void updateUsername(User user){
        System.out.print("New username");
        //TODO: add check for valid username
        String name = scanner.nextLine();

        handle(
                user.updateUsername(name),
                "Success! Username changed.",
                "Could not change username. Either the username already exists or the provided username was invalid."
        );

        waitForClick();
    }

    static void updatePassword(User user){
        System.out.print("New password: ");
        String password = scanner.nextLine();

        handle(
                user.updatePassword(password),
                "Success! Password changed.",
                "Could not change Password."
        );

        waitForClick();
    }


}
