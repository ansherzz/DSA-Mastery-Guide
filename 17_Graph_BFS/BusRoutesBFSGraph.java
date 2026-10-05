import java.util.*;

/*
Problem: Bus Routes
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
You are given an array routes where routes[i] is the list of stops that bus i visits, repeating forever in a loop.
    You start at the stop source (not on any bus) and want to reach the stop target, travelling only by bus.
    Return the least number of buses you must take, or -1 if the target cannot be reached.

Why this pattern fits:
The cost being minimized is the number of buses boarded, not the number of stops passed.
    So one BFS layer should mean "one more bus". From the stops reachable so far, board every bus
    that serves them and add all stops on those buses to the next layer.

Approach:
Build a map from each stop to the list of buses that visit it.
    BFS over stops, starting from the source, counting layers as buses taken.
    For each stop in the current layer, look at every bus that serves it. If the bus has not been boarded yet,
    mark it as boarded and enqueue all of its unvisited stops.
    Marking buses as boarded ensures each route is scanned only once.

Worked Example:
Example:
    routes = [[1, 2, 7], [3, 6, 7]], source = 1, target = 6
    Take bus 0 from stop 1 to stop 7, then bus 1 from stop 7 to stop 6. Answer = 2.

Time Complexity: O(total number of stops across all routes)
Space Complexity: O(total number of stops across all routes)
*/

public class BusRoutesBFSGraph {
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Map<Integer, List<Integer>> busesAtStop = new HashMap<>();
        for (int bus = 0; bus < routes.length; bus++) {
            for (int stop : routes[bus]) {
                busesAtStop.computeIfAbsent(stop, key -> new ArrayList<>()).add(bus);
            }
        }

        boolean[] boarded = new boolean[routes.length];
        Set<Integer> visitedStops = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        visitedStops.add(source);
        int buses = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            buses++;

            for (int i = 0; i < size; i++) {
                int stop = queue.poll();
                for (int bus : busesAtStop.getOrDefault(stop, Collections.emptyList())) {
                    if (boarded[bus]) {
                        continue;
                    }
                    boarded[bus] = true;

                    for (int nextStop : routes[bus]) {
                        if (nextStop == target) {
                            return buses;
                        }
                        if (visitedStops.add(nextStop)) {
                            queue.offer(nextStop);
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(numBusesToDestination(new int[][]{{1, 2, 7}, {3, 6, 7}}, 1, 6));
        System.out.println(numBusesToDestination(
            new int[][]{{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}}, 15, 12));
        System.out.println(numBusesToDestination(new int[][]{{1, 2, 7}, {3, 6, 7}}, 7, 7));
    }
}
