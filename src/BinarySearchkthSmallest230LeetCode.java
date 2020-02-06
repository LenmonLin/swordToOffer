/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将
 * 如何优化 kthSmallest 函数？
 * @author LemonLin
 * @Description :BinarySearchkthSmallest230LeetCode
 * @date 20.2.6-18:39
 * 思路：中序遍历二叉搜索树，得到有序的数，遍历到第k个时停止。本题注意点，主要是
 * 要用全局变量来进行计数，局部变量在遍历的时候，会有改变，不靠谱。
 */
public class BinarySearchkthSmallest230LeetCode {
    /**
     * Definition for a binary tree node.
     */
   public static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }
    private int result =0,count=0;
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        travel(root);
        return result;
    }
    private void travel(TreeNode root){
       if (root==null||count==0){
           return;
       }
       travel(root.left);
       //count--也要放在中序，不能放在travel上面一行
        count--;
       if (count==0){
           result = root.val;
       }
       travel(root.right);
    }

    public static void main(String[] args) {
        //造一颗树[5,4,8,11,null,13,4,7,2,null,null,null,1]
        /*
         *    3
         *   / \
         *  1   4
         *   \
         *    2
         */
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(2);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left =null;
        treeNode2.right = treeNode5;
        treeNode3.left = null;
        treeNode3.right = null;
        System.out.println(new BinarySearchkthSmallest230LeetCode().
                kthSmallest(treeNode1, 1));
    }
}
