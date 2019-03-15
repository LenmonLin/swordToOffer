import common.TreeNodeCommon;

/**
 * @author LemonLin
 * @Description :BalancedBinaryTree39_2
 * @date 2018/5/13-11:17
 * 题目描述
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * 注意：平衡二叉树和二叉搜索树没有一点关系
 *
 *第一种方法 基本思路是利用求左右二叉树的深度，来求差值，利用递归思想。这种做法有很明显的问题，在
 * 判断上层结点的时候，会多次重复遍历下层结点，增加了不必要的开销。
 * 第二种做法：如果改为从下往上遍历，如果子树是平衡二叉树，则返回子树的高度；如果发现子树不是平衡
 * 二叉树，则直接停止遍历，这样至多只对每个结点访问一次。（后序遍历）

 */
public class TreeBalancedBinaryTree39_2 {
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

    //第二种方法，只遍历一次
    public boolean IsBalanced_Solution2(TreeNodeCommon root){
        return getDepth(root) !=-1;
    }
    public int getDepth(TreeNodeCommon root){
        if (root ==null)return 0;
        int left = getDepth(root.left);
        //剪枝操作，如果左子树不是平衡二叉树，就不用继续遍历
        if (left == -1)return -1;
        int right = getDepth(root.right);
        //剪枝操作，如果右子树不是平衡二叉树，就不用继续遍历
        if (right ==-1)return -1;
        return Math.abs(left-right)>1?-1:(1+Math.max(left,right));
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

        TreeBalancedBinaryTree39_2 treeBalancedBinaryTree39_2 = new TreeBalancedBinaryTree39_2();
        boolean b = treeBalancedBinaryTree39_2.IsBalanced_Solution(treeNodeCommon1);
        System.out.println(b);
    }
}
