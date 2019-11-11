package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PrintRows {

    /**
     * Start with putting the head into the queue. This is the first row.
     * Add children for each item in current row (i.e. nodes in the queue) to the next row list
     * when the current row (q) becomes empty, add all of the items from the nextRow to the current row
     * Also, as we are iterating through the current row, print the item out
     * When the current row finishes, print a new line
     * @param head
     */
    public static void print(TreeNode head) {
        Queue<TreeNode> q = new LinkedList<>();
        List<TreeNode> nextRow = new ArrayList<>();

        q.add(head);
        // each iteration will have the queue filled with a row
        // continually pop each node from the queue, print and add children to list
        // when the queue is empty add all of the nodes from the list into the queue
        //while(!q.isEmpty()) {

            while(!q.isEmpty()) {
                TreeNode current = q.remove();
                System.out.print(current.value);
                // add children to the nextRow list
                if(current.left != null)
                    nextRow.add(current.left);

                if(current.right != null)
                    nextRow.add(current.right);

                if(q.isEmpty()) {
                    // add the next row to the queue
                    q.addAll(nextRow);

                    // clear the nextRow
                    nextRow = new ArrayList<>();

                    // now create a new line
                    System.out.println("");
                    continue;
                }
                System.out.print("-");
            }
        //}
    }
}
