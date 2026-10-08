import java.util.*;

/*
Problem: Number of Visible People in a Queue
Pattern: 10. Stack
Difficulty: Hard

Interview Prompt:
People with distinct heights stand in a queue facing right. Person i can see person j (i < j)
if everyone between them is shorter than both. Return how many people each person can see.

Why this pattern fits:
A person hides everyone behind them who is shorter. Scanning from the right, the stack holds the people
still visible from the left; the current person sees the shorter ones (and removes them) and the first taller one.

Approach:
For i from n - 1 down: pop every shorter height, counting each. If the stack is not empty afterwards,
count one more for the taller person that blocks the view. Push the current height.

Worked Example:
heights = [10, 6, 8, 5, 11, 9] -> person 0 sees 6, 8, and 11. Answer = [3, 1, 2, 1, 1, 0].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P43_NumberOfVisiblePeopleInAQueueStack {
    public static int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                stack.pop();
                result[i]++;
            }
            if (!stack.isEmpty()) {
                result[i]++;
            }
            stack.push(heights[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[10, 6, 8, 5, 11, 9] -> " + Arrays.toString(canSeePersonsCount(new int[]{10, 6, 8, 5, 11, 9})));
        System.out.println("[5, 1, 2, 3, 10] -> " + Arrays.toString(canSeePersonsCount(new int[]{5, 1, 2, 3, 10})));
    }
}
