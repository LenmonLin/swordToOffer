import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * @author LemonLin
 * @Description :TreeinorderTraversal94LeetCode
 * @date 20.2.12-17:15
 * 思路：主要考察非递归迭代写法
     * 参考：https://segmentfault.com/a/1190000016674584
 * 主要解决先存根节点，然后循环不断存左节点，以及左结点的左结点的问题
 */
public class TreeinorderTraversal94LeetCode {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList();
        if (root==null)return result;
        TreeNode cur = root;
        //为什么这里是或者，因为当左边结点遍历结束之后，还需要入栈右边结点，这个时
        // 候，可能栈为空，但是cur不为空
        while (cur!= null||!stack.isEmpty()){
            while (cur!= null){
                stack.addLast(cur);
                cur = cur.left;
            }
            cur = stack.pollLast();
            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }
}
