import Arrays.Reverse;
import Graphs.BreadthFirstSearch;
import Graphs.CreateGraph;
import Graphs.DepthFirstSearch;
import LinkedList.CreateLinkList;
import LinkedList.ReverseLinkedList;
import LinkedList.Sift;
import Miscelanious.MoveZeros;
import Miscelanious.Permutations;
import Miscelanious.Subsets;
import Sorting.BinarySearch;
import Strings.Substrings;
import Trees.PrintRows;
import Trees.Traversals;
import Trees.TreeFactory;
import Trees.TreeSerialization;

import java.util.List;

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

        //CreateLinkList.printList(ReverseLinkedList.reverse(CreateLinkList.createSingly()));


        //Substrings.generate("hello");

//        List<Integer> list = TreeSerialization.serialize(TreeFactory.create());
//        for(int i : list) {
//            System.out.print(i + ",");
//        }

//        int[] test = {0,0,1,0,0,0,4,6,7,0,7,0,};
//        MoveZeros.move(test);
//        for(int i: test) {
//            System.out.print(i + ",");
//        }
//        int[] test = {1,2,3};
//        //Permutations.permute(test);
//        Subsets.subsets(test);

//        int[] test = {1,2,3,4,5,6};
//        Reverse.reverse(test);
//        for(int i: test) {
//            System.out.print(i);
//        }
        int i = 20;
        testingIncrement(++i);
    }

    public static void testingIncrement(int num) {
        System.out.println("Number: " + num);
    }
}
