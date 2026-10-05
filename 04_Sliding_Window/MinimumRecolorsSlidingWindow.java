import java.util.*;

/*
Problem: Minimum Recolors to Get K Consecutive Black Blocks
Pattern: 04. Sliding Window
Difficulty: Easy

Interview Prompt:
You are given a string blocks where each character is 'W' (white) or 'B' (black), and an integer k.
    In one operation you can recolor a white block to black.
    Return the minimum number of operations needed so that there is at least one run of k consecutive black blocks.

Why this pattern fits:
Any run of k consecutive blocks is a fixed-length window.
    The cost to make a window fully black is the number of white blocks inside it,
    so the task is to find the window with the fewest whites.

Approach:
Count the white blocks in the first window of length k.
    Slide the window one step at a time, adjusting the count for the block that enters and the block that leaves.
    The smallest count seen is the answer.

Worked Example:
Example:
    blocks = "WBBWWBBWBW", k = 7
    The window "BBWWBBW" (indices 1..7) contains 3 whites, which is the minimum. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class MinimumRecolorsSlidingWindow {
    public static int minimumRecolors(String blocks, int k) {
        int whites = 0;
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                whites++;
            }
        }

        int best = whites;
        for (int right = k; right < blocks.length(); right++) {
            if (blocks.charAt(right) == 'W') {
                whites++;
            }
            if (blocks.charAt(right - k) == 'W') {
                whites--;
            }
            best = Math.min(best, whites);
        }
        return best;
    }

    private static void runDemo(String blocks, int k) {
        System.out.println("blocks = " + blocks + ", k = " + k);
        System.out.println("Recolors: " + minimumRecolors(blocks, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo("WBBWWBBWBW", 7);
        runDemo("WBWBBBW", 2);
    }
}
