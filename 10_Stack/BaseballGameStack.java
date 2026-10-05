import java.util.*;

/*
Problem: Baseball Game
Pattern: 10. Stack
Difficulty: Easy

Interview Prompt:
You keep score for a baseball game with unusual rules. You are given a list of operations, where each one is:
    - an integer x: record a new score of x
    - "+": record a new score that is the sum of the previous two scores
    - "D": record a new score that is double the previous score
    - "C": invalidate the previous score and remove it from the record

    Return the sum of all scores on the record after applying every operation.
    All operations are guaranteed to be valid.

Why this pattern fits:
Every operation refers to the most recent scores, and "C" removes the most recent one.
    Access to the latest items with removal from the same end is exactly what a stack provides.

Approach:
Keep the valid scores on a stack.
    For "+", pop the top, peek at the one below, compute the sum, then push both back.
    For "D", push twice the top. For "C", pop. Otherwise parse the integer and push it.
    Sum the stack at the end.

Worked Example:
Example:
    ops = ["5", "2", "C", "D", "+"]
    5 -> [5], 2 -> [5, 2], C -> [5], D -> [5, 10], + -> [5, 10, 15]
    Total = 30.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class BaseballGameStack {
    public static int calPoints(String[] operations) {
        Deque<Integer> scores = new ArrayDeque<>();

        for (String op : operations) {
            switch (op) {
                case "+": {
                    int last = scores.pop();
                    int sum = last + scores.peek();
                    scores.push(last);
                    scores.push(sum);
                    break;
                }
                case "D":
                    scores.push(2 * scores.peek());
                    break;
                case "C":
                    scores.pop();
                    break;
                default:
                    scores.push(Integer.parseInt(op));
            }
        }

        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }

    private static void runDemo(String[] operations) {
        System.out.println("Ops   : " + Arrays.toString(operations));
        System.out.println("Total : " + calPoints(operations));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new String[]{"5", "2", "C", "D", "+"});
        runDemo(new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"});
    }
}
