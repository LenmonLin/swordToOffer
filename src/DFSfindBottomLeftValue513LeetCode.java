/**
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出:
 * 1
 * 示例 2:
 * 输入:
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 * 输出:
 * 7
 * 注意: 您可以假设树（即给定的根节点）不为 NULL。
 * 思路：参考199题，深度优先遍历，同时遍历时用数组大小对比树高，来确定是否到达最
 * 底层。
 * 问题：参考199题，同一层右边的数值会覆盖左边的数值，达不到题目要求。
 * 解决：不能使用199题的ArrayList的方法，应该使用maxLevel全局变量和level局部
 * 变量进行比较，if (level>maxLevel)这个只会让第一次level大于maxLevel进入if，
 * 然后更新maxLevel，第二次同样值的level就不会比maxLevel大了，所以只执行了第
 * 一次的level。
 * 这题对递归的局部变量和全局变量进行了很好的考察，递归的全局变量要放在调用函数
 * findBottomLeftValue的外面，局部变量要放在递归函数helper的外面，可以在调用函数
 * findBottomLeftValue内定义，然后作为参数传入递归函数。这里maxLevel就是全局
 * 变量，因为记录的是曾经到达过的最大层数。level是局部变量，记录使用level的递归函数
 * 在第几层，仔细体会。
 * @author LemonLin
 * @Description :DFSfindBottomLeftValue513LeetCode
 * @date 20.1.15-11:49
 */
public class DFSfindBottomLeftValue513LeetCode {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    int maxLevel=-1;
    int result =0;
    public int findBottomLeftValue(TreeNode root) {
        if (root == null)return 0;
        int level =1;
        helper(root,level);
        return result ;
    }
    public void  helper(TreeNode root,int level){
        if (root == null)return;
        if (level>maxLevel){
            maxLevel = level;
            result = root.val;
        }
        helper(root.left,level+1);
        helper(root.right,level+1);
        //层数回溯
        level--;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode2;
        treeNode2.left = null;
        treeNode2.right = null;
        treeNode3.left = null;
        treeNode3.right = null;
        System.out.println(new DFSfindBottomLeftValue513LeetCode().findBottomLeftValue(treeNode1));
    }
}
