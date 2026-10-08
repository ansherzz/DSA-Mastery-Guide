import java.util.*;

/*
Problem: Furthest Building You Can Reach
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
    You start at building 0 and move to the next building each time.
    Moving to a building of equal or lower height costs nothing.
    Moving up requires either one ladder or (height difference) bricks.
    Return the furthest building index (0-indexed) you can reach if you use the ladders and bricks optimally.

Why this pattern fits:
A ladder covers a climb of any size, so ladders should go to the largest climbs and bricks to the rest.
    The largest climbs are not known in advance, but a min-heap lets the decision be revised on the fly:
    it always knows which ladder-covered climb is the cheapest to convert back to bricks.

Approach:
Walk forward. For each climb, tentatively give it a ladder by pushing its size into a min-heap.
    If the heap now holds more climbs than there are ladders, pop the smallest climb and pay for it with bricks.
    If the bricks go negative, the current building cannot be reached, so return the previous index.
    If the walk finishes, the last building is reachable.

Worked Example:
Example:
    heights = [4, 2, 7, 6, 9, 14, 12], bricks = 5, ladders = 1
    Climbs: 5 (to index 2), 3 (to index 4), 5 (to index 5).
    Ladder on one climb of 5, bricks on the climb of 3 (2 bricks left). The next climb of 5 cannot be paid. Answer = 4.

Time Complexity: O(n log L), where L is the number of ladders
Space Complexity: O(L)
*/

public class P32_FurthestBuildingYouCanReachHeapGreedy {
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> ladderClimbs = new PriorityQueue<>();

        for (int i = 0; i < heights.length - 1; i++) {
            int climb = heights[i + 1] - heights[i];
            if (climb <= 0) {
                continue;
            }

            ladderClimbs.offer(climb);
            if (ladderClimbs.size() > ladders) {
                bricks -= ladderClimbs.poll();
            }
            if (bricks < 0) {
                return i;
            }
        }
        return heights.length - 1;
    }

    public static void main(String[] args) {
        System.out.println(furthestBuilding(new int[]{4, 2, 7, 6, 9, 14, 12}, 5, 1));
        System.out.println(furthestBuilding(new int[]{4, 12, 2, 7, 3, 18, 20, 3, 19}, 10, 2));
        System.out.println(furthestBuilding(new int[]{14, 3, 19, 3}, 17, 0));
    }
}
