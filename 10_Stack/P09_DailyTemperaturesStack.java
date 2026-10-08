import java.util.*;

/*
Problem: Daily Temperatures
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Given daily temperatures, return an array where answer[i] tells how many days you must wait
    after day i to see a warmer temperature. If none exists, answer[i] is 0.

    This is a classic monotonic-stack problem.

Why this pattern fits:
Each new temperature resolves some previous colder days.
    A stack of unresolved indices lets you fill answers at the moment a warmer day appears.

Approach:
Keep a stack of indices with decreasing temperatures.
    When the current temperature is higher than the temperature at the stack top,
    pop that index and fill in the waiting distance.

Worked Example:
Example:
    temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
    The answer is [1, 1, 4, 2, 1, 1, 0, 0].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P09_DailyTemperaturesStack {
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int previous = stack.pop();
                answer[previous] = i - previous;
            }
            stack.push(i);
        }

        return answer;
    }

    private static void runDemo(int[] temperatures) {
        System.out.println("Temps  : " + Arrays.toString(temperatures));
        System.out.println("Answer : " + Arrays.toString(dailyTemperatures(temperatures)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        runDemo(new int[]{30, 40, 50, 60});
    }
}
