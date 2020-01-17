import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 您需要在二叉树的每一行中找到最大的值。
 * 示例：
 * 输入:
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 * 输出: [1, 3, 9]
 * @author LemonLin
 * @Description :BFSlargestValues515LeetCode
 * @date 20.1.15-11:57
 * 思路：这题应该考虑用BFS，记录填写每一行的最大值。用了int count =queue.size()
 * 来记录每一层数的个数，比较巧妙。
 */
public class BFSlargestValues515LeetCode {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    ArrayList result = new ArrayList();
    int maxNumber =Integer.MIN_VALUE;
    public List<Integer> largestValues(TreeNode root) {
        bfs(root);
        return result;
    }
    public void bfs(TreeNode root){
        if (root == null)return;
        Queue<TreeNode>queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int count =queue.size();
            while (count>0){
                TreeNode treeNode =queue.poll();
                if (treeNode.val>maxNumber){
                    maxNumber = treeNode.val;
                }
                if (treeNode.left!=null){
                    queue.offer(treeNode.left);
                }
                if (treeNode.right!=null){
                    queue.offer(treeNode.right);
                }
                count--;
            }
            result.add(maxNumber);
            maxNumber=Integer.MIN_VALUE;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNodeCommon1 = new TreeNode(1);
        TreeNode treeNodeCommon2 = new TreeNode(3);
        TreeNode treeNodeCommon3 = new TreeNode(2);
        TreeNode treeNodeCommon4 = new TreeNode(5);
        TreeNode treeNodeCommon5 = new TreeNode(3);
        TreeNode treeNodeCommon6 = new TreeNode(9);
        treeNodeCommon1.left = treeNodeCommon2;
        treeNodeCommon1.right = treeNodeCommon3;
        treeNodeCommon2.left = treeNodeCommon4;
        treeNodeCommon2.right = treeNodeCommon5;
        treeNodeCommon3.left = null;
        treeNodeCommon3.right = treeNodeCommon6;
        treeNodeCommon4.left = null;
        treeNodeCommon4.right = null;
        treeNodeCommon5.left = null;
        treeNodeCommon5.right = null;
        treeNodeCommon6.left = null;
        treeNodeCommon6.right = null;
        List<Integer> integers = new BFSlargestValues515LeetCode().
                largestValues(treeNodeCommon1);
        for (Integer i:integers){
            System.out.println(i);
        }
    }
}
