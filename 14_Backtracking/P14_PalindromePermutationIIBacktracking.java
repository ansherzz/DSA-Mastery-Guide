import java.util.*;

/*
Problem: Palindrome Permutation II
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given a string s, return all distinct palindromes that can be formed by rearranging its letters.
Return an empty list if none exist.

Why this pattern fits:
A palindrome is determined by its first half. Generating the unique permutations of the half
is far smaller than permuting the whole string and filtering.

Approach:
Count letters; more than one odd count means no palindrome. Build the half from count / 2 copies of each letter.
Generate permutations from the counts (each distinct letter is tried once per position),
then output half + middle + reversed half.

Worked Example:
s = "aabb" -> half "ab" has permutations "ab" and "ba" -> "abba" and "baab".

Time Complexity: O((n / 2)!) in the worst case
Space Complexity: O(n)
*/

public class P14_PalindromePermutationIIBacktracking {
    private static void permute(int[] count, StringBuilder half, int length, String middle, List<String> result) {
        if (half.length() == length) {
            result.add(half + middle + new StringBuilder(half).reverse());
            return;
        }
        for (int letter = 0; letter < 26; letter++) {
            if (count[letter] == 0) {
                continue;
            }
            count[letter]--;
            half.append((char) ('a' + letter));
            permute(count, half, length, middle, result);
            half.deleteCharAt(half.length() - 1);
            count[letter]++;
        }
    }

    public static List<String> generatePalindromes(String s) {
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        String middle = "";
        for (int letter = 0; letter < 26; letter++) {
            if (count[letter] % 2 == 1) {
                if (!middle.isEmpty()) {
                    return new ArrayList<>();
                }
                middle = String.valueOf((char) ('a' + letter));
            }
            count[letter] /= 2;
        }
        List<String> result = new ArrayList<>();
        permute(count, new StringBuilder(), s.length() / 2, middle, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("aabb -> " + generatePalindromes("aabb"));
        System.out.println("abc -> " + generatePalindromes("abc"));
    }
}
