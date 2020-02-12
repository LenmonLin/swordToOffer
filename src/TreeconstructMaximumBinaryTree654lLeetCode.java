import common.TreeNode;
/**
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 * 示例 ：
 * 输入：[3,2,1,6,0,5]
 * 输出：返回下面这棵树的根节点：
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 * 提示：
 * 给定的数组的大小在 [1, 1000] 之间。
 * @author LemonLin
 * @Description :TreeconstructMaximumBinaryTree654lLeetCode
 * @date 20.2.12-17:42
 * 思路：参考：https://leetcode-cn.com/problems/maximum-binary-tree/solution/
 * zui-da-er-cha-shu-by-leetcode/
 * 用递归的方式解决，就是需要加个递归范围
 */
public class TreeconstructMaximumBinaryTree654lLeetCode {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return helper(nums,0,nums.length-1);
    }
    private TreeNode helper(int[] nums,int left,int right){
        //递归出口left >right时
        if (left>right){
            return null;
        }
        int maxIndex=max(nums,left,right);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = helper(nums,left,maxIndex-1);
        root.right = helper(nums,maxIndex+1,right);
        return root;
    }
    private int max(int[] nums,int left,int right){
        int index =left;
        for (int i=left;i<=right;i++){
            if (nums[index]<=nums[i]){
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] nums ={3,2,1,6,0,5};
        TreeNode treeNode = new TreeconstructMaximumBinaryTree654lLeetCode()
                .constructMaximumBinaryTree(nums);
    }
}
