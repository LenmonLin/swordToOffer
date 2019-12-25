/**
 *  给定两个二叉树，编写一个函数来检验它们是否相同。
 *  如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *  示例 1:
 *  输入:       1         1
 *               / \       / \
 *             2   3     2   3
 *  [1,2,3],   [1,2,3]
 *  输出: true
 *  示例 2:
 *  输入:      1          1
 *               /           \
 *             2             2
 *  [1,2],     [1,null,2]
 *  输出: false
 *  示例 3:
 *  输入:       1         1
 *                / \       / \
 *              2   1     1   2
 *  [1,2,1],   [1,1,2]
 *  输出: false
 *  思路比较简单，递归考虑左右子树如果都相同，则两个二叉树相同，递归出口是两颗树
 *  相同的判断，如果两颗树根节点都是null，则相同；
 *                  如果两棵树根节点一个为null，一个不为null，则不同
 *                  如果两个根节点的数值不同，则不同返回false
 * @author LemonLin
 * @Description :TreeisSameTree100LeetCode
 * @date 2019/12/23-11:38
 */
public class TreeisSameTree100LeetCode {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null&&q==null)return true;
        if (p==null||q==null)return false;
        if (p.val !=q.val) return false;
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }
}

