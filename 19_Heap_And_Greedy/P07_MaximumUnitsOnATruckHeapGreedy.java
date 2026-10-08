import java.util.*;

/*
Problem: Maximum Units on a Truck
Pattern: 19. Heap & Greedy
Difficulty: Easy

Interview Prompt:
You are assigned to put some boxes onto one truck. You are given a 2D array boxTypes,
    where boxTypes[i] = [numberOfBoxes, numberOfUnitsPerBox], and an integer truckSize,
    the maximum number of boxes that can be put on the truck.
    Return the maximum total number of units that can be put on the truck.

Why this pattern fits:
Every box takes exactly one slot on the truck regardless of its type.
    Since all slots cost the same, each slot should be filled with the most valuable box still available.
    That local rule is provably optimal: swapping any chosen box for a richer unused one never hurts.

Approach:
Sort the box types by units per box in descending order.
    Walk through the types, taking as many boxes as possible from each: min(boxes of this type, remaining capacity).
    Stop when the truck is full.

Worked Example:
Example:
    boxTypes = [[1, 3], [2, 2], [3, 1]], truckSize = 4
    Take 1 box of 3 units, 2 boxes of 2 units, and 1 box of 1 unit: 3 + 4 + 1 = 8.

Time Complexity: O(n log n)
Space Complexity: O(1) besides the sort
*/

public class P07_MaximumUnitsOnATruckHeapGreedy {
    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);

        int units = 0;
        for (int[] type : boxTypes) {
            if (truckSize == 0) {
                break;
            }
            int taken = Math.min(type[0], truckSize);
            units += taken * type[1];
            truckSize -= taken;
        }
        return units;
    }

    public static void main(String[] args) {
        System.out.println(maximumUnits(new int[][]{{1, 3}, {2, 2}, {3, 1}}, 4));
        System.out.println(maximumUnits(new int[][]{{5, 10}, {2, 5}, {4, 7}, {3, 9}}, 10));
    }
}
