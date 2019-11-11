package Trees;

public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.value = val;
    }

    public void setChildren(TreeNode l, TreeNode r) {
        this.left = l;
        this.right = r;
    }
}
