/**
* * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条
 * 路径上所有节点值相加等于目标和。
* 说明: 叶子节点是指没有子节点的节点。
* 示例: 给定如下二叉树，以及目标和 sum = 22，
*       5
*      / \
*    4   8
*    /   / \
*  11  13  4
*  /  \        \
* 7    2      1
* 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * 思路：使用深度优先搜索记录根到每个叶子节点的和。
 * 有一个问题没想好，就是不知道这个求和的变量怎么加比较好?不知道用全局还是局部
 * 变量，很混乱。
 * 看了题解：原来是反向操作，把sum进行减操作，当到叶子节点的时候，判断sum是否
 * 为0即可。有点意思。
 * @author LemonLin
 * @Description :TreehasPathSum112LeetCode
 * @date 2020/1/10-11:16
 */
public class TreehasPathSum112LeetCode {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        sum -=root.val;
        //到叶子节点，仔细体会return sum==0这句，这样写可以少写一个判断。
        if (root.left ==null&&root.right==null){
            return (sum==0);
        }
        return hasPathSum(root.left,sum)||
        hasPathSum(root.right,sum);
    }

    public static void main(String[] args) {
        //造一颗树[5,4,8,11,null,13,4,7,2,null,null,null,1]
                /*
                *       5
                *      / \
                *    4   8
                *    /   / \
                *  11  13  4
                *  /  \        \
                * 7    2      1
                */
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(8);
        TreeNode treeNode4 = new TreeNode(11);
        TreeNode treeNode6 = new TreeNode(13);
        TreeNode treeNode7 = new TreeNode(4);
        TreeNode treeNode8 = new TreeNode(7);
        TreeNode treeNode9 = new TreeNode(2);
        TreeNode treeNode13 = new TreeNode(1);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left =treeNode4;
        treeNode2.right = null;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        treeNode4.left = treeNode8;
        treeNode4.right = treeNode9;
        treeNode6.left = null;
        treeNode6.right = null;
        treeNode7.left = null;
        treeNode7.right = treeNode13;
        treeNode8.left = null;
        treeNode8.right = null;
        treeNode9.left = null;
        treeNode9.right = null;
        treeNode13.left = null;
        treeNode13.right = null;
        System.out.println(new TreehasPathSum112LeetCode().hasPathSum(treeNode1, 22));
    }
}
