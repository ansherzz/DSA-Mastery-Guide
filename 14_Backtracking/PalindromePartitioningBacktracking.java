import java.util.*;

/*
Problem: Palindrome Partitioning
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given a string s, partition it so that every substring of the partition is a palindrome.
    Return all possible palindrome partitionings of s.

Why this pattern fits:
A partition is a sequence of cut positions. At each position you choose where the next piece ends,
    but only palindromic pieces are allowed. Backtracking tries each valid end, recurses on the rest,
    and undoes the choice afterward.

Approach:
From a start index, try every end index.
    If s[start..end] is a palindrome, add it to the current partition and recurse from end + 1.
    When the start index reaches the end of the string, record a copy of the current partition.

Worked Example:
Example:
    s = "aab"
    "a" | "a" | "b" works, and "aa" | "b" works.
    "aab" and "ab" are not palindromes, so no other partition is possible.
    Answer = [[a, a, b], [aa, b]]

Time Complexity: O(n * 2^n) in the worst case
Space Complexity: O(n) recursion depth, excluding the output
*/

public class PalindromePartitioningBacktracking {
    public static List<List<String>> partition(String s) {
        List<List<String>> answer = new ArrayList<>();
        backtrack(0, s, new ArrayList<>(), answer);
        return answer;
    }

    private static void backtrack(int start, String s, List<String> pieces, List<List<String>> answer) {
        if (start == s.length()) {
            answer.add(new ArrayList<>(pieces));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (!isPalindrome(s, start, end)) {
                continue;
            }
            pieces.add(s.substring(start, end + 1));
            backtrack(end + 1, s, pieces, answer);
            pieces.remove(pieces.size() - 1);
        }
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(partition("aab"));
        System.out.println(partition("a"));
        System.out.println(partition("racecar"));
    }
}
