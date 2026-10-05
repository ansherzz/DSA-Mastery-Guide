import java.util.*;

/*
Problem: Assign Cookies
Pattern: 19. Heap & Greedy
Difficulty: Easy

Interview Prompt:
You want to give cookies to children, at most one cookie per child.
    Child i has a greed factor greed[i], the minimum cookie size that will make the child content.
    Cookie j has size sizes[j]. A child is content if the cookie they receive is at least as large as their greed factor.

    Return the maximum number of content children.

Why this pattern fits:
A greedy rule works: satisfy the least greedy child first, using the smallest cookie that is big enough.
    A small cookie that fits a modest child is never better spent elsewhere,
    and a cookie too small for the least greedy remaining child is useless for everyone.

Approach:
Sort both arrays. Walk through the cookies from smallest to largest with a pointer to the least greedy
    child who is still waiting. If the current cookie satisfies that child, move to the next child.
    Either way, move to the next cookie. The number of children passed is the answer.

Worked Example:
Example:
    greed = [1, 2], sizes = [1, 2, 3]
    Cookie 1 satisfies the child with greed 1. Cookie 2 satisfies the child with greed 2.
    Both children are content. Answer = 2.

Time Complexity: O(n log n + m log m)
Space Complexity: O(n + m) for the sorted copies
*/

public class AssignCookiesHeapGreedy {
    public static int findContentChildren(int[] greed, int[] sizes) {
        int[] sortedGreed = greed.clone();
        int[] sortedSizes = sizes.clone();
        Arrays.sort(sortedGreed);
        Arrays.sort(sortedSizes);

        int child = 0;
        for (int cookie = 0; cookie < sortedSizes.length && child < sortedGreed.length; cookie++) {
            if (sortedSizes[cookie] >= sortedGreed[child]) {
                child++;
            }
        }
        return child;
    }

    public static void main(String[] args) {
        System.out.println(findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
        System.out.println(findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3}));
        System.out.println(findContentChildren(new int[]{10, 9, 8, 7}, new int[]{5, 6, 7, 8}));
    }
}
