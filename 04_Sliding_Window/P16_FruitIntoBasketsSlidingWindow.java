import java.util.*;

/*
Problem: Fruit Into Baskets
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
A row of fruit trees is given as an array where fruits[i] is the type of fruit on tree i.
    You have two baskets, and each basket can hold only one type of fruit, in unlimited quantity.
    Starting from any tree, you must pick exactly one fruit from every tree while moving right,
    and you stop at the first tree whose fruit fits in neither basket.

    Return the maximum number of fruits you can pick.

Why this pattern fits:
Strip away the story and the task is: find the longest contiguous subarray with at most two distinct values.
    That is a variable-size window with a frequency map.

Approach:
Expand the right pointer and count each fruit type in a map.
    While the map holds more than two types, shrink from the left and remove types whose count reaches zero.
    Track the largest window length.

Worked Example:
Example:
    fruits = [1, 2, 3, 2, 2]
    The window [2, 3, 2, 2] has two types and length 4, which is the best.

Time Complexity: O(n)
Space Complexity: O(1) because the map never holds more than three types
*/

public class P16_FruitIntoBasketsSlidingWindow {
    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> countByType = new HashMap<>();
        int left = 0;
        int best = 0;

        for (int right = 0; right < fruits.length; right++) {
            countByType.merge(fruits[right], 1, Integer::sum);

            while (countByType.size() > 2) {
                int leaving = fruits[left++];
                if (countByType.merge(leaving, -1, Integer::sum) == 0) {
                    countByType.remove(leaving);
                }
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    private static void runDemo(int[] fruits) {
        System.out.println("Fruits: " + Arrays.toString(fruits));
        System.out.println("Picked: " + totalFruit(fruits));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 1});
        runDemo(new int[]{0, 1, 2, 2});
        runDemo(new int[]{1, 2, 3, 2, 2});
    }
}
