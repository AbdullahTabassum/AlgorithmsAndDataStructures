package Graphs;

import java.util.HashSet;
import java.util.Set;

public class DepthFirstSearch {

    private static Set<Node> visited = new HashSet<Node>();
    public static void dfsRecur(Node strt) {
        System.out.println("--Node: " + strt.value);
        visited.add(strt);

        for(Node n: strt.adj ) {
            if(!visited.contains(n)) {
                dfsRecur(n);
            }
        }
        return;
    }
}
