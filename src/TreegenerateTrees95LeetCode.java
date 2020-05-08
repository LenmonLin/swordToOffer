import java.util.ArrayList;
import java.util.List;
/**
 * 来源：给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * 示例:
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * @author LemonLin
 * @Description :TreegenerateTrees95LeetCode
 * @date 20.5.8-11:21
 * 参考：https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-7/
 *
 */
public class TreegenerateTrees95LeetCode {
    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        if(n==0){
            return result;
        }
        return helper(1,n);
    }
    private List<TreeNode> helper(int start,int end){
        List<TreeNode>  result = new ArrayList<>();
        if (start>end){
            result.add(null);
            return result;
        }
        if(start == end){
            TreeNode treeNode = new TreeNode(start);
            result.add(treeNode);
            return result;
        }
        for (int i= start;i<=end;i++){
            List<TreeNode> leftTreeNodes =helper(start,i-1);
            List<TreeNode> rightTreeNodes = helper(i+1,end);
            for (TreeNode leftTree:leftTreeNodes){
                for (TreeNode rightTree:rightTreeNodes){
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    result.add(root);
                }
            }
        }
        return result;
    }
}
