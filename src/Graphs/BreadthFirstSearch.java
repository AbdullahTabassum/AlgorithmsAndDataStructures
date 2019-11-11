package Graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch {
    private static Set<Node> visited = new HashSet<Node>();
    private static Queue<Node> q = new LinkedList<Node>();
    public static void bfs(Node strt) {
        q.add(strt);
        visited.add(strt);
        while(!q.isEmpty()) {
            // add all of the children who have not been visited to the queue
            Node crt = q.remove();
            System.out.println("--- Node: " + crt.value);
            for(Node n: crt.adj) {
                if(!visited.contains(n)) {
                    q.add(n);
                    visited.add(n);
                }
            }
        }
        return;
    }
}
