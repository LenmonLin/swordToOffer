import java.util.ArrayList;
import java.util.List;
/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
*  说明: 叶子节点是指没有子节点的节点。
*  示例:
*  给定如下二叉树，以及目标和 sum = 22，
*            5
*           / \
*         4   8
*         /   / \
*      11  13  4
*     /  \       / \
*    7    2    5   1
*  返回:
*  [
*  [5,4,11,2],
*  [5,8,4,5]
*  ]
 *  思路：利用112题的思路框架，然后当sum==0满足条件的时候，添加进二维list中。但是
 *  这个list怎么传什么时候传，是个问题。怎么解决如果满足某一个路径满足目标和，第二条
 *  路径使用新的ArrayList。
 *
 * 解惑：这题的思考量对我这个小菜鸡来说可以说极大的：
 * 1、其实本题有三种ArrayList，咋看是两种，其实是三种，第一种函数返回值List<List>;
 * 第二种，一条目标和路径，可能有多条；第三种，也是容易遗漏的一种，其实是遍历过程
 * 中的临时ArrayList，需要回溯的。
 * 2、关于思路中的解决第二条路径使用新的ArrayList，这里用到了一个临时的ArrayList,
 * 然后如果满足就把这个临时的ArrayList复制到新的ArrayList传入到最终的List中去。
 * 复制ArrayList可以用new ArrayList<>(temp);直接在括号中传入临时ArrayList即可；
 * 为什么要进行复制，因为List是引用类型，temp如果没有复制，添加进result之后，后期回溯
 * 修改还是会改变原来添加进result的temp，所以需要拷贝一份
 * 3、关于路径的记录这里要用到回溯，在递归函数之后加入回溯temp.remove(
 * temp.size()-1);这点比较难以理解，举个例子，遍历到7时，7下面没有左右子树了，回
 * 退到11，这个过程就是回溯，那么临时ArrayList需要减去7，然后才能往2遍历
 * 4、回溯的ArrayList减去最后一个值，remove操作的是下标，不是值，所以应该用
 * temp.remove(temp.size()-1);
 * 5、关于递归函数尽量用局部变量的说法，这里可以看到有时候也需要用到全局变量，需要
 * 传入函数参数，这样就不用把变量写在函数外面了，另外这里为什么用到全局变量，是
 * 因为最后结果是保存所有递归结果的一个总和，临时ArrayList也是所有递归过程中一直用
 * 同一个，所以需要用全局的变量。
 * 关于第5点是理解错误了，看129题解法就知道，作为参数传入函数的都是局部变量，只是
 * 因为这里使用的ArrayList是引用类型，引用类型在Java中就是设置为全局使用的。
 * @author LemonLin
 * @Description :TreepathSum113LeetCode
 * @date 2020/1/10-15:28
 */
public class TreepathSum113LeetCode {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>>  result = new ArrayList<>();
            ArrayList temp = new ArrayList();
            helper(root,sum,result,temp);
            return result;
    }
    public void helper(TreeNode root, int sum, List<List<Integer>> result, ArrayList
            temp){
        if (root == null)return;
        sum-=root.val;
        temp.add(root.val);
        if (root.left == null&&root.right==null &&sum == 0){
            result.add(new ArrayList<>(temp));
        }
        helper(root.left,sum,result,temp);
        helper(root.right,sum,result,temp);
        temp.remove(temp.size()-1);
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(8);
        TreeNode treeNode4 = new TreeNode(11);
        TreeNode treeNode6 = new TreeNode(13);
        TreeNode treeNode7 = new TreeNode(4);
        TreeNode treeNode8 = new TreeNode(7);
        TreeNode treeNode9 = new TreeNode(2);
        TreeNode treeNode10 = new TreeNode(5);
        TreeNode treeNode13 = new TreeNode(1);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left =treeNode4;
        treeNode2.right = null;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        treeNode4.left = treeNode8;
        treeNode4.right = treeNode9;
        treeNode6.left = null;
        treeNode6.right = null;
        treeNode7.left = treeNode10;
        treeNode7.right = treeNode13;
        treeNode8.left = null;
        treeNode8.right = null;
        treeNode9.left = null;
        treeNode9.right = null;
        treeNode10.left = null;
        treeNode10.right = null;
        treeNode13.left = null;
        treeNode13.right = null;
        List<List<Integer>> lists = new TreepathSum113LeetCode().pathSum(treeNode1, 22);

    }
}
