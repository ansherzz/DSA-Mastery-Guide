import java.util.*;

/*
Problem: Find the Winner of the Circular Game
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
n friends sit in a circle numbered 1 to n. Starting at friend 1, count k friends clockwise (including the start);
the last one counted leaves, and counting restarts from the next friend. Return the winner.

Why this pattern fits:
A circle is a queue whose front is moved to the back. Counting k friends is k - 1 rotations followed by one removal.

Approach:
Fill a queue with 1..n. While more than one friend remains: move the front to the back k - 1 times, then poll.

Worked Example:
n = 5, k = 2 -> friends leave in the order 2, 4, 1, 5. Answer = 3.

Time Complexity: O(n * k)
Space Complexity: O(n)
*/

public class P43_FindTheWinnerOfTheCircularGameQueue {
    public static int findTheWinner(int n, int k) {
        Deque<Integer> circle = new ArrayDeque<>();
        for (int friend = 1; friend <= n; friend++) {
            circle.add(friend);
        }
        while (circle.size() > 1) {
            for (int i = 1; i < k; i++) {
                circle.add(circle.poll());
            }
            circle.poll();
        }
        return circle.peek();
    }

    public static void main(String[] args) {
        System.out.println("n = 5, k = 2 -> " + findTheWinner(5, 2));
        System.out.println("n = 6, k = 5 -> " + findTheWinner(6, 5));
    }
}
