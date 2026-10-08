import java.util.*;

/*
Problem: Letter Case Permutation
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given a string s made of letters and digits, you can transform every letter individually
    to be lowercase or uppercase. Return a list of all possible strings that can be created.

Why this pattern fits:
Each letter is an independent two-way choice and each digit is a forced choice.
    Walking the string position by position and branching only at letters is a small, clean backtracking tree.

Approach:
Work on a char array and recurse by index.
    If the current character is a digit, move on.
    If it is a letter, set it to lowercase and recurse, then set it to uppercase and recurse.
    When the index reaches the end, record the current array as a string.

Worked Example:
Example:
    s = "a1b2"
    The letters a and b each have two forms, giving 4 results: a1b2, a1B2, A1b2, A1B2

Time Complexity: O(n * 2^L) where L is the number of letters
Space Complexity: O(n) recursion depth, excluding the output
*/

public class P25_LetterCasePermutationBacktracking {
    public static List<String> letterCasePermutation(String s) {
        List<String> answer = new ArrayList<>();
        backtrack(0, s.toCharArray(), answer);
        return answer;
    }

    private static void backtrack(int index, char[] chars, List<String> answer) {
        if (index == chars.length) {
            answer.add(new String(chars));
            return;
        }

        if (!Character.isLetter(chars[index])) {
            backtrack(index + 1, chars, answer);
            return;
        }

        chars[index] = Character.toLowerCase(chars[index]);
        backtrack(index + 1, chars, answer);

        chars[index] = Character.toUpperCase(chars[index]);
        backtrack(index + 1, chars, answer);
    }

    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2"));
        System.out.println(letterCasePermutation("3z4"));
        System.out.println(letterCasePermutation("12345"));
    }
}
