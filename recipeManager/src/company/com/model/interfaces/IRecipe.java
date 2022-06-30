package company.com.model.interfaces;

import java.util.List;

public interface IRecipe {
    void addIngredient(String ingredient);
    void addStep(String step);
    void setTag(String tag);
    int getId();
    String getMeal();
    String getTag();
    List<String> getIngredients();
    List<String> getSteps();
    String toString();
}
