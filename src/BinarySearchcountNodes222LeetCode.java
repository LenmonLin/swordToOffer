/**
 * 给出一个完全二叉树，求出该树的节点个数。
 * 说明：
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节
 * 点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为
 * 第 h 层，则该层包含 1~ 2h 个节点。
 * 示例:
 * 输入:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 * 输出: 6
 * @author LemonLin
 * @Description :BinarySearchcountNodes222LeetCode
 * @date 20.2.6-18:41
 * 思路：直接一个一个遍历，时间复杂度也就O(N),就是这么没有追求。
 * 以为很low，没想到执行结果如下：这就很尴尬了。
 * 执行结果：通过
 * 显示详情
 * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗 :
 * 39.6 MB, 在所有 Java 提交中击败了24.98%的用户
 */
public class BinarySearchcountNodes222LeetCode {
    /**
     * Definition for a binary tree node.
     *
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private  int result =0;
    public int countNodes(TreeNode root) {
        travel(root);
        return result;
    }
    private void  travel(TreeNode root){
        if (root == null){
            return;
        }
        result++;
        travel(root.left);
        travel(root.right);
    }
}
