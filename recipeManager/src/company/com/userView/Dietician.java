package company.com.userView;

import company.com.model.interfaces.IRecipePool;

import java.util.ArrayList;
import java.util.List;

public class Dietician extends Person implements IDietician {

    private final IRecipePool recipePool;

    public Dietician(IRecipePool recipePool) {
        this.recipePool = recipePool;
    }

    @Override
    public void printOptions() {
        char option;
        while (true) {
            printMenu();
            option = (char) Character.codePointAt((String) getInput(), 0);
            switch (option) {
                case '1' -> viewRecipePool();
                case '2' -> editARecipe();
                case '3' -> createRecipe();
                case 'q' -> {return;}
                default -> invalidOption();
            }
        }
    }

    @Override
    public void printMenu() {
        System.out.print("""
                                
                User type selected: Dietician\s
                
                Please choose one of the following operations:
                [1] List recipes
                [2] Edit a recipe
                [3] Add a recipe
                \s
                Option: _""");
    }

    @Override
    public Object getInput() {
        return super.getInput();
    }

    @Override
    public void invalidOption() {
        super.invalidOption();
    }

    @Override
    public void createRecipe() {
        System.out.print("The name of the meal: ");
        String meal = (String) getInput();
        if (meal.equals("q"))
            return;

        List<String> ingredients = getIngredients();
        List<String> steps = getSteps();

        recipePool.addRecipe(meal, ingredients, steps);
        System.out.println("Done");
    }

    private List<String> getIngredients(){
        List<String> ingredients = new ArrayList<>();
        System.out.println("Enter the ingredients:");
        String userInput = (String) getInput();
        while (!(userInput.equals("q"))){
            ingredients.add(userInput);
            userInput = (String) getInput();
        }
        return ingredients;
    }
    private List<String> getSteps(){
        List<String> steps = new ArrayList<>();
        System.out.println("Enter the steps:");
        String userInput = (String) getInput();
        while (!(userInput.equals("q"))){
            steps.add(userInput);
            userInput = (String) getInput();
        }
        return steps;
    }

    @Override
    public void viewRecipe() {
        System.out.print("Which ID does the recipe have? ");
        int selectedId = Integer.parseInt((String)getInput());
        System.out.println(recipePool.getRecipe(selectedId));
    }

    @Override
    public void viewRecipePool() {
        System.out.println(recipePool.toString());
    }

    @Override
    public void editARecipe(){
        System.out.print("__Please choose a recipe to edit by its ID:__");

        try {
            int selectedId = Integer.parseInt(String.valueOf(getInput()));
            System.out.println(recipePool.getRecipe(selectedId) + """
                                    
                    Select one of the options to edit:\s
                    [1] Add ingredient
                    [2] Add step
                    [3] Set a tag
                    """);
            //char userInput = (char) Character.codePointAt((String) getInput(), 0);
            int userInput = Integer.parseInt(String.valueOf(getInput()));
            switch (userInput) {
                case 1 -> addIngredients(selectedId);
                case 2 -> addStep(selectedId);
                case 3 -> setATag(selectedId);
                default -> invalidOption();
            }
        }catch(Exception e){
            invalidOption();
        }

    }

    private void addIngredients(int recipeId){
        List<String> ingredients = getIngredients();
        recipePool.addIngredients(ingredients, recipeId);
    }

    private void addStep(int recipeId){
        List<String> steps = getSteps();
        recipePool.addStep(steps, recipeId);
    }

    private void setATag(int recipeId){
        System.out.print("Enter the desired tag: ");
        String tag = (String) getInput();
        recipePool.setTag(tag, recipeId);
    }
}
