package Graphs;

public class CreateGraph {

    public static Node createGraph() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);

        n1.adj = new Node[]{n2};
        n2.adj = new Node[]{n3, n4, n5};
        n3.adj = new Node[]{n5, n6};
        n4.adj = new Node[]{};
        n5.adj = new Node[]{n8};
        n6.adj = new Node[]{n7};
        n7.adj = new Node[]{};
        n8.adj = new Node[]{n9};
        n9.adj = new Node[]{};

        return n1;
    }
}
