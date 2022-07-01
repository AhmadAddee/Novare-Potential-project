package company.com.model.implementations;

import company.com.model.interfaces.IRecipe;
import company.com.model.interfaces.IRecipePool;
import company.com.model.interfaces.IRecipeWeekGenerator;

import java.time.DayOfWeek;
import java.util.*;

public class RecipeWeekGenerator implements IRecipeWeekGenerator {
    private final IRecipePool recipePool;
    private final List<Map<DayOfWeek, IRecipe>> recipeWeek;
    private final List<Integer> generatedRecipes;
    private final int id;

    public RecipeWeekGenerator(IRecipePool recipePool, int id) {
        this.recipePool = recipePool;
        this.recipeWeek = new ArrayList<>();
        this.generatedRecipes = new ArrayList<>();
        this.id = id;
        getRecipeWeek();
    }

    private List<Map<DayOfWeek, IRecipe>> getRecipeWeek(){
        int n = recipePool.getNoOfRecipes();

        if (n > 7) //Check if there is more than 7 recipes in the pool,
            n = 7;

        for (int i = 1; i <= n; i++){
            int id = generateRecipeId(recipePool.getNoOfRecipes());
            RecipeDayGenerator newDay = new RecipeDayGenerator(DayOfWeek.of(i), recipePool.getRecipe(id));//
            recipeWeek.add(newDay.getRecipeOfDay());
        }
        return recipeWeek;
    }

    public int getId(){
        return this.id;
    }

    private int generateRecipeId(int noOfRecipes){
        int id;
        Random rnd = new Random();
        do {
            id = rnd.nextInt(noOfRecipes) + 1;
        }while(alreadyExist(id));

        generatedRecipes.add(id);
        return id;
    }

    private boolean alreadyExist(int id){
        return generatedRecipes.contains(id);
    }

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder("\nWeek " + (this.getId()+1) + ": \t");
        for (Map<DayOfWeek, IRecipe> r : recipeWeek){
            r.forEach((key, value) -> sB.append("\n[" + key.getValue() + "] " + key + "\t\t\t" + value.getMeal() + " #" + (value.getId()+1)));
        }
        return sB.toString();
    }
}
