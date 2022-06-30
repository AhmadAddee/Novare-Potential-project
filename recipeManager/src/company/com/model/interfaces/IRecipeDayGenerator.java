package company.com.model.interfaces;

import java.time.DayOfWeek;
import java.util.Map;

public interface IRecipeDayGenerator {
    Map<DayOfWeek, IRecipe> getRecipeOfDay();
    DayOfWeek getDay();
    IRecipe getRecipe();
}
