package common;

/**
 * @author LemonLin
 * @Description :MakeTree
 * @date 2018/3/22-17:04
 *                  8
 *             6       10
 *          null   7    9    11
 *
 */
public class MakeTree {

    public  TreeNodeCommon makeTree(){
        TreeNodeCommon treeNodeCommon1 = new TreeNodeCommon(8);
        TreeNodeCommon treeNodeCommon2 = new TreeNodeCommon(6);
        TreeNodeCommon treeNodeCommon3 = new TreeNodeCommon(10);
//        TreeNodeCommon treeNodeCommon4 = new TreeNodeCommon(5);
        TreeNodeCommon treeNodeCommon5 = new TreeNodeCommon(7);
        TreeNodeCommon treeNodeCommon6 = new TreeNodeCommon(9);
        TreeNodeCommon treeNodeCommon7 = new TreeNodeCommon(11);

        treeNodeCommon1.left = treeNodeCommon2;
        treeNodeCommon1.right = treeNodeCommon3;
        treeNodeCommon2.left = null;
        treeNodeCommon2.right = treeNodeCommon5;

        treeNodeCommon3.left = treeNodeCommon6;
        treeNodeCommon3.right = treeNodeCommon7;

        return treeNodeCommon1;
    }
    public  TreeNode makeTree2(){
        /**
         *                  8
         *             6       10
         *          null   7    9    11
         */
        TreeNode treeNodeCommon1 = new TreeNode(8);
        TreeNode treeNodeCommon2 = new TreeNode(6);
        TreeNode treeNodeCommon3 = new TreeNode(10);
//        TreeNode treeNodeCommon4 = new TreeNode(5);
        TreeNode treeNodeCommon5 = new TreeNode(7);
        TreeNode treeNodeCommon6 = new TreeNode(9);
        TreeNode treeNodeCommon7 = new TreeNode(11);
//        TreeNode treeNodeCommon8 = new TreeNode(11);
//        TreeNode treeNodeCommon9 = new TreeNode(11);

        treeNodeCommon1.left = treeNodeCommon2;
        treeNodeCommon1.right = treeNodeCommon3;
        treeNodeCommon2.left = null;
        treeNodeCommon2.right = treeNodeCommon5;

        treeNodeCommon3.left = treeNodeCommon6;
        treeNodeCommon3.right = treeNodeCommon7;

        return treeNodeCommon1;
    }
    public static void main(String[] args) {

    }
}
