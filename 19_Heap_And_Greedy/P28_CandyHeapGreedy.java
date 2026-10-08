import java.util.*;

/*
Problem: Candy
Pattern: 19. Heap & Greedy
Difficulty: Hard

Interview Prompt:
There are n children standing in a line, each with a rating value.
    You give candies to the children subject to two rules:
    - every child must receive at least one candy
    - a child with a higher rating than an adjacent child must receive more candies than that neighbor

    Return the minimum total number of candies needed.

Why this pattern fits:
Each child is constrained by two neighbors, which is hard to satisfy in one sweep.
    Splitting the rule into a left-neighbor rule and a right-neighbor rule makes each one solvable
    by a simple greedy pass, and taking the larger requirement at each position satisfies both.

Approach:
Give every child one candy.
    Left-to-right pass: if a child's rating is higher than the left neighbor's, give one more candy than that neighbor.
    Right-to-left pass: if a child's rating is higher than the right neighbor's,
    make sure the child has at least one more candy than that neighbor (keeping the larger value).
    Sum the candies.

Worked Example:
Example:
    ratings = [1, 0, 2]
    After the left pass: [1, 1, 2]. After the right pass: [2, 1, 2].
    Total = 5.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P28_CandyHeapGreedy {
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int total = 0;
        for (int count : candies) {
            total += count;
        }
        return total;
    }

    private static void runDemo(int[] ratings) {
        System.out.println(Arrays.toString(ratings) + " -> " + candy(ratings) + " candies");
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 0, 2});
        runDemo(new int[]{1, 2, 2});
        runDemo(new int[]{1, 3, 4, 5, 2});
    }
}
