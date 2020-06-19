/**
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的
 * 一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 * 示例 1:
 * 给定的树 s:
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 t：
 *    4
 *   / \
 *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 * 示例 2:
 * 给定的树 s：
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * 给定的树 t：
 *    4
 *   / \
 *  1   2
 * 返回 false。
 * @author LemonLin
 * @Description :TreeisSubtree572LeetCode
 * @date 20.5.7-9:31
 * 参考：https://leetcode-cn.com/problems/subtree-of-another-tree/solution/java-di-gui-ban-by-kelly2018/
 * 用两棵树是否相等的函数，然后通过三次调用这个函数来判断。
 */
public class TreeisSubtree572LeetCode {
    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) return true;   // t 为 null 一定都是 true
        if (s == null) return false;  // 这里 t 一定不为 null, 只要 s 为 null，肯定是 false
        //三次调度，只要有一次是true即可。
        return isSubtree(s,t)||isSubtree(s.left,t)||isSubtree(s.right,t);
    }
    //两个数是否相同
    private boolean isSame(TreeNode p, TreeNode q){
        if(p==null&&q==null)return true;
        if (p==null||q==null)return false;
        if(p.val!=q.val)return false;
        return isSame(p.left,q.left)&&isSame(p.right,q.right);
    }
}
