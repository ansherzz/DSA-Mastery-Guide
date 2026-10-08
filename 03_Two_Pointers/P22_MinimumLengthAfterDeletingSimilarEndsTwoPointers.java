import java.util.*;

/*
Problem: Minimum Length of String After Deleting Similar Ends
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
You may repeatedly pick a non-empty prefix and a non-empty suffix that do not overlap,
where all characters of both are the same single letter, and delete them.
Return the minimum possible length of the string.

Why this pattern fits:
The operation only touches the two ends, and deleting as much of the matching letter as possible is never worse.

Approach:
While left < right and both ends are equal, remember that letter,
move left past every copy of it and move right back past every copy of it.
The answer is the size of what remains (0 if the pointers cross).

Worked Example:
s = "aabccabba"
Strip a's -> "bccabb", strip b's -> "cca". Ends differ now. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P22_MinimumLengthAfterDeletingSimilarEndsTwoPointers {
    public static int minimumLength(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right && s.charAt(left) == s.charAt(right)) {
            char letter = s.charAt(left);
            while (left <= right && s.charAt(left) == letter) {
                left++;
            }
            while (right >= left && s.charAt(right) == letter) {
                right--;
            }
        }
        return right - left + 1;
    }

    public static void main(String[] args) {
        for (String s : new String[]{"ca", "cabaabac", "aabccabba"}) {
            System.out.println(s + " -> " + minimumLength(s));
        }
    }
}
