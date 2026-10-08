import java.util.*;

/*
Problem: Predict the Winner
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Two players alternately take a number from either end of an array and add it to their score; both play optimally.
Return true if the first player can win or tie.

Why this pattern fits:
After a move, the roles swap: what remains is the same game with the opponent to move.
Tracking "my score minus the opponent's" lets one table serve both players through a sign flip.

Approach:
dp[i][i] = nums[i]. dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]).
The first player wins or ties when dp[0][n - 1] >= 0.

Worked Example:
nums = [1, 5, 2] -> whichever end player 1 takes, player 2 takes 5. Answer = false.

Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

public class P38_PredictTheWinnerDP {
    public static boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = nums[i];
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }

    public static void main(String[] args) {
        System.out.println("[1, 5, 2] -> " + predictTheWinner(new int[]{1, 5, 2}));
        System.out.println("[1, 5, 233, 7] -> " + predictTheWinner(new int[]{1, 5, 233, 7}));
    }
}
