/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径
 * 长度中的最大值。这条路径可能穿过根结点。
 * 示例 :
 * 给定二叉树
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * @author LemonLin
 * @Description :TreediameterOfBinaryTree543LeetCode
 * @date 20.3.10-15:14
 * 思路：参考：https://leetcode-cn.com/problems/diameter-of-binary-tree/solution/
 * hot-100-9er-cha-shu-de-zhi-jing-python3-di-gui-ye-/
 * 易错的点在于直径长度可能不经过根节点，所以需要求任意节点到任意节点的最大直径路径，
 * 这个在代码层面怎么处理，不可能每个节点都作为起始节点开始遍历，操作不了.
 * 解决方法：可以以每一个节点作为根进行计算，代码体现上就是引入了result的全局变量，
 * 同时代码层面是：result = Math.max(leftDepth+rightDepth,result);
 * 参考2：https://leetcode-cn.com/problems/diameter-of-binary-tree/solution/
 * er-cha-shu-de-zhi-jing-by-leetcode-solution/
 * 类似求树高，树高其实等同于求树的节点数。所以这里leftDepth等价于某个节点的左子树
 * 的节点个数。右子树以此类推。因为题目求的路径数为节点数-1.所以最后返回值为result-1
 */
public class TreediameterOfBinaryTree543LeetCode {
    /**
     * Definition for a binary tree node.
     *      */
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }
    int result = 0;
    public int diameterOfBinaryTree(TreeNode root) {
         if (root==null)return 0;
         helper(root);
         return result-1;
    }
    private  int helper(TreeNode root){
        //为什么不能用if(root.left==null&&root.right ==null)return 0?
        //不能用return 0，可以用return 1，但是多此一举，root == null就覆盖了这种情况。
        if (root==null){
             return 0;
         }
        //树高等价于求节点树，这里是左子树的节点数
         int leftDepth = helper(root.left);
        //右子树的节点数
         int rightDepth = helper(root.right);
         //路径的节点数还要加上根节点本身，所以这里有个加一。
         result = Math.max(leftDepth+rightDepth+1,result);
         return Math.max(leftDepth,rightDepth)+1;
    }
}
