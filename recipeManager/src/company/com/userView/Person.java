package company.com.userView;

import company.com.model.interfaces.IRecipePool;
import company.com.model.implementations.RecipePool;

import java.util.Scanner;

public class Person {

    IRecipePool recipePool = new RecipePool();

    public void printOptions(){}

    public void printBanner() {
        System.out.print("=== Recipe Manager ===");
    }

    public void sessionLoop() {
        char option;
        while (true) {
            printMenu();
            option = (char) Character.codePointAt((String) getInput(), 0);
            switch (option) {
                case '1' -> {
                    new User(recipePool).printOptions();}
                case '2' -> {new Dietician(recipePool).printOptions();}
                case 'q' -> {
                    System.out.println("Goodbye!");
                    return;
                    }
                default -> invalidOption();
            }
        }
    }

    public void printMenu(){
        System.out.print("""
                                

                Please choose a user type:\s
                [1] User
                [2] Dietician
                \s
                Selected user: _""");
    }

    public Object getInput() {
        Scanner scan = new Scanner(System.in);
        var option = scan.nextLine();
        while (option.equals(""))
            option = scan.nextLine();
        return option;
    }

    public void invalidOption(){
        System.out.println("Invalid input!");
    }

}
