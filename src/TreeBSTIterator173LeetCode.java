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
 * 这里看到二分查找树，一定会想到它的一个性质。「二分查找树的中序遍历依次得到的元
 * 素刚好是一个升序数组」，所以这道题无非就是把中序遍历的结果依次输出即可。
 * 解法一
 * 先不考虑题目 Note 中要求的空间复杂度和时间复杂度，简单粗暴一些。在构造函数中，
 * 对二叉树进行中序遍历，把结果保存到一个队列中，然后 next 方法直接执行出队操作即
 * 可。至于 hasNext 方法的话，判断队列是否为空即可。
 * 解法二
 * 解法一中我们把所有节点都保存了起来，其实没必要一次性保存所有节点，而是需要一个
 * 输出一个即可。所以我们要控制中序遍历的进程，不要让它一次性结束，如果用解法一递
 * 归的方法去遍历那就很难控制了，所以自然而然的会想到用栈模拟递归的过程。
 * 参考LeetCode94只需要把 stack 和 cur 作为成员变量，然后每次调用 next 就执行一
 * 次 while 循环，并且要记录当前值，结束掉本次循环。
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
