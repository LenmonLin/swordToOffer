/**
 * 翻转一棵二叉树。
 * 示例：
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * 思路：
 * 比较简单，利用一个中间变量来交换左右子节点，再递归调用左右子树即可解决问题。
 * @author LemonLin
 * @Description :TreeinvertTree226LeetCode
 * @date 19.8.6-11:29
 */
public class TreeinvertTree226LeetCode {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode invertTree(TreeNode root) {
        //这里用到了先序遍历
        if(root!=null){
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
            return root;
        }else {
            return  null;
        }
    }
    public static void main(String[] args) {

    }
}
