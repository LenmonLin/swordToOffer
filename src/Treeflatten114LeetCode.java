import common.TreeNode;

/**
 * 给定一个二叉树，原地将它展开为链表。
 * 例如，给定二叉树
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 * @author LemonLin
 * @Description :Treeflatten114LeetCode
 * @date 20.2.13-11:40
 * 思路：参考https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 * solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
 * 将左子树插入到右子树的地方
 * 将原来的右子树接到左子树的最右边节点
 * 考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
 * 照着题解写的，这题这不好理解。感觉还是没理顺
 */
public class Treeflatten114LeetCode {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public void flatten(TreeNode root) {
        while (root!=null){
            if (root.left==null){
                root = root.right;
            }else {
                //找左子树最右边节点
                TreeNode pre = root.left;
                while (pre.right!=null){
                    pre = pre.right;
                }
                //原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right =root.left;
                //左子树置空
                root.left = null;
                //循环进入下一个结点
                root = root.right;
            }
        }
    }
}
