import java.util.*;

/*
Problem: Loud and Rich
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
There is a group of n people labeled from 0 to n - 1, each with a different amount of money and a different level of quietness.
    richer[i] = [a, b] indicates that person a has more money than person b. The given data is logically consistent.
    quiet[i] is the quietness of person i, where a smaller value means quieter.
    Return an array answer where answer[x] = y if y is the quietest person among all people
    who definitely have equal to or more money than person x.

Why this pattern fits:
"Definitely richer" is transitive, so the people richer than x are everyone reachable from x
    in a graph with edges pointing from poorer to richer. The graph is a DAG because the data is consistent.
    The answer for x is the best over x and the answers of its direct richer neighbors, which is DFS with memoization.

Approach:
Build the graph with an edge b -> a for every pair [a, b].
    answer(x) starts as x itself. For each directly richer person y, compute answer(y) recursively
    and keep whichever candidate is quieter.
    Store each computed answer so that every person is solved once.

Worked Example:
Example:
    richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
    For person 0, the richer people are 1, 2, 3, 4, 5, 6. The quietest of them (and 0) is person 5.
    Result: [5, 5, 2, 5, 4, 5, 6, 7].

Time Complexity: O(n + e), where e is the number of richer pairs
Space Complexity: O(n + e)
*/

public class LoudAndRichDFSGraph {
    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<List<Integer>> richerThan = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            richerThan.add(new ArrayList<>());
        }
        for (int[] pair : richer) {
            richerThan.get(pair[1]).add(pair[0]);
        }

        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        for (int person = 0; person < n; person++) {
            dfs(person, richerThan, quiet, answer);
        }
        return answer;
    }

    private static int dfs(int person, List<List<Integer>> richerThan, int[] quiet, int[] answer) {
        if (answer[person] != -1) {
            return answer[person];
        }

        answer[person] = person;
        for (int richerPerson : richerThan.get(person)) {
            int candidate = dfs(richerPerson, richerThan, quiet, answer);
            if (quiet[candidate] < quiet[answer[person]]) {
                answer[person] = candidate;
            }
        }
        return answer[person];
    }

    public static void main(String[] args) {
        int[][] richer = {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};
        System.out.println(Arrays.toString(loudAndRich(richer, quiet)));
        System.out.println(Arrays.toString(loudAndRich(new int[][]{}, new int[]{0})));
    }
}
