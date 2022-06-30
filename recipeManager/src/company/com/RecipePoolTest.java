package company.com;

import company.com.model.interfaces.IRecipePool;
import company.com.model.implementations.RecipePool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class RecipePoolTest {
    private IRecipePool recipePool;

    @BeforeEach
    public void initTest(){
        recipePool = new RecipePool();
    }

    @Test
    public void testPrintOutThePool(){
        System.out.println(recipePool.toString());
    }

    @Test
    public void editThePool(){
        List<String> ingredients = Arrays.asList("ingredient1", "ingredient1", "ingredient3");
        List<String> steps = Arrays.asList("step one", "step two", "step three", "step four");
        recipePool.addRecipe("Chocolate pancake", ingredients, steps, "Veg");
        System.out.println("the ing of 1 4 is " + recipePool.getIngredients(5).stream().anyMatch(s -> s.contains("1")));
        System.out.println("Adding an ingredient___");
        recipePool.addIngredients(Arrays.asList("new ingredient", "another one"), 1);
    }

}
