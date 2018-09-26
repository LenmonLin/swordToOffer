import common.TreeNodeCommon;

/**
 * @author LemonLin
 * @Description :BalancedBinaryTree39_2
 * @date 2018/5/13-11:17
 * 基本思路是利用求左右二叉树的深度，来求差值，利用递归思想。
 */
public class BalancedBinaryTree39_2 {
    public boolean IsBalanced_Solution(TreeNodeCommon root) {
        //空树也算平衡二叉树
        if (root == null)
            return true;
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        int diff = left-right;
        if (diff>1||diff<-1)
            return false;
        //左右树都是平衡树才能可以
        return IsBalanced_Solution(root.left)&&IsBalanced_Solution(root.right);
    }

    //求树的深度
    public int TreeDepth(TreeNodeCommon root){
        if (root==null){
            return 0;
        }
        int nleft = TreeDepth(root.left);
        int nright = TreeDepth(root.right);
        return (nleft>nright)?(nleft+1):(nright+1);
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

        BalancedBinaryTree39_2 balancedBinaryTree39_2 = new BalancedBinaryTree39_2();
        boolean b = balancedBinaryTree39_2.IsBalanced_Solution(treeNodeCommon1);
        System.out.println(b);
    }
}
