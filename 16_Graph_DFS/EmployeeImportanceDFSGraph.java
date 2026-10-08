import java.util.*;

/*
Problem: Employee Importance
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
You have a data structure of employee information, including each employee's unique id,
    importance value, and the ids of their direct subordinates.
    Given the list of employees and an id, return the total importance value of that employee
    and all of their direct and indirect subordinates.

Why this pattern fits:
The reporting structure is a tree (a graph with edges from manager to subordinate).
    "This employee and everyone below" is the set of nodes reachable from the starting node, which DFS visits.

Approach:
The subordinates are given as ids, so first build a map from id to employee for O(1) lookup.
    Then DFS from the given id: the total for an employee is their own importance
    plus the totals of each direct subordinate.

Worked Example:
Example:
    employees: 1 (importance 5, subordinates 2 and 3), 2 (importance 3, subordinate 4),
    3 (importance 3), 4 (importance 1)
    Total importance of employee 1 = 5 + 3 + 3 + 1 = 12. Total importance of employee 2 = 3 + 1 = 4.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class EmployeeImportanceDFSGraph {
    static class Employee {
        int id;
        int importance;
        List<Integer> subordinates;

        Employee(int id, int importance, Integer... subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = Arrays.asList(subordinates);
        }
    }

    public static int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> byId = new HashMap<>();
        for (Employee employee : employees) {
            byId.put(employee.id, employee);
        }
        return dfs(byId, id);
    }

    private static int dfs(Map<Integer, Employee> byId, int id) {
        Employee employee = byId.get(id);
        int total = employee.importance;
        for (int subordinate : employee.subordinates) {
            total += dfs(byId, subordinate);
        }
        return total;
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, 5, 2, 3),
            new Employee(2, 3, 4),
            new Employee(3, 3),
            new Employee(4, 1));

        System.out.println(getImportance(employees, 1));
        System.out.println(getImportance(employees, 2));
        System.out.println(getImportance(employees, 3));
    }
}
