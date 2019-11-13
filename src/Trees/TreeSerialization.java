package Trees;

import java.util.ArrayList;
import java.util.List;

public class TreeSerialization {

    // basically
    public static List<Integer> serialize(TreeNode head) {
        List<Integer> list = new ArrayList<>();

        serializeH(head, list);
        return list;
    }

    public static void serializeH(TreeNode head, List<Integer> current) {
        if(head == null) {
            current.add(-1);
            return;
        }

        current.add(0, head.value);
        serializeH(head.left, current);
        serializeH(head.right, current);
    }

    // remove items from the list, every time you add something to the tree
    // if there is a -1, return null
    // if there is a node, recursively fill the left and right children
    // the list will be getting things removed from it constantly
    public static TreeNode deserialize(List<Integer> seq) {
        if(seq.get(0) == -1) {
            return null;
        }

        TreeNode tn = new TreeNode(seq.remove(0));
        tn.left = deserialize(seq);
        tn.right = deserialize(seq);
        return tn;
    }
}
