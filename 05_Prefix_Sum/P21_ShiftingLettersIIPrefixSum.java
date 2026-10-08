import java.util.*;

/*
Problem: Shifting Letters II
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
shifts[i] = [start, end, direction] shifts every character of s in [start, end]
forward (direction 1) or backward (direction 0) by one letter, wrapping around the alphabet.
Return the final string after all shifts.

Why this pattern fits:
Applying each shift directly costs O(n) per query. Range updates followed by a single read
are exactly what a difference array is for.

Approach:
For each shift add delta at start and subtract it at end + 1, where delta is +1 or -1.
A running sum gives the net shift at each index; apply it modulo 26.

Worked Example:
s = "abc", shifts = [[0, 1, 0], [1, 2, 1], [0, 2, 1]] -> net shifts are 0, +1, +2. Answer = "ace".

Time Complexity: O(n + q)
Space Complexity: O(n)
*/

public class P21_ShiftingLettersIIPrefixSum {
    public static String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] diff = new int[n + 1];
        for (int[] shift : shifts) {
            int delta = shift[2] == 1 ? 1 : -1;
            diff[shift[0]] += delta;
            diff[shift[1] + 1] -= delta;
        }
        char[] chars = s.toCharArray();
        int net = 0;
        for (int i = 0; i < n; i++) {
            net += diff[i];
            int shifted = ((chars[i] - 'a' + net) % 26 + 26) % 26;
            chars[i] = (char) ('a' + shifted);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println("abc -> " + shiftingLetters("abc", new int[][]{{0, 1, 0}, {1, 2, 1}, {0, 2, 1}}));
        System.out.println("dztz -> " + shiftingLetters("dztz", new int[][]{{0, 0, 0}, {1, 1, 1}}));
    }
}
