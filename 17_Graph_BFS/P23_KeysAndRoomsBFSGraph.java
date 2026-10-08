import java.util.*;

/*
Problem: Keys and Rooms using BFS
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
There are n rooms labeled 0 to n - 1. All rooms are locked except room 0.
    Each room contains a set of keys, and each key opens one specific room.
    rooms[i] lists the keys found in room i. You may move freely between rooms you have unlocked.

    Return true if you can visit every room.

    The DFS version is in folder 16. Solve it here with BFS.

Why this pattern fits:
Rooms are nodes and each key is a directed edge to the room it opens.
    "Can every room be visited starting from room 0?" is a reachability question,
    and BFS answers it by expanding from the start room through keys in the order they are found.

Approach:
Put room 0 in a queue and mark it visited.
    Take a room from the queue and look at its keys. Each key to an unvisited room unlocks that room,
    so mark it visited and enqueue it.
    When the queue is empty, compare the number of visited rooms with n.

Worked Example:
Example:
    rooms = [[1], [2], [3], []]
    Room 0 gives key 1, room 1 gives key 2, room 2 gives key 3. All four rooms are visited.

    rooms = [[1, 3], [3, 0, 1], [2], [0]]
    No room contains a key to room 2 except room 2 itself, so it can never be opened.

Time Complexity: O(rooms + keys)
Space Complexity: O(rooms)
*/

public class P23_KeysAndRoomsBFSGraph {
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        visited[0] = true;
        int visitedCount = 1;

        while (!queue.isEmpty()) {
            int room = queue.poll();
            for (int key : rooms.get(room)) {
                if (!visited[key]) {
                    visited[key] = true;
                    visitedCount++;
                    queue.offer(key);
                }
            }
        }
        return visitedCount == rooms.size();
    }

    private static List<List<Integer>> toRooms(int[][] keys) {
        List<List<Integer>> rooms = new ArrayList<>();
        for (int[] roomKeys : keys) {
            List<Integer> list = new ArrayList<>();
            for (int key : roomKeys) {
                list.add(key);
            }
            rooms.add(list);
        }
        return rooms;
    }

    public static void main(String[] args) {
        System.out.println(canVisitAllRooms(toRooms(new int[][]{{1}, {2}, {3}, {}})));
        System.out.println(canVisitAllRooms(toRooms(new int[][]{{1, 3}, {3, 0, 1}, {2}, {0}})));
    }
}
