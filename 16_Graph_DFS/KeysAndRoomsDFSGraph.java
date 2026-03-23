import java.util.*;

/*
Problem: Keys and Rooms
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
There are n rooms labeled 0 to n - 1. Each room may contain keys to other rooms.
    You start in room 0. Return true if you can visit every room.

    This is a reachability problem on a directed graph.

Why this pattern fits:
Rooms are nodes, keys are directed edges.
    DFS from room 0 tells you exactly which rooms are reachable.

Approach:
Start DFS from room 0.
    Whenever you discover a key to an unvisited room, recurse into that room.
    At the end, check whether every room was visited.

Worked Example:
Example:
    rooms = [[1],[2],[3],[]]
    The answer is true.

Time Complexity: O(V + E)
Space Complexity: O(V)
*/

public class KeysAndRoomsDFSGraph {
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        dfs(0, rooms, visited);

        for (boolean room : visited) {
            if (!room) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(int room, List<List<Integer>> rooms, boolean[] visited) {
        visited[room] = true;
        for (int key : rooms.get(room)) {
            if (!visited[key]) {
                dfs(key, rooms, visited);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(3));
        rooms.add(Collections.emptyList());

        System.out.println(canVisitAllRooms(rooms));
    }
}
