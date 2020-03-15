import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * 思路：层次遍历，建议看一下浙江大学的数据结构对应章节，讲的很清晰，醍醐灌顶
 * https://www.icourse163.org/learn/ZJU-93001?tid=1003997005#/learn/content?type=detail&id=1007588486&cid=1009151984
 * 这里需要解决把每一层的元素放入一个ArrayList中，记录队列中的元素个数，把他作为循环的条件。
 * @author LemonLin
 * @Description :BFSlevelOrder102LeetCode
 * @date 19.8.21-10:29
 */
public class BFSlevelOrder102LeetCode {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //模板代码，第二层内循环用for循环比较好，便于计算层数以及每一层的第几个节点
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null)return new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList();
        //先放入根节点
        queue.addLast(root);
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()){
            //外循环内部可以记录层数。如果在这里设置cnt++，cnt表示层数。一定要放在
            //第二个for循环的外面。参考LeetCode103

            //size保存的是每一层的个数，可能是1个，可能是2个，可能是3个，都在变化。
            int size = queue.size();
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            //for循环是每一层的操作。
            for (int i=0;i<size;i++){
                //i表示某一层的第几个阶段，i=0,表示某一层第1个节点，i=size-1,表示某一层
                //的最后一个节点。参考LeetCode199和LeetCode513

                //先取出队列中的头节点，这里是removeFirst(),容易写错，要小心
                TreeNode treeNode  =queue.removeFirst();
                arrayList.add(treeNode.val);
                //再放入头结点的左右子节点。
                //这里需要有if判断，这个也是模板的一部分，参考LeetCode429
                if (treeNode.left!=null){
                    queue.addLast(treeNode.left);
                }
                if (treeNode.right!=null){
                    queue.addLast(treeNode.right);
                }
            }
            result.add(arrayList);
        }
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList();
        //先放入根节点
        queue.offer(root);
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()){
            int count = queue.size();
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            while (count>0){
                //先取出队列中的头节点
                TreeNode treeNode  =queue.poll();
                arrayList.add(treeNode.val);
                //再放入头结点的左右子节点。
                if (treeNode.left!=null){
                    queue.offer(treeNode.left);
                }
                if (treeNode.right!=null){
                    queue.offer(treeNode.right);
                }
                count--;
            }
            result.add(arrayList);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
