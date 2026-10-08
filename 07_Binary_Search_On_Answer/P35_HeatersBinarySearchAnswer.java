import java.util.*;

/*
Problem: Heaters
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
Winter is coming. Given the positions of houses and heaters on a horizontal line,
    return the minimum radius such that, when every heater uses that same radius, all houses are covered.
    A house at position h is covered by a heater at position p with radius r if |h - p| <= r.

Why this pattern fits:
A larger radius never uncovers a house, so "all houses are covered with radius r" is monotonic in r.
    Checking a fixed radius is a single linear sweep once both arrays are sorted.

Approach:
Sort houses and heaters. To test a radius, walk through the houses with a heater pointer:
    advance the pointer while the current heater's right reach (position + radius) is left of the house.
    If no heater remains, or the heater's left reach (position - radius) is right of the house, the radius fails.
    Binary search the radius between 0 and the full span of positions.

Worked Example:
Example:
    houses = [1, 2, 3, 4], heaters = [1, 4]
    With radius 1, heater 1 covers houses 1 and 2, and heater 4 covers houses 3 and 4. Radius 0 fails. Answer = 1.

Time Complexity: O(n log n + m log m + (n + m) log(position range))
Space Complexity: O(1) besides the sorts
*/

public class P35_HeatersBinarySearchAnswer {
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int low = 0;
        int high = Math.max(houses[houses.length - 1], heaters[heaters.length - 1])
            - Math.min(houses[0], heaters[0]);
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (coversAll(houses, heaters, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static boolean coversAll(int[] houses, int[] heaters, long radius) {
        int heater = 0;
        for (int house : houses) {
            while (heater < heaters.length && heaters[heater] + radius < house) {
                heater++;
            }
            if (heater == heaters.length || heaters[heater] - radius > house) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(findRadius(new int[]{1, 2, 3}, new int[]{2}));
        System.out.println(findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
        System.out.println(findRadius(new int[]{1, 5}, new int[]{2}));
    }
}
