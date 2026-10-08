import java.util.*;

/*
Problem: Can Place Flowers
Pattern: 19. Heap & Greedy
Difficulty: Easy

Interview Prompt:
You have a long flowerbed in which some of the plots are planted and some are not.
    Flowers cannot be planted in adjacent plots.
    Given an integer array flowerbed containing 0s (empty) and 1s (planted), and an integer n,
    return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.

Why this pattern fits:
Planting in the first legal plot never blocks a better arrangement:
    any arrangement that skips that plot can be shifted to use it without losing a flower.
    So a single left-to-right pass that plants whenever possible yields the maximum number of flowers.

Approach:
Scan the plots from left to right.
    A plot is legal if it is empty and both neighbors are empty, where a missing neighbor beyond either end counts as empty.
    When a plot is legal, plant there (write 1 so the next plot sees it) and decrease n.
    Return true as soon as n reaches 0.

Worked Example:
Example:
    flowerbed = [1, 0, 0, 0, 1]
    Only the middle plot is legal, so 1 flower fits but 2 do not.

Time Complexity: O(m), where m is the number of plots
Space Complexity: O(1)
*/

public class P08_CanPlaceFlowersHeapGreedy {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int[] bed = flowerbed.clone();

        for (int i = 0; i < bed.length && n > 0; i++) {
            boolean leftEmpty = i == 0 || bed[i - 1] == 0;
            boolean rightEmpty = i == bed.length - 1 || bed[i + 1] == 0;
            if (bed[i] == 0 && leftEmpty && rightEmpty) {
                bed[i] = 1;
                n--;
            }
        }
        return n == 0;
    }

    public static void main(String[] args) {
        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
        System.out.println(canPlaceFlowers(new int[]{0, 0, 1, 0, 0}, 2));
    }
}
