import java.util.*;

/*
Problem: Boats to Save People
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
You are given the weights of people and a boat weight limit.
    Each boat carries at most two people, and their combined weight must not exceed the limit.
    Every person weighs at most the limit. Return the minimum number of boats needed to carry everyone.

Why this pattern fits:
After sorting, the heaviest person is the hardest to pair. The best possible partner is the lightest person.
    If even the lightest person does not fit, nobody will, so the heaviest goes alone. That is a two-ended greedy.

Approach:
Sort the weights. Point at the lightest and the heaviest remaining person.
    If they fit together, send both and move both pointers. Otherwise send the heaviest alone.
    Count one boat per step.

Worked Example:
Example:
    people = [3, 2, 2, 1], limit = 3
    Sorted: [1, 2, 2, 3]
    3 goes alone, 1 pairs with 2, and the last 2 goes alone. Answer = 3 boats.

Time Complexity: O(n log n)
Space Complexity: O(1) extra, ignoring the sort
*/

public class BoatsToSavePeopleTwoPointers {
    public static int numRescueBoats(int[] people, int limit) {
        int[] sorted = people.clone();
        Arrays.sort(sorted);

        int light = 0;
        int heavy = sorted.length - 1;
        int boats = 0;

        while (light <= heavy) {
            if (sorted[light] + sorted[heavy] <= limit) {
                light++;
            }
            heavy--;
            boats++;
        }
        return boats;
    }

    private static void runDemo(int[] people, int limit) {
        System.out.println("People: " + Arrays.toString(people) + ", limit = " + limit);
        System.out.println("Boats : " + numRescueBoats(people, limit));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2}, 3);
        runDemo(new int[]{3, 2, 2, 1}, 3);
        runDemo(new int[]{3, 5, 3, 4}, 5);
    }
}
