import java.util.*;

/*
Problem: Cheapest Flights Within K Stops
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
There are n cities connected by flights, where flights[i] = [from, to, price].
    Given a source city, a destination city, and an integer k, return the cheapest price
    to travel from the source to the destination using at most k stops (at most k + 1 flights).
    If there is no such route, return -1.

Why this pattern fits:
The limit is on the number of edges in the path, and BFS explores a graph one edge-layer at a time.
    Running BFS for exactly k + 1 layers considers every route within the stop limit,
    while a table of best prices prunes routes that are both longer and more expensive.

Approach:
Keep the cheapest known price to reach each city.
    Start with (source, 0) in the queue and process the queue one layer at a time, for at most k + 1 layers.
    For each (city, cost) in the layer, try every outgoing flight. If the new cost is cheaper than
    the best known price for the next city, record it and enqueue (next, new cost).
    Use the cost stored in the queue entry, not the table, so a later layer cannot borrow extra flights.

Worked Example:
Example:
    flights = [[0, 1, 100], [1, 2, 100], [2, 0, 100], [1, 3, 600], [2, 3, 200]], src = 0, dst = 3, k = 1
    With one stop the only route is 0 -> 1 -> 3, costing 700.
    The cheaper route 0 -> 1 -> 2 -> 3 costs 400 but needs two stops.

Time Complexity: O(k * E)
Space Complexity: O(V + E)
*/

public class P11_CheapestFlightsWithinKStopsBFSGraph {
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        int[] cheapest = new int[n];
        Arrays.fill(cheapest, Integer.MAX_VALUE);
        cheapest[src] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{src, 0});

        for (int flightsTaken = 0; flightsTaken <= k && !queue.isEmpty(); flightsTaken++) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                for (int[] flight : graph.get(current[0])) {
                    int cost = current[1] + flight[1];
                    if (cost < cheapest[flight[0]]) {
                        cheapest[flight[0]] = cost;
                        queue.offer(new int[]{flight[0], cost});
                    }
                }
            }
        }
        return cheapest[dst] == Integer.MAX_VALUE ? -1 : cheapest[dst];
    }

    public static void main(String[] args) {
        int[][] first = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        System.out.println("k = 1: " + findCheapestPrice(4, first, 0, 3, 1));
        System.out.println("k = 2: " + findCheapestPrice(4, first, 0, 3, 2));

        int[][] second = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        System.out.println("k = 1: " + findCheapestPrice(3, second, 0, 2, 1));
        System.out.println("k = 0: " + findCheapestPrice(3, second, 0, 2, 0));
    }
}
