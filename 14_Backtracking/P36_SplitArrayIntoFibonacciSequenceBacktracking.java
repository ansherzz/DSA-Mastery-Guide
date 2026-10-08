import java.util.*;

/*
Problem: Split Array into Fibonacci Sequence
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Split a digit string into a Fibonacci-like sequence of at least three non-negative 32-bit integers
(each number is the sum of the two before it). Numbers may not have leading zeros unless they are exactly 0.
Return any valid sequence, or an empty list.

Why this pattern fits:
The split points are unknown, so each prefix is tried as the next number.
Strong pruning is available: once two numbers exist, any candidate that is not their sum is rejected.

Approach:
dfs(start): at the end succeed when the list has at least 3 numbers. Extend a candidate one digit at a time
(stop on a leading zero or overflow). If the list has two or more numbers and the candidate exceeds their sum, stop;
if it is smaller, keep extending; otherwise add it and recurse.

Worked Example:
"1101111" -> 11, 0, 11, 11.

Time Complexity: O(n^2) choices for the first two numbers, then O(n) to verify
Space Complexity: O(n)
*/

public class P36_SplitArrayIntoFibonacciSequenceBacktracking {
    private static boolean dfs(String num, int start, List<Integer> sequence) {
        if (start == num.length()) {
            return sequence.size() >= 3;
        }
        long value = 0;
        for (int end = start; end < num.length(); end++) {
            if (end > start && num.charAt(start) == '0') {
                break;
            }
            value = value * 10 + (num.charAt(end) - '0');
            if (value > Integer.MAX_VALUE) {
                break;
            }
            int size = sequence.size();
            if (size >= 2) {
                long expected = (long) sequence.get(size - 1) + sequence.get(size - 2);
                if (value > expected) {
                    break;
                }
                if (value < expected) {
                    continue;
                }
            }
            sequence.add((int) value);
            if (dfs(num, end + 1, sequence)) {
                return true;
            }
            sequence.remove(sequence.size() - 1);
        }
        return false;
    }

    public static List<Integer> splitIntoFibonacci(String num) {
        List<Integer> sequence = new ArrayList<>();
        dfs(num, 0, sequence);
        return sequence;
    }

    public static void main(String[] args) {
        for (String num : new String[]{"1101111", "112358130", "0123"}) {
            System.out.println(num + " -> " + splitIntoFibonacci(num));
        }
    }
}
