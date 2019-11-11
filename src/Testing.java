import Graphs.BreadthFirstSearch;
import Graphs.CreateGraph;
import Graphs.DepthFirstSearch;
import LinkedList.CreateLinkList;
import LinkedList.ReverseLinkedList;
import LinkedList.Sift;
import Sorting.BinarySearch;
import Trees.PrintRows;
import Trees.Traversals;
import Trees.TreeFactory;

public class Testing {

    public static void main(String[] args) {
//        int[] sorted = {34,35,40,45,56};
//        if(BinarySearch.search(sorted, 33, 0, sorted.length)) {
//            System.out.println("Found the element");
//        } else {
//            System.out.println("Did not find the item");
//        }

        // DepthFirstSearch.dfsRecur(CreateGraph.createGraph());

        //BreadthFirstSearch.bfs(CreateGraph.createGraph());
//        Traversals.inOrderR(TreeFactory.create());
//        System.out.println("----Iterartive----");
//        Traversals.inOrderI(TreeFactory.create());
        //Traversals.levelTraversal(TreeFactory.create());

        //PrintRows.print(TreeFactory.create());

        //Sift.sift(CreateLinkList.createSingly012());

        CreateLinkList.printList(ReverseLinkedList.reverse(CreateLinkList.createSingly()));
    }
}
