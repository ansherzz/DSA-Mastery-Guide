import java.util.*;

/*
Problem: Time Needed to Buy Tickets
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
There are n people in a line to buy tickets, where person 0 is at the front.
    tickets[i] is the number of tickets person i wants to buy.
    Each person takes exactly 1 second to buy one ticket and must then go to the back of the line to buy more.
    A person who has no tickets left to buy leaves the line.
    Return the time taken for the person at position k to finish buying tickets.

Why this pattern fits:
The process is a literal queue: serve the front, and re-enqueue it if it still needs service.
    Simulating it directly is the clearest correct solution.

Approach:
Put every index into a queue. Each second, poll the front person and decrease their remaining tickets.
    If that person is k and has none left, return the elapsed time.
    If the person still needs tickets, offer them at the back.
    Upgrade path: person i buys min(tickets[i], tickets[k]) tickets if i <= k and min(tickets[i], tickets[k] - 1)
    otherwise, which gives an O(n) formula without simulation.

Worked Example:
Example:
    tickets = [2, 3, 2], k = 2
    First round takes 3 seconds and leaves [1, 2, 1]. The second round takes 3 more seconds,
    and person 2 finishes at the end of it. Answer = 6.

Time Complexity: O(n * max tickets)
Space Complexity: O(n)
*/

public class P09_TimeNeededToBuyTicketsQueue {
    public static int timeRequiredToBuy(int[] tickets, int k) {
        int[] remaining = tickets.clone();
        Deque<Integer> line = new ArrayDeque<>();
        for (int i = 0; i < remaining.length; i++) {
            line.offer(i);
        }

        int seconds = 0;
        while (!line.isEmpty()) {
            int person = line.poll();
            remaining[person]--;
            seconds++;

            if (remaining[person] > 0) {
                line.offer(person);
            } else if (person == k) {
                return seconds;
            }
        }
        return seconds;
    }

    public static void main(String[] args) {
        System.out.println(timeRequiredToBuy(new int[]{2, 3, 2}, 2));
        System.out.println(timeRequiredToBuy(new int[]{5, 1, 1, 1}, 0));
    }
}
