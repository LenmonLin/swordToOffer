import common.MakeTree;
import common.TreeNode;

import java.util.LinkedList;

/**
 * 给你一棵二叉树，请你返回层数最深的叶子节点的和。
 * 示例：
 *图片：https://leetcode-cn.com/problems/deepest-leaves-sum/
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 * 提示：
 * 树中节点数目在 1 到 10^4 之间。
 * 每个节点的值在 1 到 100 之间。
 * @author LemonLin
 * @Description :TreedeepestLeavesSum1302LeetCode
 * @date 20.2.13-15:37
 * 思路：参考LeetCode103层次遍历,每层节点的数据都相加保存起来，直到最后一层。
 */
public class BFSdeepestLeavesSum1302LeetCode {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    //修改成模板写法，更易懂一些
    public int deepestLeavesSum2(TreeNode root) {
        int result =0;
        if (root == null)return result;
        LinkedList<TreeNode> queue = new LinkedList();
        queue.addLast(root);
        while (!queue.isEmpty()){
            //用来暂时存放每一层的节点。
            int tempResult =0;
            //这里必须用一个变量保存，否则直接用size的话会出问题，因为遍历过程中，队
            // 列不断加入下一层的节点，所以size会不断增加。
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode treeNode = queue.removeFirst();
                tempResult+=treeNode.val;
                if (treeNode.left!=null){
                    queue.addLast(treeNode.left);
                }
                if (treeNode.right!=null){
                    queue.addLast(treeNode.right);
                }
            }
            //这里不去判断最后一层，和LeetCode513类似，最后一层的处理方式都是不去
            // 刻意求最后一层，用覆盖法，层序遍历结束，就一定是最后一层。
            result = tempResult;
        }
        return result;
    }
    public int deepestLeavesSum(TreeNode root) {
        int result =0;
        if (root == null)return result;
        LinkedList<TreeNode> queue = new LinkedList();
        queue.addLast(root);
        while (!queue.isEmpty()){
            //用来暂时存放每一层的节点。
            int tempResult =0;
            //这里必须用一个变量保存，否则直接用size的话会出问题，因为遍历过程中，队
            // 列不断加入下一层的节点，所以size会不断增加。
            int cnt = queue.size();
            while (cnt>0){
                TreeNode treeNode = queue.pollFirst();
                tempResult+=treeNode.val;
                if (treeNode.left!=null){
                    queue.addLast(treeNode.left);
                }
                if (treeNode.right!=null){
                    queue.addLast(treeNode.right);
                }
                cnt--;
            }
            result = tempResult;
        }
        return result;
    }

    public static void main(String[] args) {
        /**
         *  *                  8
         *  *             6       10
         *  *          null   7    9    11
         */
        TreeNode root = new MakeTree().makeTree2();
        System.out.println(new BFSdeepestLeavesSum1302LeetCode().
                deepestLeavesSum(root));

    }
}
