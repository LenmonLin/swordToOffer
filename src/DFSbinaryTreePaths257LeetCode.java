import java.util.ArrayList;
import java.util.List;
/**
 *  给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *  说明: 叶子节点是指没有子节点的节点。
 *  示例:
 *  输入:
 *     1
 *   /   \
 *  2     3
 *    \
 *     5
 *  输出: ["1->2->5", "1->3"]
 *  解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *  思路：这题有点像之前的113题求目标和的路径题目，基本思想就是用到深度优先搜索，还有
 *  结合回溯的处理，需要处理的就是需要加字符串->。
 *  解题细节：本来考虑直接用StringBuilder作为临时的变量来存储字符，但是这样回溯的时候
 *  比较麻烦，需要对字符串进行删减，很难操作，最后考虑用一个path路径数组，记录路径
 *  的数字顺序，然后当遇到叶子节点时，也就是收集了一条完整路径时，把数组和->通过
 *  StringBuilder联系起来，省去了操作字符串的删减。
 *  最后添加到结果数组中，要使用StringBuilder.toString 转换为字符串。
 * @author LemonLin
 * @Description :DFSbinaryTreePaths257LeetCode
 * @date 2020/1/11-21:42
 */
public class DFSbinaryTreePaths257LeetCode {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList result = new ArrayList();
        ArrayList path = new ArrayList();
        helper(root,result,path);
        return  result;
    }
    public void helper(TreeNode root , ArrayList result, ArrayList path){
        if (root == null)return;
        path.add(root.val);
        if (root.left == null&&root.right == null){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0;i<path.size();i++){
                stringBuilder.append(path.get(i));
                if(i != path.size() - 1) {
                    stringBuilder.append("->");
                }
            }
            result.add(stringBuilder.toString());
        }
        helper(root.left,result,path);
        helper(root.right,result,path);
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        int test = 5;
        stringBuilder.append("1234"+test);
        stringBuilder.append("678");
        System.out.println("before delete....."+stringBuilder);
        stringBuilder.delete(stringBuilder.length()-2,stringBuilder.length()-1);
        System.out.println("after delete....."+stringBuilder);
    }
}
