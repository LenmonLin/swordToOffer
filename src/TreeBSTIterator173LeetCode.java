import common.TreeNode;

import java.util.LinkedList;
/**
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * 示例：
 *图片：https://leetcode-cn.com/problems/binary-search-tree-iterator/
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 * 提示：
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一
 * 个下一个最小的数。
 * @author LemonLin
 * @Description :TreeBSTIterator173LeetCode
 * @date 20.2.13-11:19
 * 思路:参考：https://leetcode-cn.com/problems/binary-search-tree-iterator/
 * solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-4-4/
 */
public class TreeBSTIterator173LeetCode {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    LinkedList<TreeNode> stack = new LinkedList();
    TreeNode cur = null;
    public TreeBSTIterator173LeetCode(TreeNode root) {
        cur = root;
    }
    /** @return the next smallest number */
    public int next() {
        int result =-1;
        //这里是或者别写错成&&
        while (cur!=null||!stack.isEmpty()){
            while (cur !=null){
                stack.addLast(cur);
                cur = cur.left;
            }
            cur=stack.pollLast();
            result = cur.val;
            cur = cur.right;
            break;
        }
        return result;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return cur!=null||!stack.isEmpty();
    }
    /**
     * Your BSTIterator object will be instantiated and called as such:
     * BSTIterator obj = new BSTIterator(root);
     * int param_1 = obj.next();
     * boolean param_2 = obj.hasNext();
     */
}
