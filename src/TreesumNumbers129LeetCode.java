/**
*  * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径
 *  都代表一个数字。例如，从根到叶子节点路径 1->2->3 代表数字 123。
*  计算从根到叶子节点生成的所有数字之和。
*  说明: 叶子节点是指没有子节点的节点。
*  示例 1:
*  输入: [1,2,3]
*     1
*    / \
*   2   3
*  输出: 25
*  解释:
*  从根到叶子节点路径 1->2 代表数字 12.
*  从根到叶子节点路径 1->3 代表数字 13.
*  因此，数字总和 = 12 + 13 = 25.
*  示例 2:
*  输入: [4,9,0,5,1]
*     4
*    / \
*   9   0
*  / \
* 5   1
*  输出: 1026
*  解释:
*  从根到叶子节点路径 4->9->5 代表数字 495.
*  从根到叶子节点路径 4->9->1 代表数字 491.
*  从根到叶子节点路径 4->0 代表数字 40.
*  因此，数字总和 = 495 + 491 + 40 = 1026.
 *  思路：参考第113的解法，用递归思路，然后设置两个全局变量传入函数，第一个变量用
 *  来记录临时的一条路径的拼凑数，这里要注意需要用到回溯。第二个变量用来记录总的和。
 *  注意这里的全局变量要设置在外面，不能通过在函数中传递参数来体现全局变量，在做
 *  113题的时候为什么可以，因为113题传递的是引用变量，是数组，在Java中引用变量
 *  天生就是全局的，这里的int是基础类型，基础类型在函数中作为参数传入就是局部变量，
 *  之前在113题的时候理解有误。
 *  还有一个套路，这里回溯写法是个固定的helper ,返回void，这里不能把helper去掉，
 *  目前是还想不到的，回溯写法需要另起一个辅助函数。先暂时记住这个套路吧
 * @author LemonLin
 * @Description :TreesumNumbers129LeetCode
 * @date 2020/1/11-10:22
 */
public class TreesumNumbers129LeetCode {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    int temp =0;
    int sum =0;
    public int sumNumbers(TreeNode root) {
        helper(root);
        return sum;
    }
    public void helper (TreeNode root){
        if (root == null)return;
        temp = temp*10+root.val;
        if (root.left ==null&&root.right ==null){
            sum+=temp;
        }
        helper(root.left);
        helper(root.right);
        temp = (temp-root.val)/10;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left =null;
        treeNode2.right = null;
        treeNode3.left = null;
        treeNode3.right = null;
        System.out.println(new TreesumNumbers129LeetCode().sumNumbers(treeNode1));
    }
}
