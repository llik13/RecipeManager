package ekz;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RecipeManager manager = new RecipeManager("recept.json");
        Scanner scanner = new Scanner(System.in);
        manager.loadRecipesFromJson();

        boolean running = true;
        while (running) {
            System.out.println("Менеджер рецептів");
            System.out.println("1. Додати рецепт");
            System.out.println("2. Видалити рецепт");
            System.out.println("3. Оновити рецепт");
            System.out.println("4. Знайти рецепт за назвою");
            System.out.println("5. Сортувати рецепти за назвою");
            System.out.println("6. Сортувати рецепти за оцінкою");
            System.out.println("7. Відобразити рецепти");
            System.out.println("8. Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введіть назву рецепту:");
                    String name = scanner.nextLine();
                    System.out.println("Введіть інгредієнти рецепту (розділіть їх комами):");
                    String ingredientsStr = scanner.nextLine();
                    List<String> ingredients = new ArrayList<>();
                    String[] ingredientsArr = ingredientsStr.split(",");
                    for (String ingredient : ingredientsArr) {
                        ingredients.add(ingredient.trim());
                    }
                    System.out.println("Введіть інструкції до рецепту:");
                    String instructions = scanner.nextLine();
                    System.out.println("Введіть оцінку рецепту (від 0 до 5):");
                    int rating = scanner.nextInt();

                    Recipe recipe = new Recipe(name, ingredients, instructions, rating);
                    manager.addRecipe(recipe);
                    break;
                case 2:
                    System.out.println("Введіть назву рецепту для видалення:");
                    String recipeName = scanner.nextLine();
                    Recipe recipeToRemove = manager.findRecipeByName(recipeName);
                    if (recipeToRemove != null) {
                        manager.removeRecipe(recipeToRemove);
                        System.out.println("Рецепт " + recipeName + " було видалено.");
                    } else {
                        System.out.println("Рецепт з назвою '" + recipeName + "' не знайдено.");
                    }
                    break;
                case 3:
                    System.out.println("Введіть назву рецепту для оновлення:");
                    String recipeToUpdateName = scanner.nextLine();
                    Recipe recipeToUpdate = manager.findRecipeByName(recipeToUpdateName);
                    if (recipeToUpdate != null) {
                        System.out.println("Введіть нову назву рецепту:");
                        String newName = scanner.nextLine();
                        System.out.println("Введіть нові інгредієнти рецепту (розділіть їх комами):");
                        String newIngredientsStr = scanner.nextLine();
                        List<String> newIngredients = new ArrayList<>();
                        String[] newIngredientsArr = newIngredientsStr.split(",");
                        for (String ingredient : newIngredientsArr) {
                            newIngredients.add(ingredient.trim());
                        }
                        System.out.println("Введіть нові інструкції до рецепту:");
                        String newInstructions = scanner.nextLine();
                        System.out.println("Введіть нову оцінку рецепту (від 0 до 5):");
                        int newRating = scanner.nextInt();

                        manager.updateRecipe(recipeToUpdate, newName, newIngredients, newInstructions, newRating);
                        System.out.println("Рецепт " + recipeToUpdateName + " було оновлено.");
                    } else {
                        System.out.println("Рецепт з назвою '" + recipeToUpdateName + "' не знайдено.");
                    }
                    break;
                case 4:
                    System.out.println("Введіть назву рецепту для пошуку:");
                    String searchName = scanner.nextLine();
                    Recipe searchResult = manager.findRecipeByName(searchName);
                    if (searchResult != null) {
                        System.out.println("Знайдено рецепт: ");
                        System.out.println(searchResult);
                    } else {
                        System.out.println("Рецепт з назвою '" + searchName + "' не знайдено.");
                    }
                    break;
                case 5:
                    manager.sortRecipesByName();
                    System.out.println("Рецепти було відсортовано за назвою.");
                    break;
                case 6:
                    manager.sortRecipesByRating();
                    System.out.println("Рецепти було відсортовано за оцінкою.");
                    break;
                case 7:
                    manager.displayRecipes();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Некоректний вибір. Спробуйте ще раз.");
                    break;
            }
        }

        scanner.close();
    }
}
