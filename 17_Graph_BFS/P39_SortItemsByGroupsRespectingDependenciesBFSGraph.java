import java.util.*;

/*
Problem: Sort Items by Groups Respecting Dependencies
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
n items each belong to at most one of m groups (group[i] = -1 means no group).
beforeItems[i] lists items that must come before item i. Return an ordering in which items of the same group
are adjacent and all dependencies hold, or an empty array if impossible.

Why this pattern fits:
There are two layers of ordering constraints: between items, and - through items in different groups - between groups.
Each layer is an ordinary topological sort; a cycle in either makes the task impossible.

Approach:
Give every ungrouped item its own new group. Build the item graph from beforeItems and, for dependencies that cross
groups, the group graph. Kahn-sort both. Bucket the items by group in item order and concatenate the buckets in group order.

Worked Example:
With 8 items in the example, one valid answer is [6, 3, 4, 1, 5, 2, 0, 7]; adding a cyclic dependency makes it impossible.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P39_SortItemsByGroupsRespectingDependenciesBFSGraph {
    private static List<Integer> topologicalOrder(int count, List<List<Integer>> graph, int[] indegree) {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int node = 0; node < count; node++) {
            if (indegree[node] == 0) {
                queue.add(node);
            }
        }
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);
            for (int next : graph.get(node)) {
                if (--indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return order.size() == count ? order : new ArrayList<>();
    }

    public static int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        group = group.clone();
        for (int item = 0; item < n; item++) {
            if (group[item] == -1) {
                group[item] = m++;
            }
        }
        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            groupGraph.add(new ArrayList<>());
        }
        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];
        for (int item = 0; item < n; item++) {
            for (int before : beforeItems.get(item)) {
                itemGraph.get(before).add(item);
                itemIndegree[item]++;
                if (group[before] != group[item]) {
                    groupGraph.get(group[before]).add(group[item]);
                    groupIndegree[group[item]]++;
                }
            }
        }
        List<Integer> itemOrder = topologicalOrder(n, itemGraph, itemIndegree);
        List<Integer> groupOrder = topologicalOrder(m, groupGraph, groupIndegree);
        if (itemOrder.isEmpty() || groupOrder.isEmpty()) {
            return new int[0];
        }
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int item : itemOrder) {
            buckets.get(group[item]).add(item);
        }
        int[] result = new int[n];
        int index = 0;
        for (int g : groupOrder) {
            for (int item : buckets.get(g)) {
                result[index++] = item;
            }
        }
        return result;
    }

    private static boolean isValid(int[] order, int[] group, List<List<Integer>> beforeItems) {
        int[] position = new int[order.length];
        for (int i = 0; i < order.length; i++) {
            position[order[i]] = i;
        }
        for (int item = 0; item < order.length; item++) {
            for (int before : beforeItems.get(item)) {
                if (position[before] > position[item]) {
                    return false;
                }
            }
        }
        Set<Integer> closedGroups = new HashSet<>();
        for (int i = 0; i < order.length; i++) {
            int g = group[order[i]];
            if (g != -1 && (i == 0 || group[order[i - 1]] != g) && !closedGroups.add(g)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] group = {-1, -1, 1, 0, 0, 1, 0, -1};
        List<List<Integer>> before = Arrays.asList(Arrays.asList(), Arrays.asList(6), Arrays.asList(5), Arrays.asList(6),
                Arrays.asList(3, 6), Arrays.asList(), Arrays.asList(), Arrays.asList());
        int[] order = sortItems(8, 2, group, before);
        System.out.println("ordering found: " + Arrays.toString(order));
        System.out.println("ordering is valid -> " + (order.length == 8 && isValid(order, group, before)));
        List<List<Integer>> cyclic = Arrays.asList(Arrays.asList(), Arrays.asList(6), Arrays.asList(5), Arrays.asList(6),
                Arrays.asList(3), Arrays.asList(), Arrays.asList(4), Arrays.asList());
        System.out.println("with a cycle -> " + Arrays.toString(sortItems(8, 2, group, cyclic)));
    }
}
