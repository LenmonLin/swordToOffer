import java.util.ArrayList;
import java.util.List;
/**
 * 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
 * 行数 m 应当等于给定二叉树的高度。
 * 列数 n 应当总是奇数。
 * 根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。根节点所在的行与列
 * 会将剩余空间划分为两部分（左下部分和右下部分）。你应该将左子树输出在左下部分，
 * 右子树输出在右下部分。左下和右下部分应当有相同的大小。即使一个子树为空而另一个
 * 非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。然而，
 * 如果两个子树都为空则不需要为它们留出任何空间。
 * 每个未使用的空间应包含一个空的字符串""。
 * 使用相同的规则输出子树。
 * 示例 1:
 * 输入:
 *      1
 *     /
 *    2
 * 输出:
 * [["", "1", ""],
 *  ["2", "", ""]]
 * 示例 2:
 * 输入:
 *      1
 *     / \
 *    2   3
 *     \
 *      4
 * 输出:
 * [["", "", "", "1", "", "", ""],
 *  ["", "2", "", "", "", "3", ""],
 *  ["", "", "4", "", "", "", ""]]
 * 示例 3:
 * 输入:
 *       1
 *      / \
 *     2   5
 *    /
 *   3
 *  /
 * 4
 * 输出:
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 * 注意: 二叉树的高度在范围 [1, 10] 中。
 * @author LemonLin
 * @Description :BFSprintTree655LeetCode
 * @date 20.5.10-17:06
 * 思路：先求树的深度，树的深度可以推算出数组每个元素的长度，比如：
 * 树高2，数组长3
 * 树高3，数组长7 ；
 * 树高4，数组长15；
 * 也就是数组长为2^(n)-1 ,其中n是树高。
 * 参考：https://leetcode-cn.com/problems/print-binary-tree/solution/java-di-
 * gui-by-zxy0917-13/
 * 假设第一行的起始索引位置为0，结束位置为width，我们可以观察到根节点在第一行的
 * 最中间位置mid，然后对于其左子树根节点，其插入位置在[0,mid-1]的最中间，其右子
 * 树根节点在[mid+1,width]的最中间
 */
public class DoublePointerprintTree655LeetCode {
    /**
     * Definition for a binary tree node.
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<String>> printTree(TreeNode root) {
        //1、求树的高度：
        int treeDepth=getTreeDepth(root);
        //2、求输出列表的每个数组元素的宽度：
        int listBroad = (int) Math.pow(2,treeDepth)-1;
        //3、初始化结果集：
        List<List<String>> result = new ArrayList<>(treeDepth);
        for (int i=0;i<treeDepth;i++){
            List<String> list = new ArrayList<>();
            for (int j = 0; j < listBroad; j++) {
                list.add("");
            }
            result.add(list);
        }
        // 4、结果集中填充结果
        helper(root, 1, 0, listBroad, result);
        return result;
    }
    private void helper(TreeNode root, int depth, int start, int end, List<List<String>> result) {
        if (root == null || start > end) return;
        //获取当前节点需要插入List的位置
        int insert = start + (end - start) / 2;
        result.get(depth - 1).set(insert, root.val + "");
        //递归
        helper(root.left, depth + 1, start, insert - 1, result);
        helper(root.right, depth + 1, insert + 1, end, result);
    }

    //求树的高度
    private int getTreeDepth(TreeNode treeNode){
        if (treeNode == null) return 0;
        return Math.max(getTreeDepth(treeNode.left), getTreeDepth(treeNode.right)) + 1;

    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode1.left = treeNode2;
        treeNode1.right =  null;
        List<List<String>> lists = new DoublePointerprintTree655LeetCode().printTree(treeNode1);
        for(int i =0;i<lists.size();i++){
            System.out.println(lists.get(i));
        }
    }
}
