import common.MakeTree;
import common.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 *  示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * @author LemonLin
 * @Description :TreepreorderTraversal144LeetCode
 * @date 20.2.12-16:55
 * 思路：主要考察非递归迭代写法
 * 参考：https://segmentfault.com/a/1190000016674584
 */
public class TreepreorderTraversal144LeetCode {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result  = new ArrayList();
        if (root==null){
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList();
        stack.addLast(root);
        while (!stack.isEmpty()){
            TreeNode temp = stack.pollLast();
            result.add(temp.val);
            //注意这里要先入右节点，再入左节点，这样出栈的时候才能先访问左结点，再
            // 访问右结点。
            //这里一定要加判断添加，否则会无法添加空指针到结果中
            if (temp.right!=null)stack.addLast(temp.right);
            if (temp.left!=null)stack.addLast(temp.left);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new MakeTree().makeTree2();
        List<Integer> integers = new TreepreorderTraversal144LeetCode().preorderTraversal(treeNode);
        for (int i=0;i<integers.size();i++){
            System.out.print(integers.get(i));
        }

    }
}
