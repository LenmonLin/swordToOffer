import common.TreeNode;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 说明:
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 * @author LemonLin
 * @Description :TreeisSymmetric101LeetCode
 * @date 20.2.13-20:55
 * 思路，这里不要想一颗树，要把一棵树的思维换成比较两颗树，这样就是递归过程如下：
 * 判断 A 的右子树与 B 的左子树是否对称
 * 判断 A 的左子树与 B 的右子树是否对称
 * 递归出口为：
 * 都为空指针则返回 true
 * 只有一个为空则返回 false
 * 判断两个指针当前节点值是否相等，不相等为false，不能写相等为true，因为即使两个
 * 值相等，也不能保证为true。只有所有相等才能为true，所有true不能写，只能写
 * if (root1==null&&root2==null)return true;
 * 为啥要写flase,因为这是递归函数有返回值，所有要把true和false的情况都考虑到。
 */
public class TreeisSymmetric101LeetCode {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public boolean isSymmetric(TreeNode root) {
        return helper(root,root);
    }
    public boolean helper(TreeNode root1,TreeNode root2){

        if (root1==null&&root2==null)return true;
        //这里是==null,不能用root1!=null||root2!=null
        if (root1==null||root2==null)return false;
        if (root1.val!=root2.val)return false;
        return helper(root1.left,root2.right)&&helper(root1.right,root2.left);
    }
}
