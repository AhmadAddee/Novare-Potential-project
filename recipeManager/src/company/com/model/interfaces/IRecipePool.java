package company.com.model.interfaces;

import company.com.model.interfaces.IRecipe;

import java.util.List;

public interface IRecipePool {

    void addRecipe(String meal, List<String> ingredients, List<String> steps);
    void addRecipe(String meal, List<String> ingredients, List<String> steps, String tag);
    void addIngredients(List<String> ingredients, int id);
    void addStep(List<String> steps, int id);
    void setTag(String tag, int id);
    String getMeal(int id);
    IRecipe getRecipe(int id);
    String getTag(int id);
    int getNoOfRecipes();
    List<String> getIngredients(int id);
    List<String> getSteps(int id);
    String toString();
}
