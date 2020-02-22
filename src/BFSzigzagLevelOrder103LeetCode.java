import java.util.*;
/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行
 * 下一层遍历，以此类推，层与层之间交替进行）。
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 * @author LemonLin
 * @Description :BFSzigzagLevelOrder103LeetCode
 * @date 20.1.16-19:38
 * 思路：记录层数，用链表(可以头部添加，尾部添加)，如果是奇数，从尾部添加，用addLast(),
 * 如果是偶数，从头部添加,用addFirst();
 * 这里注意LinkedList也能放入ArrayList。
 */
public class BFSzigzagLevelOrder103LeetCode {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            ArrayList result = new ArrayList();
            if(root == null) return result;
            LinkedList<TreeNode> queue = new LinkedList();
            queue.addLast(root);
            //记录层数。
            int cnt=0;
            while(!queue.isEmpty()){
                int size = queue.size();
                LinkedList<Integer> temp = new LinkedList();
                //这个要放在for循环外面。容易错
                cnt++;
                for(int i=0;i<size;i++){
                    TreeNode treenode = queue.removeFirst();
                    //层数的处理，必须得放在temp上操作。绝对不能放在上面那个queue上
                    // 操作，这点十分易错。如果放在queue上操作，会造成后期输出的节点的
                    // 左右节点入栈也跟着错，那就整个逻辑就错了。
                    if(cnt%2!=0){
                        temp.addLast(treenode.val);
                    }else{
                        temp.addFirst(treenode.val);
                    }
                    if(treenode.left!=null){
                        queue.addLast(treenode.left);
                    }
                    if(treenode.right!=null){
                        queue.addLast(treenode.right);
                    }
                }
                result.add(temp);
            }
            return result;
    }
    public static void main(String[] args) {
        BFSzigzagLevelOrder103LeetCode bfSzigzagLevelOrder103LeetCode
                = new BFSzigzagLevelOrder103LeetCode();
        TreeNode node1 =bfSzigzagLevelOrder103LeetCode.new TreeNode(3);
        TreeNode node2 =bfSzigzagLevelOrder103LeetCode.new TreeNode(9);
        TreeNode node3 =bfSzigzagLevelOrder103LeetCode.new TreeNode(20);
        TreeNode node6 =bfSzigzagLevelOrder103LeetCode.new TreeNode(15);
        TreeNode node7 =bfSzigzagLevelOrder103LeetCode.new TreeNode(7);
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
        List<List<Integer>> lists = new BFSzigzagLevelOrder103LeetCode().
                zigzagLevelOrder(node1);
        for (List list:lists){
            System.out.println("====");
            for (int i=0;i<list.size();i++){
                System.out.println(list.get(i));
            }
        }
    }
}
