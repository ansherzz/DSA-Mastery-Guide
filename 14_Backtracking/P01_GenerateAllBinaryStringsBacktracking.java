import java.util.*;

/*
Problem: Generate All Binary Strings of Length N
Pattern: 14. Backtracking
Difficulty: Easy

Interview Prompt:
Given n, return all binary strings of length n in lexicographic order.

Why this pattern fits:
This is the smallest complete backtracking example: n positions, two choices each,
and a decision tree whose 2^n leaves are the answers.

Approach:
Keep a char array. For position i place '0' and recurse, then place '1' and recurse.
When i reaches n, record the string.

Worked Example:
n = 2 -> 00, 01, 10, 11.

Time Complexity: O(n * 2^n)
Space Complexity: O(n) recursion depth besides the output
*/

public class P01_GenerateAllBinaryStringsBacktracking {
    private static void fill(char[] current, int index, List<String> result) {
        if (index == current.length) {
            result.add(new String(current));
            return;
        }
        for (char bit = '0'; bit <= '1'; bit++) {
            current[index] = bit;
            fill(current, index + 1, result);
        }
    }

    public static List<String> generate(int n) {
        List<String> result = new ArrayList<>();
        fill(new char[n], 0, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("n = 2 -> " + generate(2));
        System.out.println("n = 3 -> " + generate(3));
    }
}
