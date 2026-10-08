import java.util.*;

/*
Problem: Broken Calculator
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
A calculator shows startValue and supports two operations: multiply by 2, or subtract 1.
Return the minimum number of operations needed to display target.

Why this pattern fits:
Going forward, it is unclear when to double. Going backward from the target the choice is forced:
an odd number cannot have come from a doubling, and for an even number halving first is never worse.

Approach:
While target > startValue: if target is even halve it, otherwise add 1; count each step.
Finally add startValue - target for the remaining subtractions.

Worked Example:
start = 3, target = 10 -> backwards: 10 -> 5 -> 6 -> 3. Three operations (forward: double, subtract, double).

Time Complexity: O(log target)
Space Complexity: O(1)
*/

public class P24_BrokenCalculatorHeapGreedy {
    public static int brokenCalc(int startValue, int target) {
        int operations = 0;
        while (target > startValue) {
            target = target % 2 == 0 ? target / 2 : target + 1;
            operations++;
        }
        return operations + startValue - target;
    }

    public static void main(String[] args) {
        int[][] cases = {{2, 3}, {5, 8}, {3, 10}, {1024, 1}};
        for (int[] c : cases) {
            System.out.println(c[0] + " to " + c[1] + " -> " + brokenCalc(c[0], c[1]));
        }
    }
}
