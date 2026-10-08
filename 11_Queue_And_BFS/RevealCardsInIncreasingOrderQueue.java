import java.util.*;

/*
Problem: Reveal Cards In Increasing Order
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
You are given an integer array deck of unique card values. You can order the deck in any way you want.
    Then you repeat the following steps until all cards are revealed:
    1. Take the top card of the deck, reveal it, and take it out of the deck.
    2. If there are still cards in the deck, put the next top card at the bottom of the deck.
    Return an ordering of the deck that would reveal the cards in increasing order.
    The first entry in the answer is the top of the deck.

Why this pattern fits:
The reveal process is a fixed sequence of queue operations: poll one, then move one from front to back.
    Running that same process on positions instead of values tells you the order in which positions get revealed.

Approach:
Sort the card values. Fill a queue with the positions 0..n-1 of the final deck.
    Simulate the process on the queue of positions: the polled position is the next one to be revealed,
    so assign it the next smallest card. Then move the following position to the back.

Worked Example:
Example:
    deck = [17, 13, 11, 2, 3, 5, 7]
    Positions are revealed in the order 0, 2, 4, 6, 3, 1, 5, receiving 2, 3, 5, 7, 11, 13, 17.
    Result: [2, 13, 3, 11, 5, 17, 7].

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class RevealCardsInIncreasingOrderQueue {
    public static int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        int[] sorted = deck.clone();
        Arrays.sort(sorted);

        Deque<Integer> positions = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            positions.offer(i);
        }

        int[] result = new int[n];
        for (int card : sorted) {
            result[positions.poll()] = card;
            if (!positions.isEmpty()) {
                positions.offer(positions.poll());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(deckRevealedIncreasing(new int[]{17, 13, 11, 2, 3, 5, 7})));
        System.out.println(Arrays.toString(deckRevealedIncreasing(new int[]{1, 1000})));
    }
}
