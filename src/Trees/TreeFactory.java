package Trees;

public class TreeFactory {

    public static TreeNode create() {
        TreeNode tn1 = new TreeNode(1);
        TreeNode tn2 = new TreeNode(2);
        TreeNode tn3 = new TreeNode(3);
        TreeNode tn4 = new TreeNode(4);
        TreeNode tn5 = new TreeNode(5);
        TreeNode tn6 = new TreeNode(6);
        TreeNode tn7 = new TreeNode(7);
        TreeNode tn8 = new TreeNode(8);
        TreeNode tn9 = new TreeNode(9);
        TreeNode tn10 = new TreeNode(10);
        TreeNode tn11= new TreeNode(11);
        TreeNode tn12 = new TreeNode(12);
        TreeNode tn13 = new TreeNode(13);
        TreeNode tn14 = new TreeNode(14);
        TreeNode tn15 = new TreeNode(15);
        TreeNode tn16 = new TreeNode(16);
        TreeNode tn17 = new TreeNode(17);
        TreeNode tn18 = new TreeNode(18);
        TreeNode tn19 = new TreeNode(19);
        TreeNode tn20 = new TreeNode(20);

        tn1.setChildren(tn2, tn3);
        tn2.setChildren(tn4, tn5);
        tn3.setChildren(tn6, tn7);
        tn4.setChildren(tn8, tn9);
        tn5.setChildren(null, null);
        tn6.setChildren(null, tn10);
        tn7.setChildren(null, tn11);
        tn8.setChildren(tn12, null);
        tn9.setChildren(tn13, null);
        tn10.setChildren(tn14, null);
        tn11.setChildren(tn15, null);
        tn12.setChildren(null, tn16);
        tn13.setChildren(tn17, null);
        tn14.setChildren(tn18, tn19);
        tn15.setChildren(null, null);
        tn16.setChildren(null, null);
        tn17.setChildren(null, tn20);
        tn18.setChildren(null, null);
        tn19.setChildren(null, null);
        tn20.setChildren(null, null);

        return tn1;
    }
}
