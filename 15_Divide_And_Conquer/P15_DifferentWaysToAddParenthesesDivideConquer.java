import java.util.*;

/*
Problem: Different Ways to Add Parentheses
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given a string expression of non-negative integers and the operators '+', '-', and '*',
    return all possible results from computing the expression with every possible way of grouping
    the numbers and operators with parentheses. The results may be returned in any order.

Why this pattern fits:
Every way of fully parenthesizing an expression has one operator that is applied last.
    That operator divides the expression into a left part and a right part, each of which is
    the same problem on a shorter expression. Combining every left result with every right result conquers it.

Approach:
For each operator position in the expression, recursively compute all results of the left substring
    and all results of the right substring, then apply the operator to every pair.
    If the expression contains no operator, it is a single number and that number is the only result.
    Memoize by substring to avoid recomputing shared pieces.

Worked Example:
Example:
    expression = "2-1-1"
    Split at the first '-':  2 - (1 - 1) = 2
    Split at the second '-': (2 - 1) - 1 = 0
    Results: [2, 0]

Time Complexity: Exponential in the number of operators (Catalan growth)
Space Complexity: Exponential for the stored results
*/

public class P15_DifferentWaysToAddParenthesesDivideConquer {
    public static List<Integer> diffWaysToCompute(String expression) {
        return compute(expression, new HashMap<>());
    }

    private static List<Integer> compute(String expression, Map<String, List<Integer>> memo) {
        List<Integer> cached = memo.get(expression);
        if (cached != null) {
            return cached;
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char operator = expression.charAt(i);
            if (operator != '+' && operator != '-' && operator != '*') {
                continue;
            }

            List<Integer> leftResults = compute(expression.substring(0, i), memo);
            List<Integer> rightResults = compute(expression.substring(i + 1), memo);

            for (int a : leftResults) {
                for (int b : rightResults) {
                    if (operator == '+') {
                        results.add(a + b);
                    } else if (operator == '-') {
                        results.add(a - b);
                    } else {
                        results.add(a * b);
                    }
                }
            }
        }

        if (results.isEmpty()) {
            results.add(Integer.parseInt(expression));
        }
        memo.put(expression, results);
        return results;
    }

    private static void runDemo(String expression) {
        List<Integer> results = new ArrayList<>(diffWaysToCompute(expression));
        Collections.sort(results);
        System.out.println(expression + " -> " + results);
    }

    public static void main(String[] args) {
        runDemo("2-1-1");
        runDemo("2*3-4*5");
        runDemo("42");
    }
}
