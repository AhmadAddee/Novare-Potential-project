package company.com.model.implementations;

import company.com.model.interfaces.IRecipe;
import company.com.model.interfaces.IRecipePool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecipePool implements Iterable<Recipe>, IRecipePool {
    private final List<IRecipe> allRecipes;

    public RecipePool() {
        allRecipes = new ArrayList<>();
        createRecipeSamples();
    }

    @Override
    public void addRecipe(String meal, List<String> ingredients, List<String> steps){
        addRecipe(meal, ingredients, steps, null);
    }

    @Override
    public void addRecipe(String meal, List<String> ingredients, List<String> steps, String tag){
        allRecipes.add(new Recipe(meal, allRecipes.size(), ingredients, steps, tag));
    }

    public boolean updateRecipeCount(int id){
        return false;
    }

    @Override
    public Iterator iterator() {
        Iterator<IRecipe> itrCopy = allRecipes.iterator();
        return itrCopy;
    }

    @Override
    public void addIngredients(List<String> ingredients, int id){
        if (id > allRecipes.size())
            throw new IllegalStateException();
        for (String str : ingredients){
            allRecipes.get(id - 1).addIngredient(str);
        }
    }

    @Override
    public void addStep(List<String> steps, int id) {
        if (id > allRecipes.size())
            throw new IllegalStateException();
        for (String str : steps){
            allRecipes.get(id - 1).addStep(str);
        }
    }

    @Override
    public void setTag(String tag, int id) {
        if (id > allRecipes.size())
            throw new IllegalStateException();
        allRecipes.get(id - 1).setTag(tag);
    }

    @Override
    public String getMeal(int id) {
        if (id > allRecipes.size())
            throw new IllegalStateException();
        return allRecipes.get(id - 1).getMeal();
    }

    @Override
    public IRecipe getRecipe(int id){
        if (id > allRecipes.size())
            throw new IllegalStateException();
        return allRecipes.get(id - 1);
    }

    @Override
    public String getTag(int id){
        if (id > allRecipes.size())
            throw new IllegalStateException();
        return allRecipes.get(id - 1).getTag();
    }

    @Override
    public int getNoOfRecipes(){
        return allRecipes.size();
    }

    @Override
    public List<String> getIngredients(int id) {
        if (id > allRecipes.size())
            throw new IllegalStateException();
        return allRecipes.get(id - 1).getIngredients();
    }

    @Override
    public List<String> getSteps(int id) {
        if (id > allRecipes.size())
            throw new IllegalStateException();
        return allRecipes.get(id - 1).getSteps();
    }

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder("\n\t=== Recipe Pool ===");
        for (IRecipe r : allRecipes){
            sB.append(r.toString());
        }
        return sB.toString();
    }






    private void createRecipeSamples(){
        IRecipe butterChicken = new Recipe("Butter chicken", allRecipes.size());
        butterChicken.addIngredient("500g skinless boneless chicken thighs");
        butterChicken.addIngredient("1 lemon, juiced");
        butterChicken.addIngredient("2 tsp ground cumin");
        butterChicken.addIngredient("2 tsp paprika");
        butterChicken.addIngredient("1-2 tsp hot chilli powder");
        butterChicken.addIngredient("200g natural yogurt");
        butterChicken.addIngredient("2 tbsp vegetable oil");
        butterChicken.addStep("Mix all the marinade ingredients with some seasoning.");
        butterChicken.addStep("Chop the chicken into bite-sized pieces and toss with the marinade.");
        butterChicken.addStep("Cover and chill in the fridge for 1 hr or overnight.");
        butterChicken.addStep("Add the onion, garlic, green chilli, ginger and some seasoning. Fry on a medium heat for 10 mins or until soft.");
        butterChicken.addStep("Add the spices with the tomato purée, cook for a further 2 mins until fragrant, then add the stock and marinated chicken.");
        butterChicken.addStep("Cook for 15 mins, then add any remaining marinade left in the bowl. Simmer for 5 mins, then sprinkle with the toasted almonds. Serve with rice, naan bread, chutney, coriander and lime wedges, if you like.");
        allRecipes.add(butterChicken);
        IRecipe lasagne = new Recipe("Lasagne", allRecipes.size());
        lasagne.addIngredient("1 tbsp olive oil");
        lasagne.addIngredient("2 rashers smoked streaky bacon");
        lasagne.addIngredient("1 onion , finely chopped");
        lasagne.addIngredient("1 celery stick, finely chopped");
        lasagne.addIngredient("1 medium carrot , grated");
        lasagne.addIngredient("400ml crème fraîche");
        lasagne.addIngredient("50g freshly grated parmesan");
        lasagne.addIngredient("large handful basil leaves , torn (optional)");
        lasagne.addStep("Heat the oil in a large saucepan. Use kitchen scissors to snip the bacon into small pieces, or use a sharp knife to chop it on a chopping board.");
        lasagne.addStep("Add the onion, celery and carrot, and cook over a medium heat for 5 mins, stirring occasionally, until softened.");
        lasagne.addStep("Stir in the tomato purée and cook for 1 min, mixing in well with the beef and vegetables. Tip in the chopped tomatoes.");
        lasagne.addStep("Add the honey and season to taste. Simmer for 20 mins.");
        lasagne.addStep("Place 2 sheets of lasagne on top of the sauce overlapping to make it fit, then repeat with more sauce and another layer of pasta. Repeat with a further 2 layers of sauce and pasta, finishing with a layer of pasta.");
        lasagne.addStep("Put the crème fraîche in a bowl and mix with 2 tbsp water to loosen it and make a smooth pourable sauce. Pour this over the top of the pasta, then top with the mozzarella. ");
        lasagne.addStep("Sprinkle Parmesan over the top and bake for 25–30 mins until golden and bubbling. Serve scattered with basil, if you like.");
        allRecipes.add(lasagne);
        IRecipe pancakes = new Recipe("Pancakes", allRecipes.size());
        pancakes.addIngredient("100g plain flour");
        pancakes.addIngredient("2 large eggs");
        pancakes.addIngredient("300ml milk");
        pancakes.addIngredient("1 tbsp sunflower or vegetable oil, plus a little extra for frying");
        pancakes.addIngredient("caster sugar to serve (optional)");
        pancakes.addStep("Put 100g plain flour, 2 large eggs, 300ml milk, 1 tbsp sunflower or vegetable oil and a pinch of salt into a bowl or large jug, then whisk to a smooth batter.");
        pancakes.addStep("Set aside for 30 mins to rest if you have time, or start cooking straight away.");
        pancakes.addStep("Set a medium frying pan or crêpe pan over a medium heat and carefully wipe it with some oiled kitchen paper.");
        pancakes.addStep("When hot, cook your pancakes for 1 min on each side until golden, keeping them warm in a low oven as you go.");
        pancakes.addStep("Serve with lemon wedges and caster sugar, or your favourite filling.");
        pancakes.addStep("Once cold, you can layer the pancakes between baking parchment, then wrap in cling film and freeze for up to 2 months.");
        allRecipes.add(pancakes);
        IRecipe vegLasagne = new Recipe("Lasagne", allRecipes.size());
        vegLasagne.setTag("Vegetarian");
        vegLasagne.addIngredient("3 red peppers, cut into large chunks");
        vegLasagne.addIngredient("300g lasagne sheets");
        vegLasagne.addIngredient("125g mozzarella");
        vegLasagne.addIngredient("1 tbsp olive oil");
        vegLasagne.addStep("Add the onions, garlic and carrot. Cook for 5-7 mins over a medium heat until softened.");
        vegLasagne.addStep("Pour over the chopped tomatoes and add the basil leaves.");
        vegLasagne.addStep("To make the white sauce, melt the butter in a saucepan, stir in the plain flour, then cook for 2 mins.");
        vegLasagne.addStep("Toss with the olive oil, season well, then roast for 25 mins until lightly browned.");
        vegLasagne.addStep("Bake for 45 mins until bubbling and golden.");
        allRecipes.add(vegLasagne);
        IRecipe meatballs = new Recipe("Meatballs", allRecipes.size());
        meatballs.addIngredient("300g good-quality pork sausage");
        meatballs.addIngredient("1 small onion");
        meatballs.addIngredient("1 carrot");
        meatballs.addIngredient("1 tbsp dried oregano");
        meatballs.addIngredient("1 tbsp olive oil");
        meatballs.addStep("Get your child to squeeze all the sausagemeat out of the skins into a large bowl.");
        meatballs.addStep("Get your child to coarsely grate the onion and finely grate the carrot. If the onion starts to hurt their eyes, get them to wear goggles, which is good fun.");
        meatballs.addStep("ip these vegetables in with the sausages. While you have the grater out, grate the Parmesan, other vegetables and garlic for the sauce, and set aside.");
        meatballs.addStep("Next, get your child to add all the other meatball ingredients one by one, except the olive oil, into the bowl and season with black pepper.");
        meatballs.addStep("Get the child to squish everything together through their hands until completely mixed.");
        meatballs.addStep("It’s ready to eat now or cool and freeze in suitable batches for up to 6 months. Serve with spaghetti, some basil and extra Parmesan, if you like.");
        allRecipes.add(meatballs);
        IRecipe sample6 = new Recipe("Sample recipe six", allRecipes.size());
        sample6.addIngredient("blablabla ingredients");
        sample6.addIngredient("1 small blablabla ingredients");
        sample6.addIngredient("1 blablabla ingredients");
        sample6.addIngredient("1 tbsp blablabla ingredients oregano");
        sample6.addIngredient("1 tbsp blablabla ingredients oil");
        sample6.addStep("Get your child to squeeze all the sausagemeat oblablabla ingredientsbowl.");
        sample6.addStep("Get your child to coarsely grate the onion and finely grblablabla ingredientseir eyes, get them to wear goggles, which is good fun.");
        sample6.addStep("ip these vegetables in with the sausages. Whileblablabla ingreblablabla ingredientsl the other meatball ingredients one by one, except the olive oil, into the bowl and season with black pepper.");
        sample6.addStep("Get the child to squish everything blablabla ingredientsgh their hands until completely mixed.");
        sample6.addStep("It’s ready to eat now or cool blablabla ingredientsith spaghetti, some basil and extra Parmesan, if you like.");
        allRecipes.add(sample6);
        IRecipe sample7 = new Recipe("Sample recipe seven", allRecipes.size());
        sample7.addIngredient("1 carrot");
        sample7.addIngredient("1 tbsp dried oregano");
        sample7.addIngredient("1 tbsp olive oil");
        sample7.addStep("Get your child to squeeze all the sausagemeat out of the skins into a large bowl.");
        sample7.addStep("ip these vegetables in with the sausages. While you have the grater out, grate the Parmesan, other vegetables and garlic for the sauce, and set aside.");
        sample7.addStep("Next, get your child to add all the other meatball ingredients one by one, except the olive oil, into the bowl and season with black pepper.");
        sample7.addStep("Get the child to squish everything together through their hands until completely mixed.");
        allRecipes.add(sample7);
        IRecipe sample8 = new Recipe("Sample recipe eight", allRecipes.size());
        sample8.addIngredient("300g good-quality pork sausage");
        sample8.addIngredient("1 small onion");
        sample8.addStep("Get the child to squish everything together through their hands until completely mixed.");
        sample8.addStep("It’s ready to eat now or cool and freeze in suitable batches for up to 6 months. Serve with spaghetti, some basil and extra Parmesan, if you like.");
        allRecipes.add(sample8);

    }
}
