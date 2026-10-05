import java.util.*;

/*
Problem: Time Needed to Inform All Employees
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
A company has n employees with ids 0 to n - 1. The head of the company has id headID.
    manager[i] is the direct manager of employee i, and manager[headID] = -1.
    The management structure forms a tree.

    The head wants to pass urgent news to everyone. Employee i needs informTime[i] minutes
    to inform all of their direct reports, who then start informing their own reports at the same time.
    Return the number of minutes needed until every employee knows the news.

Why this pattern fits:
The news travels down a tree, and different branches work in parallel.
    The total time is therefore the longest root-to-leaf path, where each edge costs the manager's inform time.
    A DFS computes the longest downward path from each node.

Approach:
Build a list of direct reports for each manager.
    Define time(employee) = informTime[employee] + the maximum of time(report) over all direct reports.
    Employees with no reports contribute 0. The answer is time(headID).

Worked Example:
Example:
    n = 6, headID = 2, manager = [2, 2, -1, 2, 2, 2], informTime = [0, 0, 1, 0, 0, 0]
    The head informs all five reports in 1 minute, and none of them has anyone to inform. Answer = 1.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class TimeNeededToInformAllEmployeesDFSGraph {
    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<List<Integer>> reports = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reports.add(new ArrayList<>());
        }
        for (int employee = 0; employee < n; employee++) {
            if (manager[employee] != -1) {
                reports.get(manager[employee]).add(employee);
            }
        }
        return dfs(headID, reports, informTime);
    }

    private static int dfs(int employee, List<List<Integer>> reports, int[] informTime) {
        int longest = 0;
        for (int report : reports.get(employee)) {
            longest = Math.max(longest, dfs(report, reports, informTime));
        }
        return informTime[employee] + longest;
    }

    public static void main(String[] args) {
        System.out.println(numOfMinutes(1, 0, new int[]{-1}, new int[]{0}));
        System.out.println(numOfMinutes(6, 2, new int[]{2, 2, -1, 2, 2, 2}, new int[]{0, 0, 1, 0, 0, 0}));
        System.out.println(numOfMinutes(7, 6, new int[]{1, 2, 3, 4, 5, 6, -1}, new int[]{0, 6, 5, 4, 3, 2, 1}));
    }
}
