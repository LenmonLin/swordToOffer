import common.TreeNodeCommon;

/**
 * @author LemonLin
 * @Description :TreeDepth39_1
 * @date 2018/5/12-10:54
 * 题目描述
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，
 * 最长路径的长度为树的深度
 *
 * 解题思路：
 * 使用递归
 *      如果该树只有一个结点，它的深度为1;
 *      如果根节点只有左子树没有右子树，那么树的深度为左子树的深度加1；
 *      同样，如果只有右子树没有左子树，那么树的深度为右子树的深度加1。
 *      如果既有左子树也有右子树，那该树的深度就是左子树和右子树的最大值加1.
 */
public class TreeDepth39_1 {

    public int TreeDepth(TreeNodeCommon root) {
        if (root == null){
            return 0;
        }
        int nLeft = TreeDepth(root.left);
        int nRight = TreeDepth(root.right);

        return (nLeft>nRight)?(nLeft+1):(nRight+1);
    }


    public static void main(String[] args) {
        TreeNodeCommon treeNodeCommon1 = new TreeNodeCommon(10);
        TreeNodeCommon treeNodeCommon2 = new TreeNodeCommon(5);
        TreeNodeCommon treeNodeCommon3 = new TreeNodeCommon(12);
        TreeNodeCommon treeNodeCommon4 = new TreeNodeCommon(4);
        TreeNodeCommon treeNodeCommon5 = new TreeNodeCommon(7);

        treeNodeCommon1.left = treeNodeCommon2;
        treeNodeCommon1.right = treeNodeCommon3;
        treeNodeCommon2.left = treeNodeCommon4;
        treeNodeCommon2.right = treeNodeCommon5;

        TreeDepth39_1 treeDepth39_1 = new TreeDepth39_1();
        int iDepth = treeDepth39_1.TreeDepth(treeNodeCommon1);
        System.out.println(iDepth);
    }
}
