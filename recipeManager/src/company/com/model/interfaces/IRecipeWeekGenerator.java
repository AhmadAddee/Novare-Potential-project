package company.com.model.interfaces;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public interface IRecipeWeekGenerator {
    private List<Map<DayOfWeek, IRecipe>> getRecipeWeek() {
        return null;
    }

    private int getId() {
        return 0;
    }

    private int generateRecipeId(int noOfRecipes) {
        return 0;
    }

    private boolean alreadyExist(int id) {
        return false;
    }

    String toString();
}
