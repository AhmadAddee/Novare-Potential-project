package company.com.model.implementations;

import company.com.model.interfaces.IRecipe;

import java.util.ArrayList;
import java.util.List;

class Recipe implements IRecipe {
    private final String meal;
    private final List<String> ingredientsList;
    private final List<String> stepsList;
    private String tag;
    private final int id;

    Recipe(String meal, int id) {
        this(meal, id, new ArrayList<>(), new ArrayList<>(), null);
    }

    Recipe(String meal, int id, List<String> ingredientsList, List<String> stepsList, String tag) {
        this.meal = meal;
        this.id = id;
        this.ingredientsList = ingredientsList;
        this.stepsList = stepsList;
        setTag(tag);
    }

    @Override
    public void addIngredient(String ingredient){
        ingredientsList.add(ingredient);
    }

    @Override
    public void addStep(String step){
        stepsList.add(step);
    }

    @Override
    public void setTag(String tag){
        this.tag = tag;
    }

    @Override
    public int getId(){
        return this.id;
    }

    @Override
    public String getMeal(){
        return this.meal;
    }

    @Override
    public String getTag(){
        return this.tag;
    }

    @Override
    public List<String> getIngredients(){
        return ingredientsList;
    }
    @Override
    public List<String> getSteps(){
        return stepsList;
    }

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder("\n\nID:" + (this.getId()+1) + "# " + this.getMeal().toUpperCase());
        if (!(getTag() == null))
            sB.append(" (").append(getTag()).append(")");
        sB.append("\n\n== Ingredients: ==");
        for (String s : ingredientsList){
            sB.append("\n").append(s);
        }
        sB.append("\n\n== Method: ==");
        int counter = 0;
        for (String s : stepsList){
            sB.append("\n").append(++counter).append("- ").append(s);
        }
        sB.append("\n_____________________________________________________");
        return sB.toString();
    }
}
