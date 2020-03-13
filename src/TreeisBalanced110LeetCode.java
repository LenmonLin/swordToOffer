import common.MakeTree;
import common.TreeNode;

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
 * @author LemonLin
 * @Description :TreeisBalanced110LeetCode
 * @date 2020/1/9-20:00
 */
public class TreeisBalanced110LeetCode {
    // Definition for a binary tree node.
//    public class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;
//     TreeNode(int x) { val = x; }
//     }
    //这种写法其实和isBalanced是一样的效果，这样写容易说明一点问题。就是只遍历到
    // 中间某个节点就不遍历了，主要原因是&&的短路操作，而且是直接用的(isBalanced(root.left)
    // 的结果，而不是把他保存起来等右边也遍历好了再用：比如下面这样：
//    Boolean left = isBalanced2(root.left);
//    Boolean right = isBalanced2(root.right);
//    return left&&right;
    //这样写是必须把right都算完才能算right的与操作。不能使用到了短路操作
    public boolean isBalanced2(TreeNode root) {
        if (root == null){
            return true;
        }
        int leftHeight = treeHeight(root.left);
        int  rightHeight = treeHeight(root.right);
        if (Math.abs(leftHeight-rightHeight)>=2){
            return false;
        }
        if (!(isBalanced2(root.left)&&isBalanced2(root.right))){
            return false;
        }
        return true;
    }

     public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }
        int leftHeight = treeHeight(root.left);
        int  rightHeight = treeHeight(root.right);
        if (Math.abs(leftHeight-rightHeight)>=2){
            return false;
        }
        /**这里不能写返回true，否则如果是
         *    1
         *  2   3
         *4       5
         *6         7
         * 的情况下就会返回true，实际是false,因为这种递归函数，只要有一个返回值return
         * 就会终止整个递归函数。而且如果某个节点是满足平衡，但是也不能代表这个树满足
         * 平衡，但是如果某个节点不满足平衡，那么整科树一定不平衡。然后把递归满足出口
         * 设置在root == null，叶子节点上，只有前面的节点都没有问题，那么到了叶子节点
         * 自然应该返回true。
         * 这题和LeetCode100的区别，LeetCode100都是到了叶子节点才能判断对错，而本题
         * 在中途就能判断错。但是只能在叶子节点判断错误。
        */
//        if (Math.abs(leftHeight-rightHeight)<2){
//            return true;
//        }
        return isBalanced(root.left)&&isBalanced(root.right);
     }
    public  int  treeHeight(TreeNode treeNode){
        if (treeNode == null)return 0;
        int  leftHeight = treeHeight(treeNode.left);
        int rightHeight = treeHeight(treeNode.right);
        return Math.max(leftHeight,rightHeight)+1;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new MakeTree().makeTree2();
        System.out.println(new TreeisBalanced110LeetCode().isBalanced(treeNode));
    }
}
