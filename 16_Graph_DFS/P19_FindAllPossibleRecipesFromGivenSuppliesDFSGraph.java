import java.util.*;

/*
Problem: Find All Possible Recipes from Given Supplies
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
recipes[i] can be made if all of ingredients[i] are available. An ingredient may be an initial supply
or another recipe. Return every recipe that can be made.

Why this pattern fits:
Recipes depending on recipes form a dependency graph. A recipe is makeable when all of its dependencies are,
which is a DFS; a "currently visiting" state prevents infinite loops on cyclic recipes.

Approach:
canMake(item): supplies are true; unknown non-recipes are false. For a recipe, mark it "visiting"
(treated as false if reached again), check all ingredients recursively, and cache the final result.

Worked Example:
bread needs yeast and flour (supplies); sandwich needs bread and meat; burger needs sandwich, meat, and bread.
All three can be made.

Time Complexity: O(total ingredients)
Space Complexity: O(recipes + ingredients)
*/

public class P19_FindAllPossibleRecipesFromGivenSuppliesDFSGraph {
    private static boolean canMake(String item, Map<String, List<String>> needs, Set<String> supplies,
                                   Map<String, Boolean> state) {
        if (supplies.contains(item)) {
            return true;
        }
        if (!needs.containsKey(item)) {
            return false;
        }
        if (state.containsKey(item)) {
            return state.get(item);
        }
        state.put(item, false);
        for (String ingredient : needs.get(item)) {
            if (!canMake(ingredient, needs, supplies, state)) {
                return false;
            }
        }
        state.put(item, true);
        return true;
    }

    public static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> needs = new HashMap<>();
        for (int i = 0; i < recipes.length; i++) {
            needs.put(recipes[i], ingredients.get(i));
        }
        Set<String> available = new HashSet<>(Arrays.asList(supplies));
        Map<String, Boolean> state = new HashMap<>();
        List<String> result = new ArrayList<>();
        for (String recipe : recipes) {
            if (canMake(recipe, needs, available, state)) {
                result.add(recipe);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("one recipe -> " + findAllRecipes(new String[]{"bread"},
                Arrays.asList(Arrays.asList("yeast", "flour")), new String[]{"yeast", "flour", "corn"}));
        System.out.println("chained recipes -> " + findAllRecipes(new String[]{"bread", "sandwich", "burger"},
                Arrays.asList(Arrays.asList("yeast", "flour"), Arrays.asList("bread", "meat"),
                        Arrays.asList("sandwich", "meat", "bread")),
                new String[]{"yeast", "flour", "meat"}));
    }
}
