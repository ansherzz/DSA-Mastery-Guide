import java.util.*;

/*
Problem: Letter Combinations of a Phone Number
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given a string of digits from 2 to 9, return all possible letter combinations using the classic telephone keypad mapping.

    Each digit contributes a small set of choices, so this is a Cartesian-product style backtracking problem.

Why this pattern fits:
The answer is built one position at a time.
    For each digit, you branch over its possible letters.

Approach:
Map each digit to its letter string.
    Recurse over the digit index and append one possible letter at each step.

Worked Example:
Example:
    digits = "23"
    The combinations are ["ad","ae","af","bd","be","bf","cd","ce","cf"].

Time Complexity: O(4^n * n) in the worst case
Space Complexity: O(n)
*/

public class P08_LetterCombinationsPhoneNumberBacktracking {
    private static final String[] MAP = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public static List<String> letterCombinations(String digits) {
        List<String> answer = new ArrayList<>();
        if (digits.isEmpty()) {
            return answer;
        }
        backtrack(0, digits, new StringBuilder(), answer);
        return answer;
    }

    private static void backtrack(int index, String digits, StringBuilder current, List<String> answer) {
        if (index == digits.length()) {
            answer.add(current.toString());
            return;
        }

        String letters = MAP[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(index + 1, digits, current, answer);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
