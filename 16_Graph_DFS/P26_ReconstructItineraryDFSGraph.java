import java.util.*;

/*
Problem: Reconstruct Itinerary
Pattern: 16. Graph DFS
Difficulty: Hard

Interview Prompt:
You are given a list of airline tickets where tickets[i] = [from, to].
    Reconstruct the itinerary that uses every ticket exactly once, starting from "JFK".
    If several itineraries are valid, return the one that is smallest in lexical order when read as a list.
    At least one valid itinerary is guaranteed to exist.

Why this pattern fits:
Using every ticket (edge) exactly once is an Eulerian path.
    Hierholzer's algorithm finds one with a DFS that only records an airport after all of its outgoing tickets
    are used. Dead ends are therefore recorded first, and reversing the record gives a valid route.

Approach:
Store each airport's destinations in a min-heap so the smallest destination is always tried first.
    DFS from "JFK": while the current airport still has unused tickets, take the smallest one and recurse.
    When an airport has no tickets left, add it to the front of the itinerary.

Worked Example:
Example:
    tickets = [[JFK, KUL], [JFK, NRT], [NRT, JFK]]
    Greedy takes JFK -> KUL first, but KUL is a dead end, so KUL is recorded first (it ends up last).
    Back at JFK the search continues JFK -> NRT -> JFK. Final itinerary: [JFK, NRT, JFK, KUL].

Time Complexity: O(E log E)
Space Complexity: O(E)
*/

public class P26_ReconstructItineraryDFSGraph {
    public static List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> destinations = new HashMap<>();
        for (List<String> ticket : tickets) {
            destinations.computeIfAbsent(ticket.get(0), key -> new PriorityQueue<>()).add(ticket.get(1));
        }

        LinkedList<String> itinerary = new LinkedList<>();
        visit("JFK", destinations, itinerary);
        return itinerary;
    }

    private static void visit(String airport, Map<String, PriorityQueue<String>> destinations, LinkedList<String> itinerary) {
        PriorityQueue<String> next = destinations.get(airport);
        while (next != null && !next.isEmpty()) {
            visit(next.poll(), destinations, itinerary);
        }
        itinerary.addFirst(airport);
    }

    private static List<List<String>> tickets(String... flat) {
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < flat.length; i += 2) {
            list.add(Arrays.asList(flat[i], flat[i + 1]));
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(findItinerary(tickets("MUC", "LHR", "JFK", "MUC", "SFO", "SJC", "LHR", "SFO")));
        System.out.println(findItinerary(tickets("JFK", "SFO", "JFK", "ATL", "SFO", "ATL", "ATL", "JFK", "ATL", "SFO")));
        System.out.println(findItinerary(tickets("JFK", "KUL", "JFK", "NRT", "NRT", "JFK")));
    }
}
