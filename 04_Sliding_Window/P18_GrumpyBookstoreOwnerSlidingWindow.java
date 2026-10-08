import java.util.*;

/*
Problem: Grumpy Bookstore Owner
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
A bookstore is open for n minutes. customers[i] customers enter during minute i.
    grumpy[i] is 1 if the owner is grumpy during minute i and 0 otherwise.
    Customers who arrive during a grumpy minute leave unsatisfied.

    The owner knows a technique to stay calm for `minutes` consecutive minutes, but can use it only once.
    Return the maximum number of customers that can be satisfied.

Why this pattern fits:
The technique covers a fixed-length stretch of minutes, so every placement is a fixed-size window.
    The only thing that changes between placements is how many otherwise-lost customers the window rescues.

Approach:
First add up the customers who are satisfied anyway (minutes where the owner is not grumpy).
    Then slide a window of the technique's length and track the number of grumpy-minute customers inside it.
    The answer is the always-satisfied total plus the best rescued amount.

Worked Example:
Example:
    customers = [1, 0, 1, 2, 1, 1, 7, 5], grumpy = [0, 1, 0, 1, 0, 1, 0, 1], minutes = 3
    Always satisfied: 1 + 1 + 1 + 7 = 10.
    The best window is the last three minutes, rescuing 1 + 5 = 6. Answer = 16.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P18_GrumpyBookstoreOwnerSlidingWindow {
    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int alwaysSatisfied = 0;
        int rescued = 0;
        int bestRescued = 0;

        for (int right = 0; right < customers.length; right++) {
            if (grumpy[right] == 0) {
                alwaysSatisfied += customers[right];
            } else {
                rescued += customers[right];
            }

            if (right >= minutes && grumpy[right - minutes] == 1) {
                rescued -= customers[right - minutes];
            }
            bestRescued = Math.max(bestRescued, rescued);
        }
        return alwaysSatisfied + bestRescued;
    }

    public static void main(String[] args) {
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        System.out.println("Customers: " + Arrays.toString(customers));
        System.out.println("Grumpy   : " + Arrays.toString(grumpy));
        System.out.println("Satisfied: " + maxSatisfied(customers, grumpy, 3));
        System.out.println();
        System.out.println("Satisfied: " + maxSatisfied(new int[]{1}, new int[]{0}, 1));
    }
}
