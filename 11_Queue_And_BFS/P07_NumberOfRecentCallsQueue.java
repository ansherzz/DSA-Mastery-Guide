import java.util.*;

/*
Problem: Number of Recent Calls
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
Implement a RecentCounter class that counts the number of recent requests within a time frame.
    ping(t) adds a new request at time t (in milliseconds) and returns the number of requests
    that happened in the inclusive range [t - 3000, t].
    It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.

Why this pattern fits:
Requests arrive in time order and expire in the same order: the oldest request is always the first to leave the range.
    First in, first out is a queue.

Approach:
Append each new timestamp to the back of a queue.
    Then remove timestamps from the front while they are older than t - 3000.
    Everything left is inside the range, so the queue size is the answer.

Worked Example:
Example:
    ping(1) -> 1, ping(100) -> 2, ping(3001) -> 3,
    ping(3002) -> 3, because the request at time 1 is now outside [2, 3002].

Time Complexity: O(1) amortized per ping
Space Complexity: O(number of requests inside one window)
*/

public class P07_NumberOfRecentCallsQueue {
    static class RecentCounter {
        private final Deque<Integer> requests = new ArrayDeque<>();

        int ping(int t) {
            requests.offer(t);
            while (requests.peek() < t - 3000) {
                requests.poll();
            }
            return requests.size();
        }
    }

    public static void main(String[] args) {
        RecentCounter counter = new RecentCounter();
        System.out.println(counter.ping(1));
        System.out.println(counter.ping(100));
        System.out.println(counter.ping(3001));
        System.out.println(counter.ping(3002));
    }
}
