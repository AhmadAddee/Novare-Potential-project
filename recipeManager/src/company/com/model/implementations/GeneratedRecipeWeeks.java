package company.com.model.implementations;

import company.com.model.interfaces.IGeneratedRecipeWeeks;
import company.com.model.interfaces.IRecipeWeekGenerator;

import java.util.*;

public class GeneratedRecipeWeeks implements IGeneratedRecipeWeeks {

    private final List<IRecipeWeekGenerator> recipeWeeks;

    public GeneratedRecipeWeeks() {
        this.recipeWeeks = new ArrayList<>();
    }

    @Override
    public void addAUserWeek(IRecipeWeekGenerator recipeWeek){
        recipeWeeks.add(recipeWeek);
    }

    @Override
    public int getNoOfRecipes(){
        return recipeWeeks.size();
    }
    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder("\n=== My weeks === \n");
        for (IRecipeWeekGenerator r : recipeWeeks){
            sB.append(r);
        }
        return sB.toString();
    }
}
