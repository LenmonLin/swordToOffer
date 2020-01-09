/**
*  * 给定一个二叉树，判断它是否是高度平衡的二叉树。
*  本题中，一棵高度平衡二叉树定义为：
*  一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
*  示例 1:
*  给定二叉树 [3,9,20,null,null,15,7]
*  3
*  / \
*  9  20
*  /  \
*  15   7
*  返回 true 。
*  示例 2:
*  给定二叉树 [1,2,2,3,3,null,null,4,4]
*      1
*     / \
*    2   2
*    / \
*   3   3
*   / \
*  4   4
*  返回 false 。
 * 思路：总体思路是求某个节点的树高，这里利用求左子树树高和右子树树高的较大值加1，
 * 为某个节点的树高，求完树高之后，遍历比较每个节点的左子树和右子树的树高之差的
 * 绝对值是不是小于等于1，来判断是否平衡。
 * 改进思路：这里有个问题，就是要求所有结点的树高，其实只要比较有某个结点达到不平衡
 * 的条件就可以退出递归，不需要求出所有结点的树高，有点类似剪枝的意思
 * @author LemonLin
 * @Description :TreeisBalanced110LeetCode
 * @date 2020/1/9-20:00
 */
public class TreeisBalanced110LeetCode {
    // Definition for a binary tree node.
    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
     }
     public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }
        int leftHeight = treeHeight(root.left);
        int  rightHeight = treeHeight(root.right);
        //这里要注意判断是否是平衡二叉树需要节点本身满足平衡二叉树同时左子树满足平衡
         //右子树满足平衡，要三者与的关系，不要想漏了
        return Math.abs(leftHeight-rightHeight)<2&&isBalanced(root.left)&&
                isBalanced(root.right);
     }
    public  int  treeHeight(TreeNode treeNode){
        if (treeNode == null)return 0;
        int  leftHeight = treeHeight(treeNode.left);
        int rightHeight = treeHeight(treeNode.right);
        return Math.max(leftHeight,rightHeight)+1;
    }
}
