package Trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Traversals {
    public static void inOrderR(TreeNode head) {
        if (head == null) {
            return;
        }

        inOrderR(head.left);
        System.out.println("Node: " + head.value);
        inOrderR(head.right);
    }

    public static void inOrderI(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        //stack.add(head);
        TreeNode newHead = head;

        while(stack.size() > 0 || newHead != null) {
            // keep adding the left node of the last node on the stack
            // !NOTE!: we only want to add the left node of those nodes that are being added to the stack for the first time
            while(newHead != null) {
                stack.push(newHead);
                newHead = newHead.left;
            }

            // now the last node on the stack does not have a left node, so pop-it off
            TreeNode tn = stack.pop();
            System.out.println("Node: " + tn.value);

            // if this node has a right node, then add it to the stack
            // now the last node might have a right subtree, this right node will be considered a new head whose left nodes all need to be added
            // also, it might not have any right node, but that's ok as we'll check for it in nxt iter
            newHead = tn.right;

        }
    }

    public static void levelTraversal(TreeNode head) {
       Queue<TreeNode> q = new LinkedList<>();
       //System.out.println("Node: " + head.value );
       q.add(head);

       while(!q.isEmpty()) {
           TreeNode current = q.remove();
           System.out.println("Node: " + current.value );

           if(current.left != null) {
               q.add(current.left);
           }

           if(current.right != null) {
               q.add(current.right);
           }
       }

    }
}
