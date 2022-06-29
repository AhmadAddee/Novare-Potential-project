package bank.session;

import bank.Bank;

import java.util.Optional;
import java.util.Scanner;

import static bank.session.SessionUtil.*;

interface SessionActions {

    Scanner scanner = new Scanner(System.in);

    static void deposit(Bank bank, Session session){
        int amount = readInt("""
                You are about to deposit money into your account
                How much would you like to deposit?""");

        handle(
                bank
                        .performAction(session)
                        .deposit(amount),
                "Sucess! View your balance to see the change.",
                "Could not deposit to account. Only positive numbers are allowed"
        );

        waitForClick();
    }

    static void withdraw(Bank bank, Session session){
        int amount = readInt("""
                You are about to withdraw money from your account.
                How much would you like to withdraw?
                """);

        handle(
                bank
                        .performAction(session)
                        .withdraw(amount),
                "Sucess! You have now withdrawn money from you account. View your balance to see the changes",
                "Could not process the transaction. Do you have enough funds on your account?"
        );

        waitForClick();
    }

    static void viewProfile(Bank bank, Session session){

        var username = bank.performAction(session).getUsername();
        var fullName = bank.performAction(session).getFullName();

        assert username.isPresent() && fullName.isPresent();

        String s = "-- Your profile --\n\n" +
                "Username : " +
                username +
                "\n" +
                "Full name : " +
                fullName +
                '\n';

        System.out.println(s);

        waitForClick();
    }

    static void viewBalance(Bank bank, Session session){
        Optional<Integer> balance = bank.performAction(session).getBalance();
        assert balance.isPresent();
        System.out.println("Your balance is " + balance.get());
        waitForClick();
    }

    static void transfer(Bank bank, Session session){
        printUserList();

        System.out.print("Enter either a username or the corresponding [id]: ");

        String receiverIdOrUsername = scanner.nextLine();


        int amount = readInt("""
                    You are about to transfer money to another user.
                    Please enter an amount to transfer:
                    """);

        handle(
                bank
                        .performAction(session)
                        .transferSelfMatch(amount, receiverIdOrUsername),
                "Success! Money have now been transferred.",
                "Could not transfer the money."
        );

        waitForClick();
    }

    static void updateUsername(Bank bank, Session session){
        System.out.print("New username");
        String name = scanner.nextLine();

        handle(
                bank
                        .performAction(session)
                        .updateUsername(name),
                "Success! Username changed.",
                "Could not change username. Either the username already exists or the provided username was invalid."
        );

        waitForClick();
    }

    static void updatePassword(Bank bank, Session session){
        System.out.print("New password: ");
        String password = scanner.nextLine();

        handle(
                bank
                        .performAction(session)
                        .updatePassword(password),
                "Success! Password changed.",
                "Could not change Password."
        );

        waitForClick();
    }


}
