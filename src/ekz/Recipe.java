package ekz;
import java.util.List;
public class Recipe {
    private String name;
    private List<String> ingredients;
    private String instructions;
    private int rating;

    public Recipe(String name, List<String> ingredients, String instructions, int rating) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Назва: " + name +
                "\nІнгредієнти: " + ingredients +
                "\nІнструкції: " + instructions +
                "\nОцінка: " + rating;
    }
}
