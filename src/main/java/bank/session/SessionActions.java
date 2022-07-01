package bank.session;

import bank.Bank;

import java.util.Scanner;

import static bank.session.SessionUtil.*;

/**
 * An interface containing the operations which can be performed by a user in a session.
 */
interface SessionActions {


    static void deposit(Bank bank, Session session){
        var amount = readInt("""
                You are about to deposit money into your account
                How much would you like to deposit?""");

        handle(
                amount.isPresent()
                && bank
                        .performAction(session)
                        .deposit(amount.get()),
                "Success! View your balance to see the change.",
                "Could not deposit to account. Only positive numbers are allowed"
        );

        waitForClick();
    }

    static void withdraw(Bank bank, Session session){
        var amount = readInt("""
                You are about to withdraw money from your account.
                How much would you like to withdraw?
                """);

        handle(
                amount.isPresent()
                && bank
                        .performAction(session)
                        .withdraw(amount.get()),
                "Success! You have now withdrawn money from you account. View your balance to see the changes",
                "Could not process the transaction. Do you have enough funds on your account?"
        );

        waitForClick();
    }

    static void viewProfile(Bank bank, Session session){

        var username = bank.performAction(session).getUsername();
        var fullName = bank.performAction(session).getFullName();
        boolean success = username.isPresent() && fullName.isPresent();

        if(!success){
            System.out.println("Could not read user profile");
            return;
        }

        String s = "-- Your profile --\n\n" +
                "Username : " +
                username.get() +
                "\n" +
                "Full name : " +
                fullName.get() +
                '\n';

        System.out.println(s);

        waitForClick();
    }

    static void viewBalance(Bank bank, Session session){
        var balance = bank
                .performAction(session)
                .getBalance();

        balance.ifPresentOrElse(
                (x) -> System.out.println("Your balance is " + x),
                ()  -> System.out.println("Could not read balance.")
        );
        waitForClick();
    }

    static void transfer(Bank bank, Session session){
        Scanner scanner = new Scanner(System.in);
        printUserList();

        System.out.print("Enter either a username or the corresponding [id]: ");

        String receiverIdOrUsername = scanner.nextLine();


        var amount = readInt(
                    """
                    You are about to transfer money to another user.
                    Please enter an amount to transfer:
                    """
        );

        handle(
                amount.isPresent()
                && bank
                        .performAction(session)
                        .transferSelfMatch(amount.get(), receiverIdOrUsername),
                "Success! Money have now been transferred.",
                "Could not transfer the money."
        );

        waitForClick();
    }

    static void updateUsername(Bank bank, Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("New username: ");
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
        Scanner scanner = new Scanner(System.in);
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