package ekz;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class RecipeManager {
    private List<Recipe> recipes;
    private ObjectMapper objectMapper;
    private File jsonFile;

    public RecipeManager(String jsonFilePath) {
        recipes = new ArrayList<>();
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonFile = new File(jsonFilePath);
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        saveRecipesToJson();
    }

    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
        saveRecipesToJson();
    }

    public void updateRecipe(Recipe recipeToUpdate, String newName, List<String> newIngredients,
                             String newInstructions, int newRating) {
        recipeToUpdate.setName(newName);
        recipeToUpdate.setIngredients(newIngredients);
        recipeToUpdate.setInstructions(newInstructions);
        recipeToUpdate.setRating(newRating);
        saveRecipesToJson();
    }

    public Recipe findRecipeByName(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                return recipe;
            }
        }
        return null;
    }

    public void sortRecipesByName() {
        Collections.sort(recipes, Comparator.comparing(Recipe::getName));
        saveRecipesToJson();
    }

    public void sortRecipesByRating() {
        Collections.sort(recipes, Comparator.comparingInt(Recipe::getRating).reversed());
        saveRecipesToJson();
    }

    public void loadRecipesFromJson() {
        try {
            CollectionType listType = TypeFactory.defaultInstance()
                    .constructCollectionType(ArrayList.class, Recipe.class);
            recipes = objectMapper.readValue(jsonFile, listType);
        } catch (JsonParseException | JsonMappingException e) {
            System.out.println("Помилка парсингу JSON-даних.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Помилка вводу-виводу при завантаженні рецептів з JSON.");
            e.printStackTrace();
        }
    }

    public void saveRecipesToJson() {
        try {
            objectMapper.writeValue(jsonFile, recipes);
            System.out.println("Рецепти було збережено у JSON.");
        } catch (IOException e) {
            System.out.println("Помилка вводу-виводу при збереженні рецептів у JSON.");
            e.printStackTrace();
        }
    }

    public void displayRecipes() {
        if (recipes.isEmpty()) {
            System.out.println("Список рецептів порожній.");
        } else {
            System.out.println("Список рецептів:");
            for (Recipe recipe : recipes) {
                System.out.println(recipe);
                System.out.println("-------------------------");
            }
        }
    }
}
