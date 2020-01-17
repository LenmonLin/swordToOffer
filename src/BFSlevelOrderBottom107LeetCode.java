import java.util.*;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节
 * 点所在的层，逐层从左向右遍历）
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *思路：参考102的层次遍历，加一个先进后出特性的栈来倒腾原先存入的数组即可。
 * @author LemonLin
 * @Description :BFSlevelOrderBottom107LeetCode
 * @date 20.1.15-17:28
 */
public class BFSlevelOrderBottom107LeetCode {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    Stack stack = new Stack();
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList result = new ArrayList();
        bfs(root);
        //因为在出栈的过程中stack.size()会变化，所以先用一个stackSize保存最开始的
        //栈大小。
        int stackSize = stack.size();
        for (int i=0;i<stackSize;i++){
            result.add(stack.pop());
        }
        return result;
    }
    public void bfs(TreeNode root){
        if (root==null)return;
        Queue queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()){
            int cnt =queue.size();
            ArrayList arrayList = new ArrayList();
            while (cnt>0){
                TreeNode treeNode = (TreeNode)queue.poll();
                arrayList.add(treeNode.val);
                if (treeNode.left!=null){
                    queue.offer(treeNode.left);
                }
                if (treeNode.right!=null){
                    queue.offer(treeNode.right);
                }
                cnt--;
            }
            stack.push(arrayList);
        }
    }
    public static void main(String[] args) {
        BFSlevelOrderBottom107LeetCode bfSlevelOrderBottom107LeetCode
                = new BFSlevelOrderBottom107LeetCode();
        TreeNode node1 =bfSlevelOrderBottom107LeetCode.new TreeNode(3);
        TreeNode node2 =bfSlevelOrderBottom107LeetCode.new TreeNode(9);
        TreeNode node3 =bfSlevelOrderBottom107LeetCode.new TreeNode(20);
        TreeNode node6 =bfSlevelOrderBottom107LeetCode.new TreeNode(15);
        TreeNode node7 =bfSlevelOrderBottom107LeetCode.new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = null;
        node2.right =null;
        node6.left = null;
        node6.right =null;
        node7.left = null;
        node7.right =null;
        node3.left = node6;
        node3.right = node7;
        List<List<Integer>> lists = new BFSlevelOrderBottom107LeetCode().
                levelOrderBottom(node1);
        for (List list:lists){
            System.out.println("====");
            for (int i=0;i<list.size();i++){
                System.out.println(list.get(i));
            }
        }
    }
}
