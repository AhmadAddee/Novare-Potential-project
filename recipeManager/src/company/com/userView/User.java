package company.com.userView;

import company.com.model.interfaces.IGeneratedRecipeWeeks;
import company.com.model.interfaces.IRecipePool;
import company.com.model.implementations.GeneratedRecipeWeeks;
import company.com.model.implementations.RecipeWeekGenerator;
import company.com.model.interfaces.IRecipeWeekGenerator;

public class User extends Person implements IUser {

    private final IRecipePool recipePool;
    private final IGeneratedRecipeWeeks userWeekList;

    public User(IRecipePool recipePool) {
        this.recipePool = recipePool;
        userWeekList = new GeneratedRecipeWeeks();
    }

    @Override
    public void printOptions() {
        char option;
        while (true) {
            printMenu();
            option = (char) Character.codePointAt((String) getInput(), 0);
            switch (option) {
                case '1' -> viewRecipeWeeks();
                case '2' -> viewRecipePool();
                case '3' -> viewRecipe();
                case '4' -> generateRecipeWeek();
                case 'q' -> {return;}
                default -> invalidOption();
            }
        }

    }

    @Override
    public void printMenu() {
        System.out.print("""
                                
                User type selected: User\s
                
                Please choose one of the following operations:
                [1] List my weeks
                [2] List recipes
                [3] View recipe
                [4] Generate a new week
                \s
                Option: _""");
    }

    @Override
    public void viewRecipeWeeks() {
        System.out.println(userWeekList);
    }

    @Override
    public void viewRecipePool() {
        System.out.println(recipePool.toString());
    }

    @Override
    public void viewRecipe() {
        System.out.print("Enter the recipe's ID__");
        try {
            int recipeId = Integer.parseInt(String.valueOf(getInput()));
            System.out.println(recipePool.getRecipe(recipeId));
        }catch(Exception e){
            invalidOption();
        }
    }

    @Override
    public void generateRecipeWeek() {
        IRecipeWeekGenerator newWeek = new RecipeWeekGenerator(recipePool, userWeekList.getNoOfRecipes());
        userWeekList.addAUserWeek(newWeek);
        System.out.println(newWeek.toString());
    }
}
