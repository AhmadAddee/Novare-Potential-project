package company.com.model.implementations;

import company.com.model.interfaces.IRecipe;
import company.com.model.interfaces.IRecipeDayGenerator;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class RecipeDayGenerator implements IRecipeDayGenerator {
    private final DayOfWeek day;
    private final IRecipe recipe;
    private final Map<DayOfWeek, IRecipe> recipeDay;

    public RecipeDayGenerator(DayOfWeek day, IRecipe recipe) {
        this.day = day;
        this.recipe = recipe;
        recipeDay = new HashMap<>();
    }

    @Override
    public Map<DayOfWeek, IRecipe> getRecipeOfDay(){
        recipeDay.put(day, recipe);
        return recipeDay;
    }

    @Override
    public DayOfWeek getDay(){
        return this.day;
    }

    @Override
    public IRecipe getRecipe(){
        return this.recipe;
    }

    @Override
    public String toString() {
        return "[" + day.getValue() + "] "
                + day.toString() + "\t\t"
                + recipe.getMeal()
                + " #" + recipe.getId();
    }
}
