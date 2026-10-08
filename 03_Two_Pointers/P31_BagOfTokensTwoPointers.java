import java.util.*;

/*
Problem: Bag of Tokens
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
You have `power` and a bag of tokens. Each token can be played once in one of two ways:
face up - spend tokens[i] power to gain 1 score; face down - spend 1 score to gain tokens[i] power.
Return the maximum score achievable.

Why this pattern fits:
After sorting, the best token to buy score with is always the cheapest, and the best one to cash in is the largest.
Those are the two ends of the array.

Approach:
Sort. While left <= right: if power covers tokens[left], play it face up and record the best score.
Otherwise, if score > 0 and more than one token remains, play tokens[right] face down. Otherwise stop.

Worked Example:
tokens = [100, 200, 300, 400], power = 200
Up 100 (score 1), down 400 (power 500, score 0), up 200 and up 300 (score 2). Answer = 2.

Time Complexity: O(n log n)
Space Complexity: O(1) extra
*/

public class P31_BagOfTokensTwoPointers {
    public static int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int left = 0;
        int right = tokens.length - 1;
        int score = 0;
        int best = 0;
        while (left <= right) {
            if (power >= tokens[left]) {
                power -= tokens[left++];
                score++;
                best = Math.max(best, score);
            } else if (score > 0 && left < right) {
                power += tokens[right--];
                score--;
            } else {
                break;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[100, 200, 300, 400], power = 200 -> " + bagOfTokensScore(new int[]{100, 200, 300, 400}, 200));
        System.out.println("[100], power = 50 -> " + bagOfTokensScore(new int[]{100}, 50));
        System.out.println("[200, 100], power = 150 -> " + bagOfTokensScore(new int[]{200, 100}, 150));
    }
}
